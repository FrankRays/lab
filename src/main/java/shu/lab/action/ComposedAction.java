package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.web.context.ServletContextAware;
import shu.lab.dao.impl.FieldDaoImpl;
import shu.lab.dao.impl.GroupDaoImpl;
import shu.lab.dao.impl.UserDaoImpl;
import shu.lab.util.StaticParam;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Jimmy on 2016/8/19.
 */
public class ComposedAction extends ActionSupport implements ServletContextAware {
    private List list;
    private String basePath;
    private ServletContext servletContext;

    private FieldDaoImpl fdi = new FieldDaoImpl();
    private GroupDaoImpl gdi = new GroupDaoImpl();
    private UserDaoImpl udi = new UserDaoImpl();

    public List getList() {
        return list;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /** 获取所有 研究领域 */
    public String field(){
        list = fdi.getAllField();
        return SUCCESS;
    }

    /** 获取所有小组的组名 */
    public String group(){
        list = gdi.getAllGroup();
        return SUCCESS;
    }

    /** 获取所有用户名 */
    public String user(){
        list = udi.getAllUser();
        return SUCCESS;
    }

    /** 获取项目（资源）根路径 */
    public String basePath(){
        basePath = servletContext.getRealPath(StaticParam.PAPER_FOLDER);
        return SUCCESS;
    }
}
