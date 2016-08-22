package shu.lab.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import shu.lab.dao.AuthDao;
import shu.lab.util.HibernateUtil;
import shu.lab.util.StaticParam;

/**
 * Created by Jimmy on 2016/8/4.
 */
public class AuthDaoImpl implements AuthDao {

    public boolean checkPermission(Integer mid, Integer gid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        try {
            Query q = session.createSQLQuery("SELECT * FROM group_auth WHERE group_id="+gid+" AND auth_id="+mid);
            return (q.list() != null);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    public Integer addModule(String moduleName) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("INSERT INTO authority (auth_descr) VALUES ('"+moduleName+"')").executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return 0;
    }

    public Integer delModule(String moduleName) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("DELETE FROM authority WHERE auth_descr='"+moduleName+"'").executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return 0;
    }

    public boolean addDelPermission(Integer groupId, Integer moduleId, Integer type) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Integer stat = 0;
            if (type.equals(StaticParam.ADD)){

                stat = session.createSQLQuery("INSERT INTO group_auth (group_id, auth_id) VALUES ("+groupId+", "+moduleId+")")
                .executeUpdate();
            } else {
                stat = session.createSQLQuery("DELETE FROM group_auth WHERE group_id="+groupId+" AND auth_id="+moduleId)
                .executeUpdate();
            }
            return (stat > 0);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
