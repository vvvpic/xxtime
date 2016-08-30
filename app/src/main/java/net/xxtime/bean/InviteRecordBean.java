package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/29.
 */
public class InviteRecordBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取邀请记录成功！
     * success : true
     * defaultAList : [{"createtime":"2016-08-15","telephone":"15295013726"},{"createtime":"2016-08-17","telephone":"15999999999"},{"createtime":"2016-08-24","telephone":"13888888888"},{"createtime":"2016-08-24","telephone":"18904313142"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * createtime : 2016-08-15
     * telephone : 15295013726
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
        private String createtime;
        private String telephone;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
