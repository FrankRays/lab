package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.web.context.ServletContextAware;
import shu.lab.dao.impl.PaperDaoImpl;
import shu.lab.entity.Paper;
import shu.lab.util.DelFile;
import shu.lab.util.StaticParam;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;


/**
 * Created by Jimmy on 2016/7/29.
 */
public class paperAction extends ActionSupport implements ServletContextAware {

    private boolean status;
    private Integer paperId;
    private String authors;
    ServletContext context;

    public boolean isStatus() {
        return status;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /** IOC 方式 注入 servletContext，用以获取项目真实路径*/
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    public String delPaper() {

        Paper p = new PaperDaoImpl().getPaperById(paperId);

        /*delete file from server filesystem*/
        new DelFile().delFile(context.getRealPath(StaticParam.PAPER_FOLDER)+"/"+p.getSourceUrl());

        /*delete paper tuple from database*/
        new PaperDaoImpl().delPaper(paperId);
        status = true;
        return SUCCESS;
    }
    public String updPaperMenber(){

        JSONObject jasonObject = JSONObject.fromObject(authors);
        Map map = jasonObject;
        List list = (List) map.get("list");
        new PaperDaoImpl().updPaperMember(paperId,list);
        return SUCCESS;
    }
/*
    public String addMember() {
        if (userId != null){
            new PaperDaoImpl().addDelPaperMember(paperId, userId, StaticParam.ADD, isCorr);
        } else if (extraAuthor !=null){
            new PaperDaoImpl().addDelExtraMember(paperId, extraAuthor, StaticParam.ADD, isCorr);
        }
        return SUCCESS;
    }
    public String delMember() {
        if (userId != null){
            new PaperDaoImpl().addDelPaperMember(paperId, userId, StaticParam.DELETE, isCorr);
        } else if (extraAuthor != null){
            new PaperDaoImpl().addDelExtraMember(paperId, extraAuthor, StaticParam.DELETE,isCorr);
        }
        return SUCCESS;
    }
    */
}
