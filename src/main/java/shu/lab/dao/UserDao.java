package shu.lab.dao;

import shu.lab.entity.User;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/10.
 */
public interface UserDao {
    User getUserById(Integer id);
    List<User> getUserList(Integer page, Integer num);
    boolean addUser(User user);
    void delUser(Integer uid);
}
