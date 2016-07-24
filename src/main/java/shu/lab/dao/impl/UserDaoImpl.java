package shu.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.UserDao;
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
        Transaction ts;
        User u = null;
        try{
            ts = session.beginTransaction();
            User u2 = session.load(User.class,id);
            if (u2 != null){
                u = new User();
                u.setUsername(u2.getUsername());
                u.setPassword(u2.getPassword());
                u.setPhotoUrl(u2.getPhotoUrl());
                u.setUserId(u2.getUserId());
                u.setUserType(u2.getUserType());
            }
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return u;
    }

    public List<User> getUserList(Integer page, Integer num) {
        return null;
    }

    public boolean addUser(User user) {
        return false;
    }

    public void delUser(Integer uid) {

    }
}
