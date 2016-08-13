package shu.lab.dao;

/**
 * Created by Jimmy on 2016/8/4.
 */
public interface AuthDao {
    /** check whether the group has permission to access to this module*/
    boolean checkPermission(Integer mId, Integer gid);
    /** add a module*/
    Integer addModule(String moduleName);
    /** remove a module*/
    Integer delModule(String moduleName);

    boolean addDelPermission(Integer groupId, Integer moduleId, Integer type);
}
