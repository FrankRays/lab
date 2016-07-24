package shu.lab.dao.impl;

import shu.lab.dao.PaperDao;
import shu.lab.entity.Paper;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public class PaperDaoImpl implements PaperDao {
    public List<Paper> getLatestTenPaper() {
        return null;
    }

    public List<Paper> getFamousTenPaper() {
        return null;
    }

    public List<Paper> getPaperByUserId(Integer uid, Integer page, Integer num) {
        return null;
    }

    public List<Paper> getAllPaperByUserId(Integer uid) {
        return null;
    }

    public boolean addPaper(List<Integer> authors, String extraAuthor, String title,
                            String category, String CCFStatus, String postYear,
                            String volNum, String issueNum, Integer startPage,
                            Integer endPage, String url) {

        return false;
    }

    public void delPaper(Integer paperId) {

    }
}
