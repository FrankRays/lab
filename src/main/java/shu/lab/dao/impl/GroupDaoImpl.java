package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import shu.lab.dao.GroupDao;
import shu.lab.entity.Groups;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class GroupDaoImpl implements GroupDao {

    public boolean addGroup(Groups groups) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            session.saveOrUpdate(groups);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
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
        }finally {
            session.close();
        }
        return false;
    }

    public Groups getGroupById(Integer gid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.load(Groups.class, gid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean changeGroupName(Integer gid, String name) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Query q = session.createSQLQuery("UPDATE `groups` SET group_name='"+name+"' WHERE group_id="+gid);
            return q.executeUpdate() > 0;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    public boolean changeLeader(Integer gid, Integer lid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        Integer status = 0;

        try {
            Query q = session.createSQLQuery("UPDATE `groups` SET leader="+lid+" WHERE group_id="+gid);
            status = q.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
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
        }finally {
            session.close();
        }
        return false;
    }

    public List<Groups> getAllGroup() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("select groupName from Groups ").list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List getGroupsByUserId(Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("SELECT group_name FROM groups g, group_user gu WHERE g.group_id=gu.group_id AND gu.user_id="+uid).list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
