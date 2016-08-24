package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/23.
 */
public class StudentUserInfoBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 学生用户信息加载成功！
     * success : true
     * defaultAList : [{"areaname":null,"updatetime":null,"isstudent":null,"earnestmoney":0,"userid":"YJZ1608152434501","labelcontents":"","foreignid":null,"password":"123456","city":null,"balance":0,"height":null,"province":null,"gender":null,"points":0,"degreename":null,"applydate":null,"foreignname":null,"nickname":null,"expectsalaryhour":null,"otherforeign":null,"departmentname":null,"studentcardcode":null,"referrer":null,"referrername":null,"email":null,"foreignlevel":null,"degreeid":null,"distarea":null,"staffid":null,"birthday":null,"cardcode":null,"weight":null,"enrollmentyear":null,"studentcard":null,"createtime":"2016-08-15","provincename":null,"area":null,"employstate":0,"name":null,"freetime":null,"regionalname":null,"healthcard":null,"jobintension":null,"logo":null,"expectsalaryday":null,"majorname":null,"cardreverse":null,"certification":0,"studentStarnum":0,"schoolname":null,"self_appraisalids":null,"cardobverse":null,"userstate":0,"cityname":null,"employdate":null,"telephone":"15295013726","staffname":null,"xzarea":null}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * areaname : null
     * updatetime : null
     * isstudent : null
     * earnestmoney : 0
     * userid : YJZ1608152434501
     * labelcontents :
     * foreignid : null
     * password : 123456
     * city : null
     * balance : 0
     * height : null
     * province : null
     * gender : null
     * points : 0
     * degreename : null
     * applydate : null
     * foreignname : null
     * nickname : null
     * expectsalaryhour : null
     * otherforeign : null
     * departmentname : null
     * studentcardcode : null
     * referrer : null
     * referrername : null
     * email : null
     * foreignlevel : null
     * degreeid : null
     * distarea : null
     * staffid : null
     * birthday : null
     * cardcode : null
     * weight : null
     * enrollmentyear : null
     * studentcard : null
     * createtime : 2016-08-15
     * provincename : null
     * area : null
     * employstate : 0
     * name : null
     * freetime : null
     * regionalname : null
     * healthcard : null
     * jobintension : null
     * logo : null
     * expectsalaryday : null
     * majorname : null
     * cardreverse : null
     * certification : 0
     * studentStarnum : 0
     * schoolname : null
     * self_appraisalids : null
     * cardobverse : null
     * userstate : 0
     * cityname : null
     * employdate : null
     * telephone : 15295013726
     * staffname : null
     * xzarea : null
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
        private String areaname;
        private String updatetime;
        private String isstudent;
        private int earnestmoney;
        private String userid;
        private String labelcontents;
        private String foreignid;
        private String password;
        private String city;
        private int balance;
        private String height;
        private String province;
        private String gender;
        private int points;
        private String degreename;
        private String applydate;
        private String foreignname;
        private String nickname;
        private String expectsalaryhour;
        private String otherforeign;
        private String departmentname;
        private String studentcardcode;
        private String referrer;
        private String referrername;
        private String email;
        private String foreignlevel;
        private String degreeid;
        private String distarea;
        private String staffid;
        private String birthday;
        private String cardcode;
        private String weight;
        private String enrollmentyear;
        private String studentcard;
        private String createtime;
        private String provincename;
        private String area;
        private int employstate;
        private String name;
        private String freetime;
        private String regionalname;
        private String healthcard;
        private String jobintension;
        private String logo;
        private String expectsalaryday;
        private String majorname;
        private String cardreverse;
        private int certification;
        private int studentStarnum;
        private String schoolname;
        private String self_appraisalids;
        private String cardobverse;
        private int userstate;
        private String cityname;
        private String employdate;
        private String telephone;
        private String staffname;
        private String xzarea;

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getIsstudent() {
            return isstudent;
        }

        public void setIsstudent(String isstudent) {
            this.isstudent = isstudent;
        }

        public int getEarnestmoney() {
            return earnestmoney;
        }

        public void setEarnestmoney(int earnestmoney) {
            this.earnestmoney = earnestmoney;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getLabelcontents() {
            return labelcontents;
        }

        public void setLabelcontents(String labelcontents) {
            this.labelcontents = labelcontents;
        }

        public String getForeignid() {
            return foreignid;
        }

        public void setForeignid(String foreignid) {
            this.foreignid = foreignid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getDegreename() {
            return degreename;
        }

        public void setDegreename(String degreename) {
            this.degreename = degreename;
        }

        public String getApplydate() {
            return applydate;
        }

        public void setApplydate(String applydate) {
            this.applydate = applydate;
        }

        public String getForeignname() {
            return foreignname;
        }

        public void setForeignname(String foreignname) {
            this.foreignname = foreignname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getExpectsalaryhour() {
            return expectsalaryhour;
        }

        public void setExpectsalaryhour(String expectsalaryhour) {
            this.expectsalaryhour = expectsalaryhour;
        }

        public String getOtherforeign() {
            return otherforeign;
        }

        public void setOtherforeign(String otherforeign) {
            this.otherforeign = otherforeign;
        }

        public String getDepartmentname() {
            return departmentname;
        }

        public void setDepartmentname(String departmentname) {
            this.departmentname = departmentname;
        }

        public String getStudentcardcode() {
            return studentcardcode;
        }

        public void setStudentcardcode(String studentcardcode) {
            this.studentcardcode = studentcardcode;
        }

        public String getReferrer() {
            return referrer;
        }

        public void setReferrer(String referrer) {
            this.referrer = referrer;
        }

        public String getReferrername() {
            return referrername;
        }

        public void setReferrername(String referrername) {
            this.referrername = referrername;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getForeignlevel() {
            return foreignlevel;
        }

        public void setForeignlevel(String foreignlevel) {
            this.foreignlevel = foreignlevel;
        }

        public String getDegreeid() {
            return degreeid;
        }

        public void setDegreeid(String degreeid) {
            this.degreeid = degreeid;
        }

        public String getDistarea() {
            return distarea;
        }

        public void setDistarea(String distarea) {
            this.distarea = distarea;
        }

        public String getStaffid() {
            return staffid;
        }

        public void setStaffid(String staffid) {
            this.staffid = staffid;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCardcode() {
            return cardcode;
        }

        public void setCardcode(String cardcode) {
            this.cardcode = cardcode;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getEnrollmentyear() {
            return enrollmentyear;
        }

        public void setEnrollmentyear(String enrollmentyear) {
            this.enrollmentyear = enrollmentyear;
        }

        public String getStudentcard() {
            return studentcard;
        }

        public void setStudentcard(String studentcard) {
            this.studentcard = studentcard;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getEmploystate() {
            return employstate;
        }

        public void setEmploystate(int employstate) {
            this.employstate = employstate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFreetime() {
            return freetime;
        }

        public void setFreetime(String freetime) {
            this.freetime = freetime;
        }

        public String getRegionalname() {
            return regionalname;
        }

        public void setRegionalname(String regionalname) {
            this.regionalname = regionalname;
        }

        public String getHealthcard() {
            return healthcard;
        }

        public void setHealthcard(String healthcard) {
            this.healthcard = healthcard;
        }

        public String getJobintension() {
            return jobintension;
        }

        public void setJobintension(String jobintension) {
            this.jobintension = jobintension;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getExpectsalaryday() {
            return expectsalaryday;
        }

        public void setExpectsalaryday(String expectsalaryday) {
            this.expectsalaryday = expectsalaryday;
        }

        public String getMajorname() {
            return majorname;
        }

        public void setMajorname(String majorname) {
            this.majorname = majorname;
        }

        public String getCardreverse() {
            return cardreverse;
        }

        public void setCardreverse(String cardreverse) {
            this.cardreverse = cardreverse;
        }

        public int getCertification() {
            return certification;
        }

        public void setCertification(int certification) {
            this.certification = certification;
        }

        public int getStudentStarnum() {
            return studentStarnum;
        }

        public void setStudentStarnum(int studentStarnum) {
            this.studentStarnum = studentStarnum;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public String getSelf_appraisalids() {
            return self_appraisalids;
        }

        public void setSelf_appraisalids(String self_appraisalids) {
            this.self_appraisalids = self_appraisalids;
        }

        public String getCardobverse() {
            return cardobverse;
        }

        public void setCardobverse(String cardobverse) {
            this.cardobverse = cardobverse;
        }

        public int getUserstate() {
            return userstate;
        }

        public void setUserstate(int userstate) {
            this.userstate = userstate;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getEmploydate() {
            return employdate;
        }

        public void setEmploydate(String employdate) {
            this.employdate = employdate;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getStaffname() {
            return staffname;
        }

        public void setStaffname(String staffname) {
            this.staffname = staffname;
        }

        public String getXzarea() {
            return xzarea;
        }

        public void setXzarea(String xzarea) {
            this.xzarea = xzarea;
        }
    }
}
