package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/17.
 */
public class SendMsgCodeBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取验证码成功
     * success : true
     * defaultAList : [{"randomCode":"7392"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * randomCode : 7392
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
        private String randomCode;

        public String getRandomCode() {
            return randomCode;
        }

        public void setRandomCode(String randomCode) {
            this.randomCode = randomCode;
        }
    }
}
