package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class BusCommentLevelBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取企业获得的好评度成功！
     * success : true
     * defaultAList : [{"goodpercent":0.5,"badpercent":0,"inpercent":0.5}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * goodpercent : 0.5
     * badpercent : 0
     * inpercent : 0.5
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
        private double goodpercent;
        private int badpercent;
        private double inpercent;

        public double getGoodpercent() {
            return goodpercent;
        }

        public void setGoodpercent(double goodpercent) {
            this.goodpercent = goodpercent;
        }

        public int getBadpercent() {
            return badpercent;
        }

        public void setBadpercent(int badpercent) {
            this.badpercent = badpercent;
        }

        public double getInpercent() {
            return inpercent;
        }

        public void setInpercent(double inpercent) {
            this.inpercent = inpercent;
        }
    }
}
