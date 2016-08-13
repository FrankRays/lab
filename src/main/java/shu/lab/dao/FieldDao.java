package shu.lab.dao;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface FieldDao {

    /** add a field with a describe string */
    void addField(String descr);

    /** delete a field using field id */
    void delField(Integer fid);
}
