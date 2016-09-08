package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class DegreeBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找学历成功！
     * success : true
     * defaultAList : [{"degreeid":1,"degreename":"初中"},{"degreeid":2,"degreename":"高中"},{"degreeid":3,"degreename":"大专"},{"degreeid":4,"degreename":"本科"},{"degreeid":5,"degreename":"硕士"},{"degreeid":6,"degreename":"博士"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * degreeid : 1
     * degreename : 初中
     */

    private List<DefaultAListBean> defaultAList;

    public String getBflag() {
        return bflag;
    }

    public void setBflag(String bflag) {
        this.bflag = bflag;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DefaultAListBean> getDefaultAList() {
        return defaultAList;
    }

    public void setDefaultAList(List<DefaultAListBean> defaultAList) {
        this.defaultAList = defaultAList;
    }

    public static class DefaultAListBean implements Serializable{
        private int degreeid;
        private String degreename;

        public int getDegreeid() {
            return degreeid;
        }

        public void setDegreeid(int degreeid) {
            this.degreeid = degreeid;
        }

        public String getDegreename() {
            return degreename;
        }

        public void setDegreename(String degreename) {
            this.degreename = degreename;
        }
    }
}
