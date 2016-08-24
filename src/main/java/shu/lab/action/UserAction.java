package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import shu.lab.dao.impl.AuthDaoImpl;
import shu.lab.dao.impl.GroupDaoImpl;
import shu.lab.dao.impl.UserDaoImpl;
import shu.lab.entity.User;
import shu.lab.util.StaticParam;

/**
 * Created by Jimmy on 2016/8/4.
 */
public class UserAction extends ActionSupport implements ModelDriven {
    private User user = new User();

    public Object getModel() {
        return user;
    }

    /** add a user */
    public String addUser(){
        System.out.println("user.toString() = " + user.toString());
        new UserDaoImpl().addUser(user);

        return SUCCESS;
    }

    /** delete a user */
    public String delUser(){
        System.out.println("user.toString() = " + user.toString());
        new UserDaoImpl().delUser(user.getUserId());
        return SUCCESS;
    }
}
