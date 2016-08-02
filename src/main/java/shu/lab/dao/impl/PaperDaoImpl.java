package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.PaperDao;
import shu.lab.entity.Paper;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.util.List;

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

    public boolean addPaper(Paper paper, Integer[] authors,Integer[] corrAuthors) {

        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        Transaction ts = session.beginTransaction();
        try{

            ts.begin();

            session.save(paper);
            Integer pid = paper.getPaperId();
            if (authors != null){
                for (int i = 0; i < authors.length; i++) {
                    Query q = session.createSQLQuery("INSERT INTO user_paper (user_id, paper_id, is_corr) VALUES ("+authors[i]+", "+pid+", 0)");
                    q.executeUpdate();
                }
            }

            if (corrAuthors != null){
                for (int i = 0; i < corrAuthors.length; i++) {
                    Query q = session.createSQLQuery("INSERT INTO user_paper (user_id, paper_id, is_corr) VALUES ("+corrAuthors[i]+", "+pid+", 1)");
                    q.executeUpdate();
                }
            }

            /*UserPaperDaoImpl upi = new UserPaperDaoImpl();

            Integer order = 0;
            for (Integer author:authors ) {
                order++;
                upi.addUserPaper(author,pid, order,0);

            }
            order = 0;
            for (Integer corrAuthor:corrAuthors ) {
                order++;
                upi.addUserPaper(corrAuthor,pid, order,1);
            }*/
            ts.commit();
        } catch (Exception e){
            /**
             * if insert authors or corrAuthors throws error,
             * delete the information that inserted before
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
            if (type == 1){

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
                Query q = session.createSQLQuery("INSERT INTO user_paper (paper_id, user_id, is_corr) VALUES ("+pid+", "+uid+", "+isCorr+")");
                return q.executeUpdate();

            } else if (type.equals(StaticParam.DELETE)){
                Query q = session.createSQLQuery("DELETE FROM user_paper WHERE paper_id="+pid+" AND user_id="+uid);
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
                return session.createSQLQuery("INSERT INTO  field_paper (field_id, paper_id) VALUES ("+fid+", "+pid+")")
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
