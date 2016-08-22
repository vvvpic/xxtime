package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/21.
 */
public class EmployedStudentBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取企业招聘的求职者成功！
     * success : true
     * defaultAList : [{"logo":"http://www.xxtime.net:80/images/student/logo/IMG16081817536934460.png","schoolname":"北京科技大学天津学院","registertime":"2016/08/06","name":"AA"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * logo : http://www.xxtime.net:80/images/student/logo/IMG16081817536934460.png
     * schoolname : 北京科技大学天津学院
     * registertime : 2016/08/06
     * name : AA
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
        private String logo;
        private String schoolname;
        private String registertime;
        private String name;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public String getRegistertime() {
            return registertime;
        }

        public void setRegistertime(String registertime) {
            this.registertime = registertime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
