package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/26.
 */
public class BsInfoBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取保送信息成功！
     * success : true
     * defaultAList : [{"expectsalaryday":500,"expectsalaryhour":60}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * expectsalaryday : 500
     * expectsalaryhour : 60
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
        private int expectsalaryday=0;
        private int expectsalaryhour=0;

        public int getExpectsalaryday() {
            return expectsalaryday;
        }

        public void setExpectsalaryday(int expectsalaryday) {
            this.expectsalaryday = expectsalaryday;
        }

        public int getExpectsalaryhour() {
            return expectsalaryhour;
        }

        public void setExpectsalaryhour(int expectsalaryhour) {
            this.expectsalaryhour = expectsalaryhour;
        }
    }
}
