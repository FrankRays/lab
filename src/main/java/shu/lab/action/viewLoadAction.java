package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.UserDaoImpl;
import shu.lab.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class viewLoadAction extends ActionSupport {
    private String page;
    private List users = new ArrayList();
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List getUsers() {
        return users;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("viewLoadAction.execute");
        //System.out.println(page);
        if (page.equals("index")){
            List list = new UserDaoImpl().getAllUser();
            for (Object user: list) {
                User u = (User)user;
                users.add("jimmy");
            }
        }
        return super.execute();
    }
}
