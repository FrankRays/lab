package shu.lab.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import shu.lab.dao.impl.FieldDaoImpl;
import shu.lab.dao.impl.GroupDaoImpl;
import shu.lab.dao.impl.PaperDaoImpl;
import shu.lab.dao.impl.ProDaoImpl;
import shu.lab.entity.Groups;
import shu.lab.entity.Paper;
import shu.lab.entity.Project;
import shu.lab.entity.User;

import java.util.*;

/**
 * Created by Jimmy on 2016/8/18.
 */
public class DisplayAction extends ActionSupport {
    private List papers = new ArrayList();
    private List pros = new ArrayList();
    private Integer paperId;
    private Integer page;
    private List fields = new ArrayList();
    private List groups = new ArrayList();
    private Map rtn = new HashMap();

    private PaperDaoImpl pdi = new PaperDaoImpl();
    private ProDaoImpl prodi = new ProDaoImpl();
    private FieldDaoImpl fdi = new FieldDaoImpl();
    private GroupDaoImpl gdi = new GroupDaoImpl();

    public Map getRtn() {
        return rtn;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    /** 首页需要的 最新论文数组与最新项目数组（Json格式） */
    public String index (){
        papers = pdi.getLatestTenPaper();

        papers = integrationAuthor(papers);

        pros = prodi.getLatestTenProject();

        pros = integrationDirectors(pros);

        rtn.clear();
        rtn.put("papers", papers);
        rtn.put("pros", pros);
        return SUCCESS;
    }

    /** 用户中心需要的 用户相关论文 与 项目 列表（Json格式） */
    public String user(){
        Map session = ActionContext.getContext().getSession();
        User u = (User) session.get("user");
        papers = pdi.getAllPaperByUserId(u.getUserId());
        pros = prodi.getAllProjectByUserId(u.getUserId());
        fields = fdi.getFieldsById(u.getUserId());
        groups = gdi.getGroupsByUserId(u.getUserId());

        papers = integrationAuthor(papers);

        pros = integrationDirectors(pros);

        rtn.clear();
        rtn.put("papers", papers);
        rtn.put("pros", pros);
        rtn.put("user", u);
        rtn.put("fields",fields);
        rtn.put("groups", groups);

        return SUCCESS;
    }

    /** 通过PaperId 获取论文详细内容 （Json格式） */
    public String paper() {
        Paper paper = pdi.getPaperById(paperId);
        String extraAuthorsStr = paper.getExtraAuthor();

        List authors = new ArrayList();

        if (extraAuthorsStr != null){
            JSONObject json = JSONObject.fromObject(extraAuthorsStr);
            List extraAuthors = (List) json.get("list");
            authors.addAll(extraAuthors);
        }

        List innerAuthors = pdi.getInnerAuthorsByPaperId(paperId);
        if (innerAuthors != null) {
            authors.addAll(innerAuthors);
        }

        List fields = fdi.getFieldsByPaperId(paperId);
        /** sort the authors by order */
        Collections.sort(authors, new Comparator<Map<Object, Object>>() {
            public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
                int map1value = (Integer) o1.get("order");
                int map2value = (Integer) o2.get("order");
                return map1value - map2value;
            }
        });
        rtn.clear();
        rtn.put("paper", paper);
        rtn.put("authors", authors);
        rtn.put("fields", fields);
        return SUCCESS;
    }

    /** 通过proId 获取项目详细内容 （Json格式） */
    public String project(){
        Project project = prodi.getProById(paperId);
        String extraAuthorsStr = project.getExtraDirectors();
        List authors = new ArrayList();

        if (extraAuthorsStr != null){
            JSONObject json = JSONObject.fromObject(extraAuthorsStr);
            List extraAuthors = (List) json.get("list");
            authors.addAll(extraAuthors);
        }
        List innerAuthors = new ProDaoImpl().getInnerDirectorsById(paperId);
        if (innerAuthors != null) {
            authors.addAll(innerAuthors);
        }
        List fields = fdi.getFieldsByProId(paperId);
        rtn.clear();
        rtn.put("project", project);
        rtn.put("authors", authors);
        rtn.put("fields", fields);

        return SUCCESS;
    }

    /** Paper （论文）分页时 需要的论文数组（Json格式），默认page=1(第一页) */
    public String paperList(){
        if (page == null){
            page = 1;
        }
        papers = pdi.getAllPaper(page, 5);

        papers = integrationAuthor(papers);

        rtn.clear();
        rtn.put("papers", papers);
        return SUCCESS;
    }

    /** 分页时 需要的项目数组（Json格式），默认page=1(第一页) */
    public String proList(){
        if (page == null){
            page = 1;
        }
        pros = prodi.getAllPro(page, 5);

        pros = integrationDirectors(pros);

        rtn.clear();
        rtn.put("pros", pros);
        return SUCCESS;
    }

    public String group(){
        Groups groups;
        return SUCCESS;
    }

    public String groupList(){

        return SUCCESS;
    }

    private List integrationAuthor(List papers){
        for (Object obj:papers) {
            Paper paper = (Paper) obj;
            String authorsStr = paper.getExtraAuthor();
            JSONObject json = new JSONObject();
            List list = new ArrayList();
            if (authorsStr != null){
                json = JSONObject.fromObject(authorsStr);
                list = (List) json.get("list");
            }
            List innerAuthors = pdi.getInnerAuthorsByPaperId(paper.getPaperId());
            list.addAll(innerAuthors);

            /** sort the authors by order */
            Collections.sort(list, new Comparator<Map<Object, Object>>() {
                public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
                    int map1value = (Integer) o1.get("order");
                    int map2value = (Integer) o2.get("order");
                    return map1value - map2value;
                }
            });

            json.put("list", list);
            List fields = fdi.getFieldsByPaperId(paper.getPaperId());
            json.put("fields", fields);

            paper.setExtraAuthor(json.toString());
        }
        return papers;
    }

    private List integrationDirectors(List pros){

        for (Object obj:pros) {
            Project pro = (Project) obj;
            String authorsStr = pro.getExtraDirectors();
            JSONObject json = new JSONObject();
            List list = new ArrayList();
            if (authorsStr != null){
                json = JSONObject.fromObject(authorsStr);
                list = (List) json.get("list");
            }
            List innerAuthors = prodi.getInnerDirectorsById(pro.getProId());
            list.addAll(innerAuthors);

            json.put("list", list);
            List fields = fdi.getFieldsByProId(pro.getProId());
            json.put("fields", fields);

            pro.setExtraDirectors(json.toString());
        }
        return pros;
    }
}
