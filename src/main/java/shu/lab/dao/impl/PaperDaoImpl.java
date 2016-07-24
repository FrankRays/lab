package shu.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.PaperDao;
import shu.lab.entity.Field;
import shu.lab.entity.Paper;
import shu.lab.util.HibernateUtil;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class PaperDaoImpl implements PaperDao {
    public List<Paper> getLatestTenPaper() {
        return null;
    }

    public List<Paper> getFamousTenPaper() {
        return null;
    }

    public List<Paper> getPaperByUserId(Integer uid, Integer page, Integer num) {
        return null;
    }

    public List<Paper> getAllPaperByUserId(Integer uid) {
        return null;
    }

    public boolean addPaper(List<Integer> authors,List<Integer> corrAuthors, String extraAuthor,
                            String extraCorrAuthor, String title, String category, String CCFStatus,
                            String postYear, String volNum, String issueNum, Integer startPage,
                            Integer endPage, String url) {
        Paper p = new Paper();
        p.setExtraAuthor(extraAuthor);
        p.setExtraCorrAuthor(extraCorrAuthor);
        p.setTitle(title);
        p.setCategory(category);
        p.setCcfStatus(CCFStatus);
        p.setPostYear(postYear);
        p.setVolNum(volNum);
        p.setIssueNum(issueNum);
        p.setStartPage(startPage);
        p.setEndPage(endPage);
        p.setSourceUrl(url);

        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try{

            Transaction ts = session.beginTransaction();
            session.save(p);
            ts.commit();

            Integer pid = p.getPaperId();

            UserPaperDaoImpl upi = new UserPaperDaoImpl();

            Integer order = 0;
            for (Integer author:authors ) {
                order++;
                upi.addUserPaper(author,pid, order,0);
            }
            order = 0;
            for (Integer corrAuthor:corrAuthors ) {
                order++;
                upi.addUserPaper(corrAuthor,pid, order,1);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }

    public void delPaper(Integer paperId) {

    }

    public void addPaperField(Integer pid, Integer fid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            Paper p = session.load(Paper.class, pid);
            Field f = session.load(Field.class, fid);
            p.getFieldPapers().add(f);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delPaperField(Integer pid, Integer fid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            Paper p = session.load(Paper.class, pid);
            Field f = session.load(Field.class, fid);
            p.getFieldPapers().remove(f);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
