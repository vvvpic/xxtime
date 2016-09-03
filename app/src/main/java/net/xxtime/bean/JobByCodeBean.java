package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/20.
 */
public class JobByCodeBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 岗位详情查询成功
     * success : true
     * defaultAList : [{"buslogo":"http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg","screenmsg1":0,"recruitboy":null,"screenmsg2":0,"screenmsg3":0,"jobname":"AAAAAAAAAAA","areaname":"南长区","shjznum":16,"joblocalatwill":1,"salarytype":3,"foreignid":6,"avgLevel":null,"applystartdate":null,"city":"320200","jobcontinuous":0,"contacts":"呵呵","jobtype":1,"gyzznum":2,"salary":9,"commission":null,"degreename":"博士","recruitall":10000,"foreignname":"韩语","provice":"320000","otherforeign":null,"isdelete":0,"jobstarttime":"08:00:00","settlementtime":4,"foreignlevel":2,"degreeid":6,"contactphone":null,"jobdemands":"无","labelnames":"提供工作餐,提供住宿","postid":23,"isFocus":0,"other":null,"contactmphone":"15206172683","provicename":"江苏省","jobstartdate":null,"publisher":0,"createtime":"2016-08-06 11:18:42","area":"320203","buscode":"2016080421206","sxzqnum":0,"recruitgirl":null,"contactphoneph":null,"contactmail":null,"registerNum":1,"basicsalary":null,"busfullname":"苹果手机专营店","jobstate":1,"starNum":5,"applyenddate":null,"settlementtype":1,"releaseallcity":1,"jobenddate":null,"isEmploy":0,"failurereason":null,"jobdescription":"无","address":null,"jobweekend":null,"joblabelids":"2,3","cityname":"无锡市","jobendtime":"18:00:00","jobcode":"160806111636616"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * buslogo : http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg
     * screenmsg1 : 0
     * recruitboy : null
     * screenmsg2 : 0
     * screenmsg3 : 0
     * jobname : AAAAAAAAAAA
     * areaname : 南长区
     * shjznum : 16
     * joblocalatwill : 1
     * salarytype : 3
     * foreignid : 6
     * avgLevel : null
     * applystartdate : null
     * city : 320200
     * jobcontinuous : 0
     * contacts : 呵呵
     * jobtype : 1
     * gyzznum : 2
     * salary : 9
     * commission : null
     * degreename : 博士
     * recruitall : 10000
     * foreignname : 韩语
     * provice : 320000
     * otherforeign : null
     * isdelete : 0
     * jobstarttime : 08:00:00
     * settlementtime : 4
     * foreignlevel : 2
     * degreeid : 6
     * contactphone : null
     * jobdemands : 无
     * labelnames : 提供工作餐,提供住宿
     * postid : 23
     * isFocus : 0
     * other : null
     * contactmphone : 15206172683
     * provicename : 江苏省
     * jobstartdate : null
     * publisher : 0
     * createtime : 2016-08-06 11:18:42
     * area : 320203
     * buscode : 2016080421206
     * sxzqnum : 0
     * recruitgirl : null
     * contactphoneph : null
     * contactmail : null
     * registerNum : 1
     * basicsalary : null
     * busfullname : 苹果手机专营店
     * jobstate : 1
     * starNum : 5
     * applyenddate : null
     * settlementtype : 1
     * releaseallcity : 1
     * jobenddate : null
     * isEmploy : 0
     * failurereason : null
     * jobdescription : 无
     * address : null
     * jobweekend : null
     * joblabelids : 2,3
     * cityname : 无锡市
     * jobendtime : 18:00:00
     * jobcode : 160806111636616
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
        private int recruitboy=0;
        private int screenmsg2;
        private int screenmsg3;
        private String jobname;
        private String areaname;
        private int shjznum;
        private int joblocalatwill;
        private int salarytype;
        private int foreignid;
        public int isApply=0;
        private String avgLevel;
        private String applystartdate;
        private String city;
        private int jobcontinuous;
        private String contacts;
        private int jobtype;
        private int gyzznum;
        private int salary;
        private String commission;
        private String degreename;
        private int recruitall=0;
        private String foreignname;
        private String provice;
        private String otherforeign;
        private int isdelete;
        private String jobstarttime;
        private int settlementtime;
        private int foreignlevel;
        private int degreeid;
        private String contactphone;
        private String jobdemands;
        private String labelnames;
        private int postid;
        private int isFocus;
        private String other;
        private String contactmphone;
        private String provicename;
        private String jobstartdate;
        private int publisher;
        private String createtime;
        private String area;
        private String buscode;
        private int sxzqnum;
        private int recruitgirl=0;
        private String contactphoneph;
        private String contactmail;
        private int registerNum;
        private String basicsalary;
        private String busfullname;
        private int jobstate;
        private int starNum;
        private String applyenddate;
        private int settlementtype=0;
        private int releaseallcity;
        private String jobenddate;
        private int isEmploy;
        private String failurereason;
        private String jobdescription;
        private String address;
        private String jobweekend;
        private String joblabelids;
        private String cityname;
        private String jobendtime;
        private String jobcode;
        public String email;
        public String dates;

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

        public int getRecruitboy() {
            return recruitboy;
        }

        public void setRecruitboy(int recruitboy) {
            this.recruitboy = recruitboy;
        }

        public int getScreenmsg2() {
            return screenmsg2;
        }

        public void setScreenmsg2(int screenmsg2) {
            this.screenmsg2 = screenmsg2;
        }

        public int getScreenmsg3() {
            return screenmsg3;
        }

        public void setScreenmsg3(int screenmsg3) {
            this.screenmsg3 = screenmsg3;
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

        public int getShjznum() {
            return shjznum;
        }

        public void setShjznum(int shjznum) {
            this.shjznum = shjznum;
        }

        public int getJoblocalatwill() {
            return joblocalatwill;
        }

        public void setJoblocalatwill(int joblocalatwill) {
            this.joblocalatwill = joblocalatwill;
        }

        public int getSalarytype() {
            return salarytype;
        }

        public void setSalarytype(int salarytype) {
            this.salarytype = salarytype;
        }

        public int getForeignid() {
            return foreignid;
        }

        public void setForeignid(int foreignid) {
            this.foreignid = foreignid;
        }

        public String getAvgLevel() {
            return avgLevel;
        }

        public void setAvgLevel(String avgLevel) {
            this.avgLevel = avgLevel;
        }

        public String getApplystartdate() {
            return applystartdate;
        }

        public void setApplystartdate(String applystartdate) {
            this.applystartdate = applystartdate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getJobcontinuous() {
            return jobcontinuous;
        }

        public void setJobcontinuous(int jobcontinuous) {
            this.jobcontinuous = jobcontinuous;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public int getJobtype() {
            return jobtype;
        }

        public void setJobtype(int jobtype) {
            this.jobtype = jobtype;
        }

        public int getGyzznum() {
            return gyzznum;
        }

        public void setGyzznum(int gyzznum) {
            this.gyzznum = gyzznum;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getDegreename() {
            return degreename;
        }

        public void setDegreename(String degreename) {
            this.degreename = degreename;
        }

        public int getRecruitall() {
            return recruitall;
        }

        public void setRecruitall(int recruitall) {
            this.recruitall = recruitall;
        }

        public String getForeignname() {
            return foreignname;
        }

        public void setForeignname(String foreignname) {
            this.foreignname = foreignname;
        }

        public String getProvice() {
            return provice;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public String getOtherforeign() {
            return otherforeign;
        }

        public void setOtherforeign(String otherforeign) {
            this.otherforeign = otherforeign;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public String getJobstarttime() {
            return jobstarttime;
        }

        public void setJobstarttime(String jobstarttime) {
            this.jobstarttime = jobstarttime;
        }

        public int getSettlementtime() {
            return settlementtime;
        }

        public void setSettlementtime(int settlementtime) {
            this.settlementtime = settlementtime;
        }

        public int getForeignlevel() {
            return foreignlevel;
        }

        public void setForeignlevel(int foreignlevel) {
            this.foreignlevel = foreignlevel;
        }

        public int getDegreeid() {
            return degreeid;
        }

        public void setDegreeid(int degreeid) {
            this.degreeid = degreeid;
        }

        public String getContactphone() {
            return contactphone;
        }

        public void setContactphone(String contactphone) {
            this.contactphone = contactphone;
        }

        public String getJobdemands() {
            return jobdemands;
        }

        public void setJobdemands(String jobdemands) {
            this.jobdemands = jobdemands;
        }

        public String getLabelnames() {
            return labelnames;
        }

        public void setLabelnames(String labelnames) {
            this.labelnames = labelnames;
        }

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
            this.postid = postid;
        }

        public int getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(int isFocus) {
            this.isFocus = isFocus;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getContactmphone() {
            return contactmphone;
        }

        public void setContactmphone(String contactmphone) {
            this.contactmphone = contactmphone;
        }

        public String getProvicename() {
            return provicename;
        }

        public void setProvicename(String provicename) {
            this.provicename = provicename;
        }

        public String getJobstartdate() {
            return jobstartdate;
        }

        public void setJobstartdate(String jobstartdate) {
            this.jobstartdate = jobstartdate;
        }

        public int getPublisher() {
            return publisher;
        }

        public void setPublisher(int publisher) {
            this.publisher = publisher;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBuscode() {
            return buscode;
        }

        public void setBuscode(String buscode) {
            this.buscode = buscode;
        }

        public int getSxzqnum() {
            return sxzqnum;
        }

        public void setSxzqnum(int sxzqnum) {
            this.sxzqnum = sxzqnum;
        }

        public int getRecruitgirl() {
            return recruitgirl;
        }

        public void setRecruitgirl(int recruitgirl) {
            this.recruitgirl = recruitgirl;
        }

        public String getContactphoneph() {
            return contactphoneph;
        }

        public void setContactphoneph(String contactphoneph) {
            this.contactphoneph = contactphoneph;
        }

        public String getContactmail() {
            return contactmail;
        }

        public void setContactmail(String contactmail) {
            this.contactmail = contactmail;
        }

        public int getRegisterNum() {
            return registerNum;
        }

        public void setRegisterNum(int registerNum) {
            this.registerNum = registerNum;
        }

        public String getBasicsalary() {
            return basicsalary;
        }

        public void setBasicsalary(String basicsalary) {
            this.basicsalary = basicsalary;
        }

        public String getBusfullname() {
            return busfullname;
        }

        public void setBusfullname(String busfullname) {
            this.busfullname = busfullname;
        }

        public int getJobstate() {
            return jobstate;
        }

        public void setJobstate(int jobstate) {
            this.jobstate = jobstate;
        }

        public int getStarNum() {
            return starNum;
        }

        public void setStarNum(int starNum) {
            this.starNum = starNum;
        }

        public String getApplyenddate() {
            return applyenddate;
        }

        public void setApplyenddate(String applyenddate) {
            this.applyenddate = applyenddate;
        }

        public int getSettlementtype() {
            return settlementtype;
        }

        public void setSettlementtype(int settlementtype) {
            this.settlementtype = settlementtype;
        }

        public int getReleaseallcity() {
            return releaseallcity;
        }

        public void setReleaseallcity(int releaseallcity) {
            this.releaseallcity = releaseallcity;
        }

        public String getJobenddate() {
            return jobenddate;
        }

        public void setJobenddate(String jobenddate) {
            this.jobenddate = jobenddate;
        }

        public int getIsEmploy() {
            return isEmploy;
        }

        public void setIsEmploy(int isEmploy) {
            this.isEmploy = isEmploy;
        }

        public String getFailurereason() {
            return failurereason;
        }

        public void setFailurereason(String failurereason) {
            this.failurereason = failurereason;
        }

        public String getJobdescription() {
            return jobdescription;
        }

        public void setJobdescription(String jobdescription) {
            this.jobdescription = jobdescription;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getJobweekend() {
            return jobweekend;
        }

        public void setJobweekend(String jobweekend) {
            this.jobweekend = jobweekend;
        }

        public String getJoblabelids() {
            return joblabelids;
        }

        public void setJoblabelids(String joblabelids) {
            this.joblabelids = joblabelids;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getJobendtime() {
            return jobendtime;
        }

        public void setJobendtime(String jobendtime) {
            this.jobendtime = jobendtime;
        }

        public String getJobcode() {
            return jobcode;
        }

        public void setJobcode(String jobcode) {
            this.jobcode = jobcode;
        }
    }
}
