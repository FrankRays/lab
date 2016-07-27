package shu.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.FieldDao;
import shu.lab.entity.Field;
import shu.lab.util.HibernateUtil;

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
        } finally {
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
        } finally {
            session.close();
        }
    }
}
