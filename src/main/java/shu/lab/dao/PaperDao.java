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
    List<Paper> getPaperByUserId(Integer uid, Integer page, Integer num);
    List<Paper> getAllPaperByUserId(Integer uid);
    boolean addPaper(Paper paper, Integer[] authors,Integer[] corrAuthors);
    void delPaper(Integer paperId);
    Integer addDelPaperField(Integer pid, Integer fid, Integer type);
    Integer addDelPaperMember(Integer pid, Integer uid, Integer type, Integer isCorr);
    Integer addDelExtraMember(Integer pid, String extraAuthor, Integer type, Integer isCorr);
    Integer addDelFieldPaper(Integer fid, Integer pid, Integer type);
}
