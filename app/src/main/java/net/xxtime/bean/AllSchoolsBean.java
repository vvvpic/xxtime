package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class AllSchoolsBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查询成功
     * success : true
     * defaultAList : [{"schoolname":"上海交通大学"},{"schoolname":"上海理工大学"},{"schoolname":"上海海事大学"},{"schoolname":"上海电力学院"},{"schoolname":"上海应用技术学院"},{"schoolname":"上海健康医学院"},{"schoolname":"上海海洋大学"},{"schoolname":"上海中医药大学"},{"schoolname":"上海师范大学"},{"schoolname":"上海外国语大学"},{"schoolname":"上海财经大学"},{"schoolname":"上海对外经贸大学"},{"schoolname":"上海海关学院"},{"schoolname":"上海体育学院"},{"schoolname":"上海音乐学院"},{"schoolname":"上海戏剧学院"},{"schoolname":"上海大学"},{"schoolname":"上海工程技术大学"},{"schoolname":"上海立信会计学院"},{"schoolname":"上海电机学院"},{"schoolname":"上海金融学院"},{"schoolname":"上海杉达学院"},{"schoolname":"上海政法学院"},{"schoolname":"上海第二工业大学"},{"schoolname":"上海商学院"},{"schoolname":"上海建桥学院"},{"schoolname":"上海兴伟学院"},{"schoolname":"上海视觉艺术学院"},{"schoolname":"上海外国语大学贤达经济人文学院"},{"schoolname":"上海师范大学天华学院"},{"schoolname":"上海科技大学"},{"schoolname":"上海纽约大学"},{"schoolname":"上海旅游高等专科学校"},{"schoolname":"上海公安高等专科学校"},{"schoolname":"上海东海职业技术学院"},{"schoolname":"上海工商职业技术学院"},{"schoolname":"上海出版印刷高等专科学校"},{"schoolname":"上海行健职业学院"},{"schoolname":"上海城市管理职业技术学院"},{"schoolname":"上海交通职业技术学院"},{"schoolname":"上海海事职业技术学院"},{"schoolname":"上海电子信息职业技术学院"},{"schoolname":"上海震旦职业学院"},{"schoolname":"上海民远职业技术学院"},{"schoolname":"上海欧华职业技术学院"},{"schoolname":"上海思博职业技术学院"},{"schoolname":"上海立达职业技术学院"},{"schoolname":"上海工艺美术职业学院"},{"schoolname":"上海济光职业技术学院"},{"schoolname":"上海工商外国语职业学院"},{"schoolname":"上海科学技术职业学院"},{"schoolname":"上海农林职业技术学院"},{"schoolname":"上海邦德职业技术学院"},{"schoolname":"上海中侨职业技术学院"},{"schoolname":"上海建峰职业技术学院"},{"schoolname":"上海电影艺术职业学院"},{"schoolname":"上海中华职业技术学院"},{"schoolname":"上海工会管理职业学院"},{"schoolname":"上海体育职业学院"},{"schoolname":"上海健康职业技术学院"},{"schoolname":"上海民航职业技术学院"},{"schoolname":"上海科技管理干部学院"},{"schoolname":"上海市黄浦区业余大学"},{"schoolname":"上海市徐汇区业余大学"},{"schoolname":"上海市长宁区业余大学"},{"schoolname":"上海市静安区业余大学"},{"schoolname":"上海市普陀区业余大学"},{"schoolname":"上海市虹口区业余大学"},{"schoolname":"上海市杨浦区业余大学"},{"schoolname":"上海纺织工业职工大学"},{"schoolname":"上海医药职工大学"},{"schoolname":"上海开放大学"},{"schoolname":"上海市经济管理干部学院"},{"schoolname":"上海青年管理干部学院"},{"schoolname":"上海市宝山区业余大学"},{"schoolname":"上海财经大学浙江学院"},{"schoolname":"上饶师范学院"},{"schoolname":"上饶职业技术学院"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * schoolname : 上海交通大学
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
        private String schoolname;

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }
    }
}
