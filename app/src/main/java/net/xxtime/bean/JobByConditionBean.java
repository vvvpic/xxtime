package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/19.
 */
public class JobByConditionBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 岗位查找成功！
     * success : true
     * defaultAList : [{"buslogo":"http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg","screenmsg1":0,"applyenddate":"2016-08-19","starNum":5,"jobname":"thtrhhdjrmjtgfndfbviukberdbnfdobienfdbdfnvidfhendfbndjfbdf","areaname":"北塘区","provice":"320000","salarytype":2,"city":"320200","applystartdate":"2016-08-06","area":"320204","address":"北塘","settlementtime":1,"salary":54,"cityname":"无锡市","labelnames":"有培训,提供工作餐","jobcode":"160806112906892"},{"buslogo":"http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg","screenmsg1":0,"applyenddate":null,"starNum":5,"jobname":"AAAAAAAAAAA","areaname":"南长区","provice":"320000","salarytype":3,"city":"320200","applystartdate":null,"area":"320203","address":null,"settlementtime":4,"salary":9,"cityname":"无锡市","labelnames":"提供工作餐,提供住宿","jobcode":"160806111636616"},{"buslogo":"http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg","screenmsg1":1,"applyenddate":"2016-09-01","starNum":5,"jobname":"iPhone8发布会招募1","areaname":"新区","provice":"320000","salarytype":2,"city":"320200","applystartdate":"2016-08-04","area":"320296","address":"新安花园一区188","settlementtime":4,"salary":160,"cityname":"无锡市","labelnames":"提供工作餐","jobcode":"160804100942863"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * buslogo : http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg
     * screenmsg1 : 0
     * applyenddate : 2016-08-19
     * starNum : 5
     * jobname : thtrhhdjrmjtgfndfbviukberdbnfdobienfdbdfnvidfhendfbndjfbdf
     * areaname : 北塘区
     * provice : 320000
     * salarytype : 2
     * city : 320200
     * applystartdate : 2016-08-06
     * area : 320204
     * address : 北塘
     * settlementtime : 1
     * salary : 54
     * cityname : 无锡市
     * labelnames : 有培训,提供工作餐
     * jobcode : 160806112906892
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
        private int screenmsg1;
        private String applyenddate;
        private int starNum;
        private String jobname;
        private String areaname;
        private String provice;
        private int salarytype;
        private String city;
        private String applystartdate;
        private String area;
        private String address;
        private int settlementtime;
        public int registerid=0;
        public int workid=0;
        public int settlementid=0;
        private int salary;
        private String cityname;
        private String labelnames;
        private String jobcode;
        public String jobstartdate;
        public String jobenddate;

        public String getBuslogo() {
            return buslogo;
        }

        public void setBuslogo(String buslogo) {
            this.buslogo = buslogo;
        }

        public int getScreenmsg1() {
            return screenmsg1;
        }

        public void setScreenmsg1(int screenmsg1) {
            this.screenmsg1 = screenmsg1;
        }

        public String getApplyenddate() {
            return applyenddate;
        }

        public void setApplyenddate(String applyenddate) {
            this.applyenddate = applyenddate;
        }

        public int getStarNum() {
            return starNum;
        }

        public void setStarNum(int starNum) {
            this.starNum = starNum;
        }

        public String getJobname() {
            return jobname;
        }

        public void setJobname(String jobname) {
            this.jobname = jobname;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getProvice() {
            return provice;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public int getSalarytype() {
            return salarytype;
        }

        public void setSalarytype(int salarytype) {
            this.salarytype = salarytype;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getApplystartdate() {
            return applystartdate;
        }

        public void setApplystartdate(String applystartdate) {
            this.applystartdate = applystartdate;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSettlementtime() {
            return settlementtime;
        }

        public void setSettlementtime(int settlementtime) {
            this.settlementtime = settlementtime;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getLabelnames() {
            return labelnames;
        }

        public void setLabelnames(String labelnames) {
            this.labelnames = labelnames;
        }

        public String getJobcode() {
            return jobcode;
        }

        public void setJobcode(String jobcode) {
            this.jobcode = jobcode;
        }
    }
}
