package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/27.
 */
public class StudentAccountBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功s
     * msg : 账号信息获取成功！
     * success : true
     * defaultAList : [{"id":6,"accountname":"李恩","userid":"YJZ1608152434501","type":1,"accountid":"76688@qq.com"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * id : 6
     * accountname : 李恩
     * userid : YJZ1608152434501
     * type : 1
     * accountid : 76688@qq.com
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
        private int id;
        private String accountname;
        private String userid;
        private int type;
        private String accountid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccountname() {
            return accountname;
        }

        public void setAccountname(String accountname) {
            this.accountname = accountname;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAccountid() {
            return accountid;
        }

        public void setAccountid(String accountid) {
            this.accountid = accountid;
        }
    }
}
