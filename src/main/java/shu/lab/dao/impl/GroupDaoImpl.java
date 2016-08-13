package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import shu.lab.dao.GroupDao;
import shu.lab.entity.Groups;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class GroupDaoImpl implements GroupDao {
    public boolean addGroup(String gName) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            session.createSQLQuery("INSERT INTO `groups` (group_name) VALUES ('"+gName+"')").executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean delGroup(Integer gid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("DELETE FROM `groups` WHERE group_id="+gid);
            return (q.executeUpdate() > 0);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean changeGroupName(Integer gid, String name) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("UPDATE `groups` SET group_name='"+name+"' WHERE group_id="+gid);
            return q.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean changeLeader(Integer gid, Integer lid) {
        Integer status = 0;
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("UPDATE `groups` SET leader="+lid+" WHERE group_id="+gid);
            status = q.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (status > 0);
    }

    public boolean addDelMember(Integer gid, Integer uid, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            if (type.equals(StaticParam.ADD)){
                return session.createSQLQuery("INSERT INTO `group_user` (user_id, group_id) VALUES ("+gid+", "+uid+")")
                        .executeUpdate() > 0;
            } else if (type.equals(StaticParam.DELETE)){
                return session.createSQLQuery("DELETE FROM `group_user` WHERE group_id="+gid+" AND user_id="+uid)
                        .executeUpdate() > 0;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public List<Groups> getAllGroup() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("from Groups ").list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
