package shu.lab.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import shu.lab.dao.ProDao;
import shu.lab.entity.Field;
import shu.lab.entity.Paper;
import shu.lab.entity.Project;
import shu.lab.util.HibernateUtil;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class ProDaoImpl implements ProDao {
    public List<Project> getLatestTenProject() {
        return null;
    }

    public List<Project> getFamousTenProject() {
        return null;
    }

    public List<Project> getProjectByUserId(Integer uid, Integer page, Integer num) {
        return null;
    }

    public List<Project> getAllProjectByUserId(Integer uid) {
        return null;
    }

    public boolean addProject(String proName, List<Integer> directors, String extraDirectors,
                              Timestamp startDate, Timestamp endDate, String proFee,
                              String proType, String proLevel) {
        return false;
    }

    public void delProject(Integer projectId) {

    }

    public void addProField(Integer fid, Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            Project p = session.load(Project.class, pid);
            Field f = session.load(Field.class, fid);
            p.getFieldProjects().add(f);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delProField(Integer fid, Integer pid) {
        HibernateUtil util = new HibernateUtil();
        Session session = util.openSession();
        try {
            Transaction ts = session.beginTransaction();
            ts.begin();
            Project p = session.load(Project.class, pid);
            Field f = session.load(Field.class, fid);
            p.getFieldProjects().remove(f);
            ts.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
