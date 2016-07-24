package shu.lab.dao;

import shu.lab.entity.Inform;

import java.util.List;

/**
 * Created by Jimmy on 2016/7/24.
 */
public interface InformDao {
    boolean addInform(Integer senderId, Integer receiverId,String content);
    void modifyStatus(Integer informId, String Status);
    List<Inform> getInformsBySenderId(Integer sid,Integer page,Integer num);
    List<Inform> getInformsByReceiverId(Integer rid,Integer page,Integer num);
}
