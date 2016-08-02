package shu.lab.dao;

import shu.lab.entity.User;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/10.
 */
public interface UserDao {
    User getUserById(Integer id);
    User getUserByName(String name);
    List<User> getUserList(Integer page, Integer num);
    List<User> getAllUser();
    boolean addUser(User user);
    void delUser(Integer uid);
    void addUserField(Integer fid, Integer uid);
    void delUserField(Integer fid, Integer uid);
}
