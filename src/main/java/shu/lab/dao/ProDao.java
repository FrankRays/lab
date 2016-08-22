package shu.lab.dao;

import shu.lab.entity.Project;

import java.security.Timestamp;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface ProDao {
    Project getProById(Integer pid);
    List<Project> getLatestTenProject();
    List<Project> getAllPro(Integer page, Integer num);
    List<Project> getFamousTenProject();
    List<Project> getProjectByUserId(Integer uid, Integer page, Integer num);
    List<Project> getAllProjectByUserId(Integer uid);
    Integer addProject(Project pro);
    void delProject(Integer projectId);
    boolean modifyFields(List fields, Integer pid);
    boolean modifyDirectors(Integer pId, List directors);
    List getInnerDirectorsById(Integer pid);
    Integer addDelProDirectors(Integer pid, Integer uid, Integer type);
    Integer addDelExtraDirectors(Integer pid, String extraAuthor, Integer type);
}
