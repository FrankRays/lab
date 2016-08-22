package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.web.context.ServletContextAware;
import shu.lab.dao.impl.PaperDaoImpl;
import shu.lab.entity.Paper;
import shu.lab.util.DelFile;
import shu.lab.util.StaticParam;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class PaperAction extends ActionSupport implements ServletContextAware, ModelDriven<Paper> {

    private Paper paper = new Paper();
    private boolean status;
    private String authors;
    private ServletContext context;
    private File file;
    private String fileFileName;

    private PaperDaoImpl pdi = new PaperDaoImpl();

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public Paper getModel() {
        return paper;
    }

    public boolean isStatus() {
        return status;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /** IOC 方式 注入 servletContext，用以获取项目真实路径*/
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    public String addPaper(){
        /**get the real path*/
        String realPath = context.getRealPath(StaticParam.PAPER_FOLDER);
        //System.out.println("realPath = " + realPath);
        //Integer position = fileFileName.lastIndexOf(".");
        //String extension = position == -1 ? "":fileFileName.substring(position);
        //System.out.println("extension = " + extension);

        String fileName;
        boolean isModify = (paper.getPaperId() != null);

        /** 如果是上传了附件的，获取附件名字 */
        if (file != null){
            /** add date info to the fileName */
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
            String dateInfo = df.format(new Date());
            fileName = dateInfo + fileFileName;
            /**add file path to paper*/
            paper.setSourceUrl(fileName);
        }
        /** 如果是没有上传附件，且是有paperId（即表示是更新Paper操作）的，保留原来的附件信息 */
        else if (paper.getPaperId() != null){
            fileName = pdi.getPaperById(paper.getPaperId()).getSourceUrl();
            paper.setSourceUrl(fileName);
        } else {
            status = false;
            return SUCCESS;
        }

        try {
            /**save paper to DB*/
            /**
             * if paperId != null and already exist in the DB,
             * it will update it instead of create a new records
             * */
            Integer pid = pdi.addPaper(paper);

            /** get authors info as a list*/
            JSONObject json = JSONObject.fromObject(authors);
            List list = (List) json.get("list");
            List fields = (List) json.get("fields");

            /** if there no fields or authors, return false */
            if (list == null || fields == null){
                status = false;
                return SUCCESS;
            }

            //System.out.println("fields = " + fields.toString());
            /** 如果插入作者出错，整个事件会回滚，同时，此处删除之前添加的paper*/
            if ( (!pdi.updPaperMember(pid, list) || !pdi.modifyFields(pid, fields)) && !isModify){
                pdi.delPaper(pid);
                status = false;
            } else if (file != null){
                /**copy file to server*/
                System.out.println("realPath = " + realPath +"\\"+ fileName);
                File destFile  = new File(realPath, fileName);
                FileUtils.copyFile(file, destFile);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String delPaper() {

        Paper p = pdi.getPaperById(paper.getPaperId());

        /** delete file from server filesystem */
        new DelFile().delFile(context.getRealPath(StaticParam.PAPER_FOLDER)+"/"+p.getSourceUrl());

        /** delete paper tuple from database */
        pdi.delPaper(paper.getPaperId());
        status = true;
        return SUCCESS;
    }
}
