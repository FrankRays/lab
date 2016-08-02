package shu.lab.dao;

import shu.lab.entity.Group;

import java.util.List;


/**
 * Created by Jimmy on 2016/7/24.
 */
public interface GroupDao {
    void addGroup(Group g);
    void delGroup(Integer gid);
    void changeGroupName(Integer gid, String name);
    boolean changeLeader(Integer gid, Integer lid);
    List<Group> getAllGroup();
}
