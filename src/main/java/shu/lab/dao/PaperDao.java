package shu.lab.dao;

import shu.lab.entity.Paper;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface PaperDao {
    List<Paper> getLatestTenPaper();
    List<Paper> getFamousTenPaper();
    List<Paper> getPaperByUserId(Integer uid, Integer page, Integer num);
    List<Paper> getAllPaperByUserId(Integer uid);
    boolean addPaper(List<Integer> authors, String extraAuthor, String title,
                     String category, String CCFStatus, String postYear,
                     String volNum, String issueNum, Integer startPage,
                     Integer endPage, String url);
    void delPaper(Integer paperId);

}
