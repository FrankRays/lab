package shu.lab.dao;

import shu.lab.entity.Groups;

import java.util.List;


/**
 * Created by Jimmy on 2016/7/24.
 */
public interface GroupDao {

    /** Add a group with a string group name */
    boolean addGroup(Groups groups);

    /** delete a group by group id */
    boolean delGroup(Integer gid);

    Groups getGroupById(Integer gid);

    /** change group name */
    boolean changeGroupName(Integer gid, String name);

    /**
     * change group leader
     * @param gid Integer group id
     * @param lid user's id that going to be the leader
     * */
    boolean changeLeader(Integer gid, Integer lid);

    /**
     * 增加或者删除组员
     * @param gid 组号
     * @param uid 将要添加或者删除的 用户ID
     * @param type 标识 是删除还是添加操作（StaticParam.ADD or StaticParam.DELETE）
     * */
    boolean addDelMember(Integer gid, Integer uid, Integer type);

    /** 获取数据库中所有组员信息 */
    List<Groups> getAllGroup();

    List getGroupsByUserId(Integer uid);
}
