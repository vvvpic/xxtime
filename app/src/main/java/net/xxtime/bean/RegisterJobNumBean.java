package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/2.
 */
public class RegisterJobNumBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 统计报名岗位数量成功！
     * success : true
     * defaultAList : [{"wlyNum":0,"ypjNum":0,"dlyNum":2,"djsNum":0,"syNum":0,"dqdNum":0,"dpjNum":0}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * wlyNum : 0
     * ypjNum : 0
     * dlyNum : 2
     * djsNum : 0
     * syNum : 0
     * dqdNum : 0
     * dpjNum : 0
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
        private int wlyNum=0;
        private int ypjNum=0;
        private int dlyNum=0;
        private int djsNum=0;
        private int syNum=0;
        private int dqdNum=0;
        private int dpjNum=0;

        public int getWlyNum() {
            return wlyNum;
        }

        public void setWlyNum(int wlyNum) {
            this.wlyNum = wlyNum;
        }

        public int getYpjNum() {
            return ypjNum;
        }

        public void setYpjNum(int ypjNum) {
            this.ypjNum = ypjNum;
        }

        public int getDlyNum() {
            return dlyNum;
        }

        public void setDlyNum(int dlyNum) {
            this.dlyNum = dlyNum;
        }

        public int getDjsNum() {
            return djsNum;
        }

        public void setDjsNum(int djsNum) {
            this.djsNum = djsNum;
        }

        public int getSyNum() {
            return syNum;
        }

        public void setSyNum(int syNum) {
            this.syNum = syNum;
        }

        public int getDqdNum() {
            return dqdNum;
        }

        public void setDqdNum(int dqdNum) {
            this.dqdNum = dqdNum;
        }

        public int getDpjNum() {
            return dpjNum;
        }

        public void setDpjNum(int dpjNum) {
            this.dpjNum = dpjNum;
        }
    }
}
