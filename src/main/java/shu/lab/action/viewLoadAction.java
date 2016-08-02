package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class viewLoadAction extends ActionSupport {
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("viewLoadAction.execute");
        //System.out.println(page);
        return super.execute();
    }
}
