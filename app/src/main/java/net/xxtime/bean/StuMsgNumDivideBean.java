package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/29.
 */
public class StuMsgNumDivideBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找用户消息数量成功！
     * success : true
     * defaultAList : [{"txmsgNum":1,"gzmsgNum":2,"salarymsgNum":1,"systemmsgNum":1,"sqmsgNum":1}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * txmsgNum : 1
     * gzmsgNum : 2
     * salarymsgNum : 1
     * systemmsgNum : 1
     * sqmsgNum : 1
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
        private int txmsgNum;
        private int gzmsgNum;
        private int salarymsgNum;
        private int systemmsgNum;
        private int sqmsgNum;

        public int getTxmsgNum() {
            return txmsgNum;
        }

        public void setTxmsgNum(int txmsgNum) {
            this.txmsgNum = txmsgNum;
        }

        public int getGzmsgNum() {
            return gzmsgNum;
        }

        public void setGzmsgNum(int gzmsgNum) {
            this.gzmsgNum = gzmsgNum;
        }

        public int getSalarymsgNum() {
            return salarymsgNum;
        }

        public void setSalarymsgNum(int salarymsgNum) {
            this.salarymsgNum = salarymsgNum;
        }

        public int getSystemmsgNum() {
            return systemmsgNum;
        }

        public void setSystemmsgNum(int systemmsgNum) {
            this.systemmsgNum = systemmsgNum;
        }

        public int getSqmsgNum() {
            return sqmsgNum;
        }

        public void setSqmsgNum(int sqmsgNum) {
            this.sqmsgNum = sqmsgNum;
        }
    }
}
