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

    GroupDaoImpl gdi = new GroupDaoImpl();

    public String getStatus() {
        return status;
    }

    public Object getModel() {
        return groups;
    }

    public void addGroup(){
        /** 如果提供groupId 且这个ID在数据库中已经存在，就会更新这个group*/
        gdi.addGroup(groups);
    }

    /** 需要 Integer groupId*/
    public void delGroup(){

        if (gdi.delGroup(groups.getGroupId())){
            status = "success";
        } else {
            status = "failed";
        }
    }
}
