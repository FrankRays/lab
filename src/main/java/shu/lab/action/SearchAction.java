package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.SearchDao;
import shu.lab.dao.impl.SearchDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SearchAction extends ActionSupport {
    private SearchDao searchDao = new SearchDaoImpl();
    private String CCFType;//CCF类型
    private String categoryType;//category类型
    private String Year;//年份类型
    private String Type;//type类型
    private Integer Group;//group num
    private String Field;//field类型
    private List<Map> list;

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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getField() {
        return Field;
    }

    public void setField(String field) {
        Field = field;
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
    //获取各个类型CCF数量
    public String CCFNum() {
        list = searchDao.getCCFNum();
        //list = searchDao.getCCFNum(CCFType);
        return SUCCESS;
    }
    //获取各个类型category数量
    public String categoryNum() {
        list = searchDao.getCategoryNum();
        //list = searchDao.getCategoryNum(categoryType);
        return SUCCESS;
    }
    //获取某一年文章数量
    public String yearNum() {
        list = searchDao.getYearNum();
        //list = searchDao.getYearNum(Year);
        return SUCCESS;
    }
    //获取某个小组论文数量
    public String GroupNum() {
        list = searchDao.getGroupNum(Group);
        return SUCCESS;
    }
    //获取所有小组论文数量
    public String GroupNumAll() {
        list = searchDao.getGroupNumAll();
        return SUCCESS;
    }
    //根据年份获取各个小组论文数量
    public String GroupNumWithYear() {
        list = searchDao.getGroupNumWithYear();
        return SUCCESS;
    }
    //根据CCF类型获取各个小组论文数量
    public String GroupNumWithCCF() {
        list = searchDao.getGroupNumWithCCF();
        return SUCCESS;
    }
    //根据category获取各个小组论文数量
    public String GroupNumWithCategory() {
        list = searchDao.getGroupNumWithCategory();
        return SUCCESS;
    }
    //获取某人的会议论文/期刊论文数量
    public String TypeNum() {
        list = searchDao.getTypeNum();
        //list = searchDao.getTypeNum(Type);
        return SUCCESS;
    }
    public String FieldNum() {
        list = searchDao.getTypeNum();
        //list = searchDao.getTypeNum(Field);
        return SUCCESS;
    }
}
