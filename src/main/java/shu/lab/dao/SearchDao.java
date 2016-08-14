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
    List getGroupNumAll();
    List getGroupNumWithYear();
    List getGroupNumWithCCF();
    List getGroupNumWithCategory();
}
