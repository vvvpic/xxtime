package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/30.
 */
public class MsgStatusBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找成功
     * success : true
     * defaultAList : [{"msgtype":1,"unread":0},{"msgtype":2,"unread":1},{"msgtype":3,"unread":1},{"msgtype":4,"unread":1},{"msgtype":5,"unread":1}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * msgtype : 1
     * unread : 0
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
        private int msgtype;
        private int unread;

        public int getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(int msgtype) {
            this.msgtype = msgtype;
        }

        public int getUnread() {
            return unread;
        }

        public void setUnread(int unread) {
            this.unread = unread;
        }
    }
}
