package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/26.
 */
public class FocusBusBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找关注企业成功！
     * success : true
     * defaultAList : [{"buslogo":"http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg","userid":"YJZ1608172173614","code":"2016080421206","type":1,"busfullname":"苹果手机专营店"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * buslogo : http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg
     * userid : YJZ1608172173614
     * code : 2016080421206
     * type : 1
     * busfullname : 苹果手机专营店
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
        private String buslogo;
        private String userid;
        private String code;
        private int type;
        private String busfullname;

        public String getBuslogo() {
            return buslogo;
        }

        public void setBuslogo(String buslogo) {
            this.buslogo = buslogo;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBusfullname() {
            return busfullname;
        }

        public void setBusfullname(String busfullname) {
            this.busfullname = busfullname;
        }
    }
}
