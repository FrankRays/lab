package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import shu.lab.dao.impl.ProDaoImpl;
import shu.lab.entity.Project;
import shu.lab.util.StaticParam;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Jimmy on 2016/8/2.
 */
public class ProAction extends ActionSupport implements ModelDriven<Project> {
    /*Model Object*/
    Project pro = new Project();

    /*variables that used to transfer args between front and backend*/
    String startTime;
    String endTime;
    Integer directorId;
    String directors;
    boolean status;

    private ProDaoImpl pdi = new ProDaoImpl();

    /** get and set method, makes possible to access variable*/
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public boolean isStatus() {
        return status;
    }

    /** implement ModelDriven */
    public Project getModel() {
        return pro;
    }

    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String addPro(){

        Timestamp start = Timestamp.valueOf(startTime+" 00:00:00.0");
        Timestamp end = Timestamp.valueOf(endTime+" 00:00:00.0");
        pro.setStartDate(start);
        pro.setEndDate(end);

        boolean isModify = (pro.getProId() != null);

        System.out.println("isModify = " + isModify);
        System.out.println("pro.toString() = " + pro.toString());

        Integer pid = pdi.addProject(pro);
        JSONObject directorsMap = JSONObject.fromObject(directors);
        List dirList = (List) directorsMap.get("list");
        List fields = (List) directorsMap.get("fields");
        if ((! pdi.modifyDirectors(pid, dirList) || !pdi.modifyFields(fields, pid)) && !isModify){
            delPro();
            status = false;
        } else {
            status = true;
        }
        return SUCCESS;
    }

    public String delPro(){
        System.out.println("ProAction.delPro");
        pdi.delProject(pro.getProId());
        status = true;
        return SUCCESS;
    }
    public String addDirectors(){
        if (directorId != null){
           pdi.addDelProDirectors(pro.getProId(), directorId, StaticParam.ADD);
        } else if (directors != null){
            pdi.addDelExtraDirectors(pro.getProId(), directors, StaticParam.ADD);
        }
        return SUCCESS;
    }
    public String delDirectirs(){
        if (directorId != null){
            pdi.addDelProDirectors(pro.getProId(), directorId, StaticParam.DELETE);
        } else if (directors != null){
            pdi.addDelExtraDirectors(pro.getProId(), directors, StaticParam.DELETE);
        }
        return SUCCESS;
    }
}
