package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.SearchDao;
import shu.lab.dao.impl.SearchDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SearchAction extends ActionSupport{
    private String CCFType;
    private String categoryType;
    private String Year;
    private Integer Group;
    private List<Map> list ;

    public String getCCFType() {
        return CCFType;
    }

    public void setCCFType(String CCFType) {
        this.CCFType = CCFType;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public Integer getGroup() {
        return Group;
    }

    public void setGroup(Integer group) {
        Group = group;
    }

    public List<Map> getList() {
        return list;
    }

    public void setList(List<Map> list) {
        this.list = list;
    }

    public String CCFNum(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getCCFNum(CCFType);
        return SUCCESS;
    }
    public String categoryNum(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getCategoryNum(categoryType);
        return SUCCESS;
    }
    public String yearNum(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getYearNum(Year);
        return SUCCESS;
    }
    public String GroupNum(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getGroupNum(Group);
        return SUCCESS;
    }
    public String GroupNumAll(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getGroupNumAll();
        return SUCCESS;
    }
    public String GroupNumWithYear(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getGroupNumWithYear();
        return SUCCESS;
    }
    public String GroupNumWithCCF(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getGroupNumWithCCF();
        return SUCCESS;
    }
    public String GroupNumWithCategory(){
        list = null;
        SearchDao searchDao = new SearchDaoImpl();
        list = searchDao.getGroupNumWithCategory();
        return SUCCESS;
    }
}
