package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import shu.lab.dao.impl.AuthDaoImpl;
import shu.lab.dao.impl.GroupDaoImpl;
import shu.lab.entity.Groups;
import shu.lab.entity.User;
import shu.lab.util.StaticParam;

/**
 * Created by Jimmy on 2016/8/4.
 */
public class AuthAction extends ActionSupport implements ModelDriven {
    private String  moduleName;
    private String  status = "error";
    private Integer groupId;
    private Integer leaderId;
    private String  groupName;
    private Integer moduleId;
    private String userInfo;
    User user = new User();
    Groups groups = new Groups();

    GroupDaoImpl gdi = new GroupDaoImpl();
    AuthDaoImpl adi = new AuthDaoImpl();

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getStatus() {
        return status;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public User getModel() {
        return user;
    }

    /** Add a group, 需要 String GroupName */
    public String addGroup(){
        if(gdi.addGroup(groups)){
            status = "success";
        }
        return SUCCESS;
    }
    /** change the group name */
    public String chgName(){
        if(gdi.changeGroupName(groupId, groupName)){
            status = "success";
        }
        return SUCCESS;
    }
    /** Action to change group leader */
    public String chgLeader() {
        if (gdi.changeLeader(groupId, leaderId)){
            status = "success";
        }
        return SUCCESS;
    }
    /** delete a group by group id */
    public String delGroup(){
        if (gdi.delGroup(groupId)){
            status = "success";
        }
        return SUCCESS;
    }
    /** add a module permission to this group */
    public String addPermission(){
        if (adi.addDelPermission(groupId, moduleId, StaticParam.ADD)){
            status = "success";
        }
        return SUCCESS;
    }
    /** remove a module permission from this group*/
    public String rmPermission(){
        if (adi.addDelPermission(groupId, moduleId, StaticParam.DELETE)){
            status = "success";
        }
        return SUCCESS;
    }
    /** 添加一个功能模块描述 */
    public String addModule(){
        if (moduleName != null && !moduleName.equals("")){
            if (adi.addModule(moduleName) > 0){
                this.status = "success";
            } else {
                this.status = "something error occurred";
            }
        } else {
            this.status = "please input the name of a module";
        }
        return SUCCESS;
    }

    /** 删除一个功能模块 */
    public String delModule(){
        if (moduleName != null && !moduleName.equals("")){
            if (adi.delModule(moduleName) > 0){
                this.status = "success";
            } else {
                this.status = "something error occurred";
            }
        } else {
            this.status = "please input the name of a module";
        }
        return SUCCESS;
    }

}
