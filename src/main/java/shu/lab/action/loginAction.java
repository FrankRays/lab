package shu.lab.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.UserDaoImpl;
import shu.lab.entity.User;

import java.util.Map;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class LoginAction extends ActionSupport {
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
        System.out.println("LoginAction.login");
        User u = new UserDaoImpl().getUserByName(username);
        System.out.println(u);
        status = u.getPassword().equals(password);
        if (status){
            Map session = ActionContext.getContext().getSession();
            session.put("user", u);

            String name = u.getUsername();
            if(u.getEngName() != null){
                name += "(" + u.getEngName() + ")";
            }

            String str = "<li class=\"dropdown\">" +
                    "                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" +
                                                name+
                    "                        <b class=\"caret\"></b>" +
                    "                    </a>" +
                    "                    <ul class=\"dropdown-menu\">" +
                    "                        <li><a href=\"/lab/view?page=userCenter\">个人中心</a></li>" +
                    "                        <li class=\"divider\"></li>" +
                    "                        <li><a href=\"#\" id='logout'>退出</a></li>" +
                    "                    </ul>" +
                    "                </li>";
            username = str;
        }
        return SUCCESS;
    }
    public String logout(){
        System.out.println("LoginAction.logout");
        Map session = ActionContext.getContext().getSession();
        status = false;
        if (session != null && session.get("user") != null){
            session.remove("user");
            username = "<li id='showLogin'><a href='#'>请登录</a></li>";
            status = true;
        }
        return SUCCESS;
    }
    public String currentUser(){
        System.out.println("LoginAction.currentUser");
        Map session = ActionContext.getContext().getSession();
        User u = (User)session.get("user");
        if (u != null){

            String name = u.getUsername();
            if(u.getEngName() != null){
                name += "(" + u.getEngName() + ")";
            }

            String str = "<li class=\"dropdown\">" +
                    "                    <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" +
                                                name +
                    "                        <b class=\"caret\"></b>" +
                    "                    </a>" +
                    "                    <ul class=\"dropdown-menu\">" +
                    "                        <li><a href=\"#\">个人中心</a></li>" +
                    "                        <li class=\"divider\"></li>" +
                    "                        <li><a href=\"#\">退出</a></li>" +
                    "                    </ul>" +
                    "                </li>";

            username = str;
        } else{
            username = "<li id='showLogin'><a href='#'>请登录</a></li>";
        }

        return SUCCESS;
    }
}
