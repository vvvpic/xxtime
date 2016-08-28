package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/26.
 */
public class FocusNumBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 统计关注数量成功！
     * success : true
     * defaultAList : [{"busNum":2,"posNum":3}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * busNum : 2
     * posNum : 3
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
        private int busNum;
        private int posNum;

        public int getBusNum() {
            return busNum;
        }

        public void setBusNum(int busNum) {
            this.busNum = busNum;
        }

        public int getPosNum() {
            return posNum;
        }

        public void setPosNum(int posNum) {
            this.posNum = posNum;
        }
    }
}
