package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.UserDao;
import shu.lab.entity.Field;
import shu.lab.entity.User;
import shu.lab.util.HibernateUtil;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/10.
 */
public class UserDaoImpl implements UserDao {
    public User getUserById(Integer id) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        try{
            return session.load(User.class,id);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByName(String name) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        try{
            Query q = session.createQuery("from User where username=?");
            q.setParameter(0, name);
            return (User) q.list().get(0);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserList(Integer page, Integer num) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            return session.createQuery("from User")
                    .setFirstResult((page-1)*num).setMaxResults(num).list();

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAllUser() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {

            return session.createQuery("from User").list();

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean addUser(User user) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        boolean status = false;
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            session.save(user);
            status = true;
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }

        return status;
    }

    public void delUser(Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            User u = session.load(User.class, uid);
            session.delete(u);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addUserField(Integer fid, Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            User u = session.load(User.class, uid);
            Field f = session.load(Field.class, fid);
            u.getFieldUsers().add(f);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delUserField(Integer fid, Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            User u = session.load(User.class, uid);
            Field f = session.load(Field.class, fid);
            u.getFieldUsers().remove(f);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
