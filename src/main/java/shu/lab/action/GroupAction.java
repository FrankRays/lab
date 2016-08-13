package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.AuthDaoImpl;
import shu.lab.dao.impl.GroupDaoImpl;

/**
 * Created by Jimmy on 2016/8/4.
 */
public class GroupAction extends ActionSupport {
    private Integer groupId;
    private Integer leaderId;
    private String groupName;
    private boolean status;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public boolean isStatus() {
        return status;
    }

    /** Add a group*/
    public String addGroup(){

        status = new GroupDaoImpl().addGroup(groupName);
        return SUCCESS;
    }
    /** change the group name*/
    public String chgName(){

        status = new GroupDaoImpl().changeGroupName(groupId, groupName);
        return SUCCESS;
    }
    /** Action to change group leader*/
    public String chgLeader() {
        status = new GroupDaoImpl().changeLeader(groupId, leaderId);
        return SUCCESS;
    }
    /** delete a group by group id*/
    public String delGroup(){
        status = new GroupDaoImpl().delGroup(groupId);
        return SUCCESS;
    }
    /** add a module permission to this group*/
    public String addPermission(){
        //status = new AuthDaoImpl().addDelPermission(groupId, )
        return SUCCESS;
    }
    /** remove a module permission from this group*/
    public String rmPermission(){

        return SUCCESS;
    }
}
