package shu.lab.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by Jimmy on 2016/7/29.
 */
public class ViewLoadAction extends ActionSupport{
    private String page;
    private Integer id;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {

        return super.execute();
    }
}
