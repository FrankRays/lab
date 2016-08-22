package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.UserDao;
import shu.lab.entity.*;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

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
            Query q = session.createSQLQuery("SELECT * FROM `user` WHERE username='"+name+"'").addEntity(User.class);

            return q.list().size() <= 0 ? null:(User) q.list().get(0);

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
            return session.createQuery("select username from User").list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            /** save user */
            session.saveOrUpdate(user);
            /** delete old information from field_user */
            session.createSQLQuery("DELETE FROM field_user WHERE user_id="+user.getUserId()).executeUpdate();
            /** save user field again */
            if (user.getFieldUsers() != null){
                for (Object obj:user.getFieldUsers()) {
                    String str = (String) obj;
                    Field f = (Field) session.createSQLQuery("SELECT * FROM field WHERE field_descr='"+str+"'")
                            .addEntity(Field.class).uniqueResult();
                    if (f != null){
                        session.createSQLQuery("INSERT INTO field_user (field_id, user_id) VALUES ( "+f.getFieldId()+", "+user.getUserId()+")")
                                .executeUpdate();
                    }
                }
            }

            session.createSQLQuery("DELETE FROM group_user WHERE user_id="+user.getUserId()).executeUpdate();
            /** save user group */
            if (user.getGroupUsers() != null){
                for (Object obj:user.getGroupUsers()) {
                    String str = (String) obj;
                    Groups g = (Groups) session.createSQLQuery("SELECT * FROM groups WHERE group_name='"+str+"'")
                            .addEntity(Groups.class).uniqueResult();
                    if (g!= null){
                        session.createSQLQuery("INSERT INTO group_user (user_id, group_id) VALUES ( "+user.getUserId()+", "+g.getGroupId()+")")
                                .executeUpdate();
                    }
                }
            }
            ts.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return false;
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
        }finally {
            session.close();
        }
    }

    public Integer addDelUserField(Integer fid, Integer uid, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            if (type.equals(StaticParam.ADD)){
                return session.createSQLQuery("INSERT INTO  field_user (field_id, user_id) VALUES ("+fid+", "+uid+")")
                        .executeUpdate();
            } else {
                return session.createSQLQuery("DELETE FROM field_user WHERE field_id="+fid+" AND user_id="+uid)
                        .executeUpdate();
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return 0;
    }
}
