package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/7.
 */
public class AppraisalLabelsBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 自我评价标签加载成功！
     * success : true
     * defaultAList : [{"labelcontent":"开朗","labelid":1},{"labelcontent":"诚实","labelid":2},{"labelcontent":"活泼","labelid":3},{"labelcontent":"自信","labelid":4},{"labelcontent":"随和","labelid":5},{"labelcontent":"热心","labelid":6},{"labelcontent":"学生干部","labelid":7},{"labelcontent":" 普通话标准","labelid":8}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * labelcontent : 开朗
     * labelid : 1
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
        private String labelcontent;
        private int labelid;
        public boolean choosebool=false;

        public String getLabelcontent() {
            return labelcontent;
        }

        public void setLabelcontent(String labelcontent) {
            this.labelcontent = labelcontent;
        }

        public int getLabelid() {
            return labelid;
        }

        public void setLabelid(int labelid) {
            this.labelid = labelid;
        }
    }
}
