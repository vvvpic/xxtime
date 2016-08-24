package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class BusCommentBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取企业获得的评论成功！
     * success : true
     * defaultAList : [{"content":"工作环境不错，好评好评","logo":"IMG16081817536934460.png","level":4,"name":"AA","commenttime":"2016/08/22"},{"content":"啦啦啦啦啦啦啦，假数据啦","logo":"IMG16070810001931609.jpg","level":3,"name":"杨震","commenttime":"2016/08/22"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * content : 工作环境不错，好评好评
     * logo : IMG16081817536934460.png
     * level : 4
     * name : AA
     * commenttime : 2016/08/22
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
        private String content;
        private String logo;
        private int level=0;
        private String name;
        private String commenttime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommenttime() {
            return commenttime;
        }

        public void setCommenttime(String commenttime) {
            this.commenttime = commenttime;
        }
    }
}
