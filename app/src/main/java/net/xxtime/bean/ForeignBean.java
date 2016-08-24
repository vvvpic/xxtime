package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/24.
 */
public class ForeignBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找外语成功！
     * success : true
     * defaultAList : [{"foreignname":"英语","foreignid":1},{"foreignname":"法语","foreignid":2},{"foreignname":"德语","foreignid":3},{"foreignname":"俄语","foreignid":4},{"foreignname":"日语","foreignid":5},{"foreignname":"韩语","foreignid":6},{"foreignname":"西班牙语","foreignid":7},{"foreignname":"意大利语","foreignid":8}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * foreignname : 英语
     * foreignid : 1
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
        private String foreignname;
        private int foreignid;

        public String getForeignname() {
            return foreignname;
        }

        public void setForeignname(String foreignname) {
            this.foreignname = foreignname;
        }

        public int getForeignid() {
            return foreignid;
        }

        public void setForeignid(int foreignid) {
            this.foreignid = foreignid;
        }
    }
}
