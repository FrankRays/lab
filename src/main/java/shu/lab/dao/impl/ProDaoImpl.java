package shu.lab.dao.impl;

import shu.lab.dao.ProDao;
import shu.lab.entity.Project;

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

    public boolean addProject(String proName, List<Integer> directors, String extraDirectors, Timestamp startDate, Timestamp endDate, String proFee, String proType, String proLevel) {
        return false;
    }

    public void delProject(Integer projectId) {

    }
}
