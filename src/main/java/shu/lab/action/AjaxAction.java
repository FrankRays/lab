package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.InformDaoImpl;
import shu.lab.entity.Inform;
import shu.lab.entity.User;

import java.util.List;


/**
 * Created by Jimmy on 2016/7/17.
 */
public class AjaxAction extends ActionSupport {
    //Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
    User user;
    User ue;
    List<Inform> list;
    String information;
    Integer senderId;
    Integer receiverId;

    String content;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getInformation() {
        return information;
    }

    public List<Inform> getList() {

        return list;
    }

    public User getUser() {
        return user;
    }

    public User getUe() {
        return ue;
    }

    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String inform(){
    System.out.println("AjaxAction.inform");
    list = new InformDaoImpl().getInformsBySenderId(1,1,10);

    return SUCCESS;
}

    public String test() throws Exception {
        System.out.println("AjaxAction.test");
        user = new User();
        ue = new User();
        user.setUserId(12);
        user.setUsername("jimmy");
        user.setPassword("123456");
        ue.setUsername("marvin");
        ue.setPassword("666666");
        ue.setUserIntro("I'm a pretty girl");
        return super.execute();
    }
    public String sendInform(){
        //boolean status = new InformDaoImpl().addInform(1, 2, "you are my friend");
        boolean status = new InformDaoImpl().addInform(senderId, receiverId, content);

        if (status){
            information = "send successful";
        }
        return SUCCESS;
    }
}
