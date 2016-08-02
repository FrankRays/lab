package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.GroupDao;
import shu.lab.entity.Group;
import shu.lab.util.HibernateUtil;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class GroupDaoImpl implements GroupDao {
    public void addGroup(Group g) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            session.save(g);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delGroup(Integer gid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("DELETE FROM group WHERE group_id=?");
            q.setParameter(1, gid);
            q.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void changeGroupName(Integer gid, String name) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("UPDATE group SET group_name=? WHERE group_id=?");
            q.setParameter(1, name);
            q.setParameter(2,gid);
            q.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean changeLeader(Integer gid, Integer lid) {
        Integer status = 0;
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("UPDATE group SET leader=? WHERE group_id=?");
            q.setParameter(1, lid);
            q.setParameter(2,gid);
            status = q.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (status > 0);
    }

    public List<Group> getAllGroup() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("from Group ").list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
