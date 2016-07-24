import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.entity.User;
import shu.lab.util.HibernateUtil;

/**
 * Created by Jimmy on 2016/7/10.
 */
public class Test extends TestCase {
    public void testCase(){

        Session session = null;
        Transaction transaction = null;
        HibernateUtil util = new HibernateUtil();
        try {
            session = util.openSession();
            transaction = session.beginTransaction();

            session = new HibernateUtil().openSession();
            Transaction ts = session.beginTransaction();
            ts.begin();
            User u = new User();
            u.setUsername("jimmy");
            u.setPassword("123456");
            session.save(u);
            ts.commit();

        } catch (Exception e) {

            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            util.closeSession(session);
        }
    }
}
