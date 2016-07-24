package shu.lab.dao;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface UserPaperDao {
    void addUserPaper(Integer uid, Integer pid, Integer order, Integer isCorr);
    void delUserPaper(Integer uid, Integer pid);
}
