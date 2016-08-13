package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.AuthDaoImpl;

/**
 * Created by Jimmy on 2016/8/4.
 */
public class AuthAction extends ActionSupport {
    private String moduleName;
    private String status;

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getStatus() {
        return status;
    }

    /** 添加一个功能模块描述 */
    public String addModule(){
        if (moduleName != null && !moduleName.equals("")){
            if (new AuthDaoImpl().addModule(moduleName) > 0){
                this.status = "add module succeed";
            } else {
                this.status = "some thing error occurred";
            }
        } else {
            this.status = "please input the name of a module";
        }
        return SUCCESS;
    }

    /** 删除一个功能模块 */
    public String delModule(){
        if (moduleName != null && !moduleName.equals("")){
            if (new AuthDaoImpl().delModule(moduleName) > 0){
                this.status = "delete module succeed";
            } else {
                this.status = "some thing error occurred";
            }
        } else {
            this.status = "please input the name of a module";
        }
        return SUCCESS;
    }
}
