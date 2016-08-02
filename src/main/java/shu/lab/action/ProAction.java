package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import shu.lab.dao.impl.ProDaoImpl;
import shu.lab.entity.Project;

import java.sql.Timestamp;

/**
 * Created by Jimmy on 2016/8/2.
 */
public class ProAction extends ActionSupport implements ModelDriven<Project> {
    /*Model Object*/
    Project pro = new Project();

    /*variables that used to transfer args between front and backend*/
    String startTime;
    String endTime;
    boolean status;

    /* get and set method, makes possible to access variable*/
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isStatus() {
        return status;
    }

    /*implement ModelDriven*/
    public Project getModel() {
        return pro;
    }

    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String addPro(){
        Timestamp start = Timestamp.valueOf(startTime="00:00:00.0");
        Timestamp end = Timestamp.valueOf(endTime="00:00:00.0");
        pro.setStartDate(start);
        pro.setEndDate(end);
        new ProDaoImpl().addProject(pro);
        return SUCCESS;
    }

    public String delPro(){

        return SUCCESS;
    }

}
