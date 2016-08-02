package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import shu.lab.dao.impl.PaperDaoImpl;
import shu.lab.entity.Paper;
import shu.lab.util.StaticParam;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class PaperUploadAction extends ActionSupport implements ModelDriven<Paper>, ServletContextAware {
    private Paper paper = new Paper();
    private ServletContext context;
    private Integer[] authors;
    private Integer[] corrAuthors;
    private File file;
    private String fileFileName;

    public void setAuthors(Integer[] authors) {
        this.authors = authors;
    }

    public void setCorrAuthors(Integer[] corrAuthors) {
        this.corrAuthors = corrAuthors;
    }

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

    /*acquire parameter with ModelDriven*/
    public Paper getModel() {
        return paper;
    }

    /*used to get the real relative path*/
    public void setServletContext(ServletContext context) {
        this.context = context;
    }

    @Override
    public String execute() throws Exception {

        String realPath = context.getRealPath(StaticParam.PAPER_FOLDER);
        System.out.println("realPath = " + realPath);

        //Integer position = fileFileName.lastIndexOf(".");
        //String extension = position == -1 ? "":fileFileName.substring(position);
        //System.out.println("extension = " + extension);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateInfo = df.format(new Date());
        //System.out.println("dateInfo = " + dateInfo);

        String fileName = dateInfo + fileFileName;
        System.out.println("fileName = " + fileName);

        System.out.println("authors = " + Arrays.toString(authors));
        try {
            /*copy file to server*/
            File destFile  = new File(realPath, fileName);
            FileUtils.copyFile(file, destFile);

            /*save file path to paper*/
            paper.setSourceUrl(fileName);

            new PaperDaoImpl().addPaper(paper,authors,corrAuthors);

        } catch (Exception e){
            e.printStackTrace();
        }
        return super.execute();
    }
}
