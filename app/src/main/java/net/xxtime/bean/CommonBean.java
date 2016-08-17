package net.xxtime.bean;

import java.io.Serializable;

/**
 * Created by 唯图 on 2016/8/17.
 */
public class CommonBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 当前账号未被注册！
     * success : true
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;

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
}
