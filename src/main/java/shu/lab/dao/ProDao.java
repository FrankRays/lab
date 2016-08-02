package shu.lab.dao;

import shu.lab.entity.Project;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface ProDao {
    List<Project> getLatestTenProject();
    List<Project> getFamousTenProject();
    List<Project> getProjectByUserId(Integer uid, Integer page, Integer num);
    List<Project> getAllProjectByUserId(Integer uid);
    boolean addProject(Project pro);
    void delProject(Integer projectId);
    Integer addDelFieldPro(Integer fid, Integer pid, Integer type);
}
