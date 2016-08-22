package shu.lab.dao.impl;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.ProDao;
import shu.lab.entity.Project;
import shu.lab.entity.User;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class ProDaoImpl implements ProDao {

    public Project getProById(Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.load(Project.class, pid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Project> getLatestTenProject() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("from Project order by proId desc")
                    .setMaxResults(3).list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Project> getAllPro(Integer page, Integer num) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("from Project order by proId desc")
                    .setFirstResult((page-1)*num).setMaxResults(num).list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Project> getFamousTenProject() {
        return null;
    }

    public List<Project> getProjectByUserId(Integer uid, Integer page, Integer num) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("SELECT * FROM project p, user_project up WHERE p.pro_id = up.pro_id AND up.user_id="
                    +uid+" ORDER BY p.pro_id DESC").addEntity(Project.class).setFirstResult((page-1)*num).setMaxResults(num).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Project> getAllProjectByUserId(Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("SELECT * FROM project p, user_project up WHERE p.pro_id = up.pro_id AND up.user_id="
                    +uid+" ORDER BY p.pro_id DESC").addEntity(Project.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addProject(Project pro) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try {
            ts.begin();
            session.saveOrUpdate(pro);
            ts.commit();
            return pro.getProId();
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return 0;
    }

    public void delProject(Integer projectId) {
        System.out.println("ProDaoImpl.delProject");
        System.out.println("projectId = " + projectId);
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            session.createSQLQuery("DELETE FROM project WHERE pro_id="+projectId).executeUpdate();
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public boolean modifyFields(List fields, Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try {
            ts.begin();
            /** delete all fields with current project */
            session.createSQLQuery("DELETE FROM field_project WHERE project_id="+pid).executeUpdate();
            /** add new fields again */
            for (Object obj:fields) {
                String field = (String) obj;
                Integer fid = (Integer) session.createSQLQuery("SELECT field_id FROM field WHERE field_descr='"+field.trim()+"'").uniqueResult();

                session.createSQLQuery("INSERT INTO  field_project (field_id, project_id) VALUES ("+fid+", "+pid+")")
                        .executeUpdate();
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            ts.rollback();
        }finally {
            session.close();
        }
        return false;
    }

    public boolean modifyDirectors(Integer pId, List directors) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();

        if (directors.size() <= 0){
            return false;
        }

        try{
            ts.begin();
            /** delete previous directors from DB */
            session.createSQLQuery("DELETE FROM user_project WHERE pro_id="+pId).executeUpdate();

            /** and add it again */
            List extraList = new ArrayList();

            for (Object dire:directors) {
                Map director = (Map)dire;
                /** 标识是否是数据库外的用户，默认为0，表示是库内的 */
                Integer isExtra = director.get("isExtra") == null ? 0:(Integer) director.get("isExtra");
                /** 标识是否是 通讯作者， Integer 1 表示是通讯作者 */
                Integer isCorr = (Integer) director.get("isCorr");
                /** 作者序号 */
                Integer order = (Integer) director.get("order");

                /** 如果是数据库中的用户*/
                if (isExtra == 0){
                    /** get user by name */
                    String userName = (String) director.get("name");
                    User u = new UserDaoImpl().getUserByName(userName.trim());

                    /** 如果标签标识 是数据库用户，但是在哎数据库中没有查找到，同样添加到extraAuthors 中 */
                    if (u == null){
                        director.put("isExtra", 1);
                        extraList.add(director);
                    }else{
                        Query q = session.createSQLQuery("INSERT INTO user_project (user_id, pro_id, up_order)" +
                                " VALUES ("+u.getUserId()+", "+pId+","+order+")");
                        q.executeUpdate();
                    }
                }
                /**
                 * 若不是数据库的用户, 将所有不是数据库内的用户重新组成一个List，
                 * */
                else {
                    extraList.add(director);
                }
            }
            /** 如果有库外作者 */
            if (extraList.size() > 0) {
                Map map = new HashMap();
                map.put("list", extraList);
                JSONObject json = JSONObject.fromObject(map);
                /** toJSONString 之后以字符形式存入数据库 */
                Query q = session.createSQLQuery("UPDATE project SET extra_directors = '"+json.toString()+"' WHERE pro_id="+pId);
                q.executeUpdate();
            }
            ts.commit();
            return true;
        } catch (Exception e){
            /**
             * if insert authors throws error,
             * rollback all effort that have been done before
             **/
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    public List getInnerDirectorsById(Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            session.clear();
            List list = session.createQuery("select new map (u.username as name, up.upOrder as order) " +
                    "from UserProject up join up.user u join up.project p WHERE p.proId="+pid).list();
            if (list != null){
                for (Object obj:list) {
                    Map author = (Map) obj;
                    author.put("isExtra", 0);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer addDelProDirectors(Integer pid, Integer uid, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try {

            if (type.equals(StaticParam.ADD)){
                Query q = session.createSQLQuery("INSERT INTO user_project (pro_id, user_id) VALUES ("+pid+", "+uid+")");
                return q.executeUpdate();

            } else if (type.equals(StaticParam.DELETE)){
                Query q = session.createSQLQuery("DELETE FROM user_projectr WHERE pro_id="+pid+" AND user_id="+uid);
                return q.executeUpdate();
            }
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return -1;
    }

    public Integer addDelExtraDirectors(Integer pid, String extraDirectors, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        Integer status = -1;
        try {
            ts.begin();
            Project p = session.load(Project.class, pid);
            if (type.equals(StaticParam.ADD)) {
                /*append to extraAuthors */
                String s = p.getExtraDirectors();

                s += s==null ? extraDirectors : "," + extraDirectors;
                //s += ","+extraDirectors;

                p.setExtraDirectors(s);

                status = 1;

            } else if (type.equals(StaticParam.DELETE)){

                String s = p.getExtraDirectors();

                if(s != null && !s.equals("")){
                    Integer start = s.indexOf(extraDirectors);
                    Integer end = s.indexOf(",",start);
                    /* if at the end of string, there is no comma left*/
                    if (start > 0 && end == -1){

                        s = s.substring(0,start-1);
                        status = 1;

                    } else if (start > 0 && end != -1){

                        s = s.substring(0,start-1) + s.substring(end+1);
                        status = 1;
                    } else if (start == 0 && end == -1){

                        s = null;
                    } else{

                        s = s.substring(end + 1);
                    }
                    //System.out.println("s = " + s);

                    p.setExtraDirectors(s);
                }

            }
            ts.commit();
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return status;
    }
}
