package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/28.
 */
public class ShareWayBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取成功
     * success : true
     * defaultAList : [{"shareUrl":"http://localhost:8080/yjz/student/studentUser.h8?reqCode=toShare&referrer=YJZ1604160062022","QRcodeUrl":"http://localhost:8080/yjz/images/student/QRcode/YJZ1604160062022qr.png"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * shareUrl : http://localhost:8080/yjz/student/studentUser.h8?reqCode=toShare&referrer=YJZ1604160062022
     * QRcodeUrl : http://localhost:8080/yjz/images/student/QRcode/YJZ1604160062022qr.png
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
        private String shareUrl;
        private String QRcodeUrl;

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public String getQRcodeUrl() {
            return QRcodeUrl;
        }

        public void setQRcodeUrl(String QRcodeUrl) {
            this.QRcodeUrl = QRcodeUrl;
        }
    }
}
