package shu.lab.dao.impl;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.PaperDao;
import shu.lab.entity.Paper;
import shu.lab.entity.User;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class PaperDaoImpl implements PaperDao {
    public List<Paper> getLatestTenPaper() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("from Paper order by paperId desc ")
                    .setMaxResults(10).list();

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Paper> getFamousTenPaper() {

        return null;
    }

    public Paper getPaperById(Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.load(Paper.class, pid);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Paper> getPaperByUserId(Integer uid, Integer page, Integer num) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createQuery("from Paper p join fetch p.userPapers up join fetch up.user upu where upu.userId=?  order by p.paperId desc ");
            q.setParameter(0,uid);
            return q.setFirstResult((page-1)*num).setMaxResults(num).list();

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Paper> getAllPaperByUserId(Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createQuery("from Paper p join fetch p.userPapers up join fetch up.user upu where upu.userId=?  order by p.paperId desc ");
            q.setParameter(0,uid);
            return q.list();

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Integer addPaper(Paper paper) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            session.save(paper);
            return paper.getPaperId();
        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    public boolean updPaperMember(Integer pId, List authors) {

        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try{

            ts.begin();
/*

            session.save(paper);
            Integer pid = paper.getPaperId();
*/
            /** delete previous authors from DB */
            session.createSQLQuery("DELETE FROM user_paper WHERE paper_id="+pId);

            /** and add it again */

            for (int i = 0; i < authors.size(); i++) {
                Map author = (Map) authors.get(i);
                /** 标识是否是数据库外的用户，默认为0，表示是库内的 */
                Integer isExtra = author.get("isExtra") == null ? 0:(Integer) author.get("isExtra");
                /** 标识是否是 通讯作者， Integer 1 表示是通讯作者 */
                Integer isCorr = (Integer) author.get("isCorr");
                /** 作者序号 */
                Integer order = (Integer) author.get("order");

                List extraList = new ArrayList();
                List extraCorrList = new ArrayList();
                /** 如果是数据库中的用户*/
                if (isExtra == 0){
                    /** get user by name */
                    String userName = (String) author.get("name");
                    User u = new UserDaoImpl().getUserByName(userName.trim());
                    /** 如果标签标识 是数据库用户，但是在哎数据库中没有查找到，同样添加到extraAuthors 中 */
                    if (u == null){
                        if (isCorr != 1) {
                            extraList.add(author);
                        } else {
                            extraCorrList.add(author);
                        }
                    }else{
                        Query q = session.createSQLQuery("INSERT INTO user_paper (user_id, paper_id, up_order, is_corr)" +
                                " VALUES ("+u.getUserId()+", "+pId+", "+ order +", "+isCorr+")");

                        q.executeUpdate();
                    }
                }
                /**
                 * 若不是数据库的用户, 将所有不是数据库内的用户重新组成一个List，
                 * */
                else {
                    if (isCorr != 1){
                        extraList.add(author);
                    } else {
                        extraCorrList.add(author);
                    }
                }
                /** 如果有库外作者 */
                if (extraList.size() > 0) {

                    Map map = new HashMap();
                    map.put("list", extraList);
                    JSONObject json = JSONObject.fromObject(map);
                    /** toJSONString 之后以字符形式存入数据库 */
                    Query q = session.createQuery("update Paper p set p.extraAuthor = ?");
                    q.setParameter(0, json.toString());
                    q.executeUpdate();
                }
                /** 如果有库外通讯作者 */
                if (extraCorrList.size() > 0) {

                    Map map = new HashMap();
                    map.put("list", extraCorrList);
                    JSONObject json = JSONObject.fromObject(map);
                    /** toJSONString 之后以字符形式存入数据库 */
                    Query q = session.createQuery("update Paper p set p.extraCorrAuthor = ?");
                    q.setParameter(0, json.toString());
                    q.executeUpdate();
                }

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
        } finally {
            session.close();
        }
        return false;
    }

    public void delPaper(Integer paperId) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try {
            ts.begin();
            Paper p = session.load(Paper.class, paperId);
            session.delete(p);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Integer addDelPaperField(Integer pid, Integer fid, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            /*Transaction ts = session.beginTransaction();
            ts.begin();
            Paper p = session.load(Paper.class, pid);
            Field f = session.load(Field.class, fid);
            p.getFieldPapers().add(f);
            session.save(p);
            ts.commit();*/
            //session.createSQLQuery("INSERT INTO field_paper (field_id, paper_id) VALUES ("+fid+","+pid+")").executeUpdate();
            if (type == StaticParam.ADD){

                return session.createSQLQuery("INSERT INTO field_paper (field_id, paper_id) VALUES ("+fid+", "+pid+")")
                        .executeUpdate();
            } else {
                return session.createSQLQuery("DELETE FROM field_paper WHERE paper_id="+pid+" AND field_id="+fid)
                        .executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }

    public Integer addDelPaperMember(Integer pid, Integer uid, Integer type, Integer isCorr) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try {

            if (type.equals(StaticParam.ADD)){
                /*
                BigInteger currIndex = (BigInteger) session.createSQLQuery
                        ("SELECT count(*) AS currIndex FROM user_paper WHERE paper_id=? AND user_id=? AND is_corr=?")
                        .uniqueResult();
                Query q = session.createSQLQuery("INSERT INTO user_paper (paper_id, user_id, up_order, is_corr)" +
                        " VALUES ("+pid+", "+uid+", "+currIndex.intValue()+", "+isCorr+")");
                return q.executeUpdate();
                */

            } else if (type.equals(StaticParam.DELETE)){

                /*find the order of the user in this paper that going to de delete*/
                Integer ord = (Integer) session.createSQLQuery("SELECT up_order FROM user_paper " +
                        "WHERE paper_id="+pid+" AND user_id="+uid+" AND is_corr="+isCorr).uniqueResult();

                /*then promote the other's order which behind it */
                session.createSQLQuery("UPDATE user_paper SET up_order = up_order-1 WHERE up_order > " + ord)
                        .executeUpdate();
                
                /*delete it from user_paper*/
                Query q = session.createSQLQuery("DELETE FROM user_paper " +
                        "WHERE paper_id="+pid+" AND user_id="+uid+" AND is_corr="+isCorr);
                return q.executeUpdate();
            }
            /*Query q = session.createQuery(sql);
            q.setParameter(0, pid);
            q.setParameter(1, uid);
            q.setParameter(2, isCorr);
            return q.executeUpdate();*/
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return -1;
    }

    public Integer addDelExtraMember(Integer pid, String extraAuthor, Integer type, Integer isCorr) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        Integer status = -1;
        try {
            ts.begin();
            Paper p = session.load(Paper.class, pid);
            if (type.equals(StaticParam.ADD) && isCorr == 1){
                /*append to extraCorrAuthors */
                String s = p.getExtraCorrAuthor();

                s += s==null || s.equals("") ? extraAuthor : "," + extraAuthor;

                p.setExtraCorrAuthor(s);
                status = 1;

            }else if (type.equals(StaticParam.ADD) && isCorr == 0) {
                /*append to extraAuthors */
                String s = p.getExtraAuthor();

                s += s==null ? extraAuthor : "," + extraAuthor;
                //s += ","+extraAuthor;

                p.setExtraAuthor(s);

                status = 1;

            } else if (type.equals(StaticParam.DELETE)){
                //String s = p.getExtraAuthor();
                String s = isCorr == 1 ? p.getExtraCorrAuthor() : p.getExtraAuthor();

                if(s != null && !s.equals("")){
                    Integer start = s.indexOf(extraAuthor);
                    Integer end = s.indexOf(",",start);
                    /* if at the end of string, there is no comma left*/
                    if (start > 0 && end == -1){

                        s = s.substring(0,start-1);
                        status = 1;

                    } else if (start > 0 && end != -1){

                        s = s.substring(0,start-1) + s.substring(end+1);
                        status = 1;
                    } else if (start == 0 && end == -1){

                        s = "";
                    } else{

                        s = s.substring(end + 1);
                    }
                    System.out.println("s = " + s);

                    if (isCorr == 1){
                        p.setExtraCorrAuthor(s);
                    } else {
                        p.setExtraAuthor(s);
                    }
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

    public Integer addDelFieldPaper(Integer fid, Integer pid, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            if (type.equals(StaticParam.ADD)){
                return session.createSQLQuery("INSERT INTO field_paper (field_id, paper_id) VALUES ("+fid+", "+pid+")")
                        .executeUpdate();
            } else {
                return session.createSQLQuery("DELETE FROM field_paper WHERE field_id="+fid+" AND paper_id="+pid)
                        .executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return 0;
    }
}
