package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/17.
 */
public class UserBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 登录成功！
     * success : true
     * defaultAList : [{"userid":"YJZ1604160062022","password":"111","telephone":"18326908223"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * userid : YJZ1604160062022
     * password : 111
     * telephone : 18326908223
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
        private String userid;
        private String password;
        private String telephone;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
