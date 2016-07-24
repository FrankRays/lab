package shu.lab.dao;

import shu.lab.entity.Paper;

import java.util.List;
import java.util.Set;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface PaperDao {
    List<Paper> getLatestTenPaper();
    List<Paper> getFamousTenPaper();
    boolean addPaper(Set<Integer> authorSet);
}
