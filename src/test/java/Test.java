import junit.framework.TestCase;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.impl.PaperDaoImpl;
import shu.lab.entity.User;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void testCase2(){

            //new FieldDaoImpl().addField("计算与建模");
            //new FieldDaoImpl().delField(7);
            List<Integer> authors = new ArrayList<Integer>();
            authors.add(2);
            authors.add(1);
            authors.add(3);
            List<Integer> corrAuthors = new ArrayList<Integer>();
            corrAuthors.add(5);
            corrAuthors.add(4);
            //new PaperDaoImpl().updPaperMember(authors, corrAuthors, "liuxiang", "make", "关于神经网络的深度学习", "aaa", "A", "2016", "第5卷", "第8期", 25, 76,"locat");

    }
    public void test3(){

        //System.out.println(new PaperDaoImpl().getAllPaperByUserId(1));
        //new PaperDaoImpl().addPaperField(2, 1);
        //new PaperDaoImpl().addDelExtraMember(2,"sss", StaticParam.ADD, 1);
        //new PaperDaoImpl().addDelExtraMember(2,"make", StaticParam.DELETE, 1);
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            BigInteger bigInteger = (BigInteger) session.createSQLQuery("SELECT count(*) FROM `user` ")
                    .uniqueResult();

            System.out.println("a = " + bigInteger.intValue());

            Integer ord = (Integer) session.createSQLQuery("SELECT up_order FROM user_paper " +
                    "WHERE paper_id=2 AND user_id=2 AND is_corr=0").uniqueResult();
            System.out.println("ord = " + ord);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void test4(){
        /*
        String s = "mark,ttt";
        System.out.println(s.indexOf("ttt"));

        System.out.println(s.substring(0,s.indexOf("ttt")-1));*/
        /*
        String s = "2016-11-12";
        String e = "2016-12-13";
        Timestamp ts = Timestamp.valueOf(s+" 00:00:00.00");
        System.out.println("ts.toString() = " + ts.toString());*/

        /** 测试MAP 转 JSON 再转 JSON格式的字符串 */
        Map map = new HashMap();
        List list = new ArrayList();
        User u1 = new User();
        User u2 = new User();

        u1.setUsername("jimmy");
        u2.setUsername("marvin");
        list.add(u1);
        list.add(u2);
        map.put("list", list);
        JSONObject json = JSONObject.fromObject(map);
        System.out.println("json.toString() = " + json.toString());
    }
}
