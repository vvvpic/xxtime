package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/25.
 */
public class StudentUserPictureBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 加载照片成功！
     * success : true
     * defaultAList : [{"createtime":"2016-08-24 21:48:53","picture":"http://www.xxtime.net:80/images/student/per_img/IMG16082421481605365.jpg","userpictureid":20,"userid":"YJZ1608172173614"},{"createtime":"2016-08-24 21:48:55","picture":"http://www.xxtime.net:80/images/student/per_img/IMG16082421483435514.jpg","userpictureid":21,"userid":"YJZ1608172173614"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * createtime : 2016-08-24 21:48:53
     * picture : http://www.xxtime.net:80/images/student/per_img/IMG16082421481605365.jpg
     * userpictureid : 20
     * userid : YJZ1608172173614
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

    public static class DefaultAListBean implements Serializable{
        private String createtime;
        private String picture;
        private int userpictureid;
        private String userid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getUserpictureid() {
            return userpictureid;
        }

        public void setUserpictureid(int userpictureid) {
            this.userpictureid = userpictureid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
