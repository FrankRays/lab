package shu.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.FieldDao;
import shu.lab.entity.Field;
import shu.lab.util.HibernateUtil;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class FieldDaoImpl implements FieldDao {

    public void addField(String descr) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        Transaction ts = session.beginTransaction();
        try {
            Field f = new Field();
            f.setFieldDescr(descr);
            f.setCount(0);

            ts.begin();
            session.save(f);
            ts.commit();
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void delField(Integer fid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();

        Transaction ts = session.beginTransaction();
        try {
            ts.begin();
            Field f = session.load(Field.class, fid);
            session.delete(f);
            ts.commit();
        } catch (Exception e){
            ts.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List getAllField() {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createQuery("select fieldDescr from Field").list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List getFieldsById(Integer uid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("SELECT f.field_descr FROM field_user fu, field f WHERE fu.field_id=f.field_id AND fu.user_id = "+uid).list();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List getFieldsByPaperId(Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("SELECT f.field_descr FROM field_paper fp, field f WHERE fp.field_id=f.field_id AND fp.paper_id = "+pid).list();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List getFieldsByProId(Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            return session.createSQLQuery("SELECT f.field_descr FROM field_project fp, field f WHERE fp.field_id=f.field_id AND fp.project_id = "+pid).list();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
