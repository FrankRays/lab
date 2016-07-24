package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;
import shu.lab.dao.impl.InformDaoImpl;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Jimmy on 2016/7/17.
 */
public class AjaxAction extends ActionSupport {
    //Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
    Map<String,Object> rtnParam = new HashMap<String, Object>();
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

    public Map<String, Object> getRtnParam() {
        return rtnParam;
    }

    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String inform(){
        System.out.println("AjaxAction.inform");
        //list = new InformDaoImpl().getInformsBySenderId(1,1,10);
        rtnParam.put("informs",new InformDaoImpl().getInformsBySenderId(1,1,10));
        return SUCCESS;
    }
    public String sendInform(){
        //boolean status = new InformDaoImpl().addInform(1, 2, "you are my friend");
        boolean status = new InformDaoImpl().addInform(senderId, receiverId, content);

        if (status){
            //information = "send successful";
            rtnParam.put("information","send successful");
        }
        return SUCCESS;
    }
}
