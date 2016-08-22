package shu.lab.dao;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface FieldDao {

    /** add a field with a describe string */
    void addField(String descr);

    /** delete a field using field id */
    void delField(Integer fid);

    /** get all fields from DB*/
    List getAllField();

    List getFieldsById(Integer uid);

    List getFieldsByPaperId(Integer pid);

    List getFieldsByProId(Integer pid);
}
