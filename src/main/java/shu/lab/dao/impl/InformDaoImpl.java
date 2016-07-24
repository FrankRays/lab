package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.InformDao;
import shu.lab.entity.Inform;
import shu.lab.entity.User;
import shu.lab.util.HibernateUtil;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class InformDaoImpl implements InformDao {
    public List<Inform> getInformsBySenderId(Integer sid,Integer page,Integer num) {

        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        try{
            Query query = session.createQuery("from Inform where userBySenderId.userId=?");
            query.setParameter(0,sid);
            query.setFirstResult((page-1)*num);
            query.setMaxResults(num);

            return query.list();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return null;
    }

    public List<Inform> getInformsByReceiverId(Integer rid, Integer page, Integer num) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        try{
            Query query = session.createQuery("from Inform where userByReceiverId.userId=?");
            query.setParameter(0,rid);
            query.setFirstResult((page-1)*num);
            query.setMaxResults(num);

            return query.list();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //session.close();
        }

        return null;
    }

    public boolean addInform(Integer senderId, Integer receiverId, String content) {
        boolean status = false;
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        try{

            Transaction ts = session.beginTransaction();
            Inform inform = new Inform();
            User sender = session.load(User.class,senderId);
            User receiver = session.load(User.class, receiverId);
            inform.setUserBySenderId(sender);
            inform.setUserByReceiverId(receiver);
            inform.setContent(content);
            inform.setSendDate(new Timestamp(System.currentTimeMillis()));
            inform.setInformType("unread");
            session.save(inform);
            status = true;
            ts.commit();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }

        return status;
    }

    public void modifyStatus(Integer informId, String status) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try{
            Transaction ts = session.beginTransaction();
            Inform inform =  session.load(Inform.class, informId);
            inform.setInformType(status);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
