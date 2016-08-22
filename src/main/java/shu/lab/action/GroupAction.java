package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import shu.lab.dao.impl.GroupDaoImpl;
import shu.lab.entity.Groups;

/**
 * Created by Jimmy on 2016/8/22.
 */
public class GroupAction extends ActionSupport implements ModelDriven {
    private Groups groups = new Groups();
    private String status;

    public String getStatus() {
        return status;
    }

    public Object getModel() {
        return groups;
    }

    public void addGroup(){
        new GroupDaoImpl().addGroup(groups);

    }
}
