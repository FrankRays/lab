package shu.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.UserPaperDao;
import shu.lab.entity.Paper;
import shu.lab.entity.User;
import shu.lab.entity.UserPaper;
import shu.lab.util.HibernateUtil;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class UserPaperDaoImpl implements UserPaperDao {
    public void addUserPaper(Integer uid, Integer pid, Integer order, Integer isCorr) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            UserPaper up = new UserPaper();

            Transaction ts = session.beginTransaction();
            ts.begin();

            User u = session.load(User.class, uid);
            Paper p = session.load(Paper.class, pid);
            up.setUser(u);
            up.setPaper(p);
            up.setOrder(order);
            up.setIsCorr(isCorr);
            session.save(up);
            //session.createSQLQuery("INSERT INTO user_paper (user_id,paper_id,) ").executeUpdate();

            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delUserPaper(Integer uid, Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            session.createQuery("delete from UserPaper up where up.user.userId="+uid+" and up.paper.paperId="+pid).executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
