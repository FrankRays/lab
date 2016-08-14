package shu.lab.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.UserDaoImpl;
import shu.lab.entity.User;

import java.util.Map;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class loginAction extends ActionSupport {
    private String username;
    private String password;
    private boolean status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public String login(){
        System.out.println("loginAction.login");
        User u = new UserDaoImpl().getUserByName(username);
        System.out.println(u);
        status = u.getPassword().equals(password);
        if (status){
            Map session = ActionContext.getContext().getSession();
            session.put("user", u);
        }
        return SUCCESS;
    }
    public String logout(){
        System.out.println("loginAction.logout");
        Map session = ActionContext.getContext().getSession();
        status = false;
        if (session != null && session.get("user") != null){
            session.remove("user");
            status = true;
        }
        return SUCCESS;
    }
    public String currentUser(){
        System.out.println("loginAction.currentUser");
        Map session = ActionContext.getContext().getSession();
        User u = (User)session.get("user");
        this.username = u.getUsername();
        if(u.getEngName() != null){
            this.username += "(" + u.getEngName() + ")";
        }
        return SUCCESS;
    }
}
