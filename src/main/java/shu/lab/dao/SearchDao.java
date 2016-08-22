package shu.lab.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/13.
 */
public interface SearchDao {
    List<Map> getCCFNum(String ccf);
    List<Map> getYearNum(String year);
    List<Map> getCategoryNum(String category);
    List<Map> getGroupNum(Integer group);
    List<Map> getGroupNumAll();
    List<Map> getGroupNumWithYear();
    List<Map> getGroupNumWithCCF();
    List<Map> getGroupNumWithCategory();
    List<Map> getTypeNum(String type);
    List<Map> getFieldNum(String field);

    //各个查询的无参重载
    List<Map> getCCFNum();
    List<Map> getYearNum();
    List<Map> getCategoryNum();
    List<Map> getTypeNum();
    List<Map> getFieldNum();
}
