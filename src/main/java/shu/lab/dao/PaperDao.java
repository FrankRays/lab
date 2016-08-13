package shu.lab.dao;

import shu.lab.entity.Paper;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface PaperDao {
    List<Paper> getLatestTenPaper();
    List<Paper> getFamousTenPaper();
    Paper getPaperById(Integer pid);
    /** get paper by user id with pagination */
    List<Paper> getPaperByUserId(Integer uid, Integer page, Integer num);
    /** get all user paper*/
    List<Paper> getAllPaperByUserId(Integer uid);
    /** add a paper record into DB and return paperId*/
    Integer addPaper(Paper paper);
    /**
     *
     * Include delete or add a couple of member and change order of them
     * */
    boolean updPaperMember(Integer paperId, List authors);

    /** delete a paper record from DB */
    void delPaper(Integer paperId);
    /**
     * @param fid research field id
     * @param type defined option type (StaticParam.ADD or StaticParam.DELETE)
     * */
    Integer addDelPaperField(Integer pid, Integer fid, Integer type);
    /**
     * @param type defined option type (StaticParam.ADD or StaticParam.DELETE)
     * @param isCorr 表示是否是通讯作者(0 表示 否， 1 表示 是)
     * @param pid paper id
     * @param uid user id
     * */
    Integer addDelPaperMember(Integer pid, Integer uid, Integer type, Integer isCorr);
    Integer addDelExtraMember(Integer pid, String extraAuthor, Integer type, Integer isCorr);
    Integer addDelFieldPaper(Integer fid, Integer pid, Integer type);
}
