package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/29.
 */
public class StudentUserMsgBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找用户消息成功！
     * success : true
     * defaultAList : [{"transaction_num":null,"msgid":15,"buscode":"2016080421206","isread":0,"msgtype":1,"userid":"YJZ1608152434501","money":null,"msgcontent":"关注消息测试","msgtime":"2016.08.29","jobcode":null}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * transaction_num : null
     * msgid : 15
     * buscode : 2016080421206
     * isread : 0
     * msgtype : 1
     * userid : YJZ1608152434501
     * money : null
     * msgcontent : 关注消息测试
     * msgtime : 2016.08.29
     * jobcode : null
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
        private Object transaction_num;
        private int msgid;
        private String buscode;
        private int isread;
        private int msgtype;
        private String userid;
        private Object money;
        private String msgcontent;
        private String msgtime;
        private Object jobcode;
        public boolean del=false;

        public Object getTransaction_num() {
            return transaction_num;
        }

        public void setTransaction_num(Object transaction_num) {
            this.transaction_num = transaction_num;
        }

        public int getMsgid() {
            return msgid;
        }

        public void setMsgid(int msgid) {
            this.msgid = msgid;
        }

        public String getBuscode() {
            return buscode;
        }

        public void setBuscode(String buscode) {
            this.buscode = buscode;
        }

        public int getIsread() {
            return isread;
        }

        public void setIsread(int isread) {
            this.isread = isread;
        }

        public int getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(int msgtype) {
            this.msgtype = msgtype;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public String getMsgcontent() {
            return msgcontent;
        }

        public void setMsgcontent(String msgcontent) {
            this.msgcontent = msgcontent;
        }

        public String getMsgtime() {
            return msgtime;
        }

        public void setMsgtime(String msgtime) {
            this.msgtime = msgtime;
        }

        public Object getJobcode() {
            return jobcode;
        }

        public void setJobcode(Object jobcode) {
            this.jobcode = jobcode;
        }
    }
}
