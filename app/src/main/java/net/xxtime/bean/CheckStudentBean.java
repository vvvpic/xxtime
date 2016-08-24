package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/23.
 */
public class CheckStudentBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 信息已完善
     * success : true
     * defaultAList : [{"isstudent":0}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * isstudent : 0
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

    public static class DefaultAListBean {
        private int isstudent;

        public int getIsstudent() {
            return isstudent;
        }

        public void setIsstudent(int isstudent) {
            this.isstudent = isstudent;
        }
    }
}
