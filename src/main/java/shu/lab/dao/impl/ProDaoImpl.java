package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.ProDao;
import shu.lab.entity.Field;
import shu.lab.entity.Paper;
import shu.lab.entity.Project;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class ProDaoImpl implements ProDao {
    public List<Project> getLatestTenProject() {
        return null;
    }

    public List<Project> getFamousTenProject() {
        return null;
    }

    public List<Project> getProjectByUserId(Integer uid, Integer page, Integer num) {
        return null;
    }

    public List<Project> getAllProjectByUserId(Integer uid) {
        return null;
    }

    public boolean addProject(Project pro) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try {
            ts.begin();
            session.save(pro);
            ts.commit();
            return true;
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public void delProject(Integer projectId) {

    }

    public Integer addDelFieldPro(Integer fid, Integer pid, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        //Transaction ts = session.beginTransaction();
        try {
            //ts.begin();
            if (type.equals(StaticParam.ADD)){
                return session.createSQLQuery("INSERT INTO  field_project (field_id, project_id) VALUES ("+fid+", "+pid+")")
                        .executeUpdate();
            } else {
                return session.createSQLQuery("DELETE FROM field_project WHERE field_id="+fid+" AND project_id="+pid)
                        .executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
            //ts.rollback();
        } finally {
            session.close();
        }
        return 0;
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
        } finally {
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
        } finally {
            session.close();
        }
        return status;
    }
}
