package shu.lab.action;

/**
 * Created by Jimmy on 2016/7/10.
 */
import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.UserDaoImpl;
import shu.lab.entity.User;


public class TestAction extends ActionSupport {

    /**
     * TestAction
     */
    private static final long serialVersionUID = 1L;
    private String name;

    public String testMethod() {
        if (name.equals(null) || name.equals("")) {
            return ERROR;
        } else {
            User u = new UserDaoImpl().getUserById(1);
            System.out.println(u.toString());
            if (name.equals(u.getUsername())){
                return SUCCESS;
            } else {
                name = "user not exist";
                return ERROR;
            }

        }
    }
    @Override
    public String execute() throws Exception {
        System.out.println("TestAction execute...");
        return super.execute();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

