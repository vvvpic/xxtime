package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/21.
 */
public class BusInfoBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取企业信息成功！
     * success : true
     * defaultAList : [{"buslogo":"http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg","updatetime":"2016-08-04 10:11:14","areaname":"新区","deposit":0,"avgStarNum":0,"userid":"15206172683","buslicense":"IMG2016080408899.jpg","busimg6":"IMG2016080447338.jpg","busimg5":"IMG2016080428469.png","busimg4":"IMG2016080419210.jpg","busimg3":"IMG2016080413586.jpg","city":"320200","busimg1":"IMG2016080400673.jpg","busimg2":"IMG2016080406677.jpg","balance":0,"username":"呵呵","rztype":"1","points":null,"idchardopposite":"IMG2016080429917.jpg","provice":"320000","isdelete":0,"zpgw":6,"email":"","telephoneqh":"","staffid":null,"isFocus":0,"bustype":1,"msgcount":0,"isforbidden":0,"buscontent":"","provicename":"江苏省","idchardpositive":"IMG2016080418335.jpg","createtime":"2016-08-04 09:56:21","buscode":"2016080421206","area":"320296","busscale":1,"regionalname":"江苏区","busfullname":"苹果手机专营店","catename":"手机/通讯","yjratio":10,"cateid":4,"bushomepage":"","busimg":"http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080400673.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080406677.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080413586.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080419210.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080428469.png,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080447338.jpg","hdpj":0,"zprs":1,"failurereason":null,"certification":1,"address":"新安花园二区188栋","cityname":"无锡市","telephone":""}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * buslogo : http://www.xxtime.net:80/images/ht-qysjimg/logo/IMG2016080428716.jpg
     * updatetime : 2016-08-04 10:11:14
     * areaname : 新区
     * deposit : 0
     * avgStarNum : 0
     * userid : 15206172683
     * buslicense : IMG2016080408899.jpg
     * busimg6 : IMG2016080447338.jpg
     * busimg5 : IMG2016080428469.png
     * busimg4 : IMG2016080419210.jpg
     * busimg3 : IMG2016080413586.jpg
     * city : 320200
     * busimg1 : IMG2016080400673.jpg
     * busimg2 : IMG2016080406677.jpg
     * balance : 0
     * username : 呵呵
     * rztype : 1
     * points : null
     * idchardopposite : IMG2016080429917.jpg
     * provice : 320000
     * isdelete : 0
     * zpgw : 6
     * email :
     * telephoneqh :
     * staffid : null
     * isFocus : 0
     * bustype : 1
     * msgcount : 0
     * isforbidden : 0
     * buscontent :
     * provicename : 江苏省
     * idchardpositive : IMG2016080418335.jpg
     * createtime : 2016-08-04 09:56:21
     * buscode : 2016080421206
     * area : 320296
     * busscale : 1
     * regionalname : 江苏区
     * busfullname : 苹果手机专营店
     * catename : 手机/通讯
     * yjratio : 10
     * cateid : 4
     * bushomepage :
     * busimg : http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080400673.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080406677.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080413586.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080419210.jpg,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080428469.png,http://www.xxtime.net:80/images/ht-qysjimg/busimg/IMG2016080447338.jpg
     * hdpj : 0
     * zprs : 1
     * failurereason : null
     * certification : 1
     * address : 新安花园二区188栋
     * cityname : 无锡市
     * telephone :
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
        private String updatetime;
        private String areaname;
        private int deposit;
        private int avgStarNum;
        private String userid;
        private String buslicense;
        private String busimg6;
        private String busimg5;
        private String busimg4;
        private String busimg3;
        private String city;
        private String busimg1;
        private String busimg2;
        private int balance;
        private String username;
        private String rztype;
        private Object points;
        private String idchardopposite;
        private String provice;
        private int isdelete;
        private int zpgw;
        private String email;
        private String telephoneqh;
        private Object staffid;
        private int isFocus;
        private int bustype;
        private int msgcount;
        private int isforbidden;
        private String buscontent;
        private String provicename;
        private String idchardpositive;
        private String createtime;
        private String buscode;
        private String area;
        private int busscale;
        private String regionalname;
        private String busfullname;
        private String catename;
        private int yjratio;
        private int cateid;
        private String bushomepage;
        private String busimg;
        private int hdpj;
        private int zprs;
        private Object failurereason;
        private int certification;
        private String address;
        private String cityname;
        private String telephone;

        public String getBuslogo() {
            return buslogo;
        }

        public void setBuslogo(String buslogo) {
            this.buslogo = buslogo;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getAvgStarNum() {
            return avgStarNum;
        }

        public void setAvgStarNum(int avgStarNum) {
            this.avgStarNum = avgStarNum;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getBuslicense() {
            return buslicense;
        }

        public void setBuslicense(String buslicense) {
            this.buslicense = buslicense;
        }

        public String getBusimg6() {
            return busimg6;
        }

        public void setBusimg6(String busimg6) {
            this.busimg6 = busimg6;
        }

        public String getBusimg5() {
            return busimg5;
        }

        public void setBusimg5(String busimg5) {
            this.busimg5 = busimg5;
        }

        public String getBusimg4() {
            return busimg4;
        }

        public void setBusimg4(String busimg4) {
            this.busimg4 = busimg4;
        }

        public String getBusimg3() {
            return busimg3;
        }

        public void setBusimg3(String busimg3) {
            this.busimg3 = busimg3;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBusimg1() {
            return busimg1;
        }

        public void setBusimg1(String busimg1) {
            this.busimg1 = busimg1;
        }

        public String getBusimg2() {
            return busimg2;
        }

        public void setBusimg2(String busimg2) {
            this.busimg2 = busimg2;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRztype() {
            return rztype;
        }

        public void setRztype(String rztype) {
            this.rztype = rztype;
        }

        public Object getPoints() {
            return points;
        }

        public void setPoints(Object points) {
            this.points = points;
        }

        public String getIdchardopposite() {
            return idchardopposite;
        }

        public void setIdchardopposite(String idchardopposite) {
            this.idchardopposite = idchardopposite;
        }

        public String getProvice() {
            return provice;
        }

        public void setProvice(String provice) {
            this.provice = provice;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public int getZpgw() {
            return zpgw;
        }

        public void setZpgw(int zpgw) {
            this.zpgw = zpgw;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephoneqh() {
            return telephoneqh;
        }

        public void setTelephoneqh(String telephoneqh) {
            this.telephoneqh = telephoneqh;
        }

        public Object getStaffid() {
            return staffid;
        }

        public void setStaffid(Object staffid) {
            this.staffid = staffid;
        }

        public int getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(int isFocus) {
            this.isFocus = isFocus;
        }

        public int getBustype() {
            return bustype;
        }

        public void setBustype(int bustype) {
            this.bustype = bustype;
        }

        public int getMsgcount() {
            return msgcount;
        }

        public void setMsgcount(int msgcount) {
            this.msgcount = msgcount;
        }

        public int getIsforbidden() {
            return isforbidden;
        }

        public void setIsforbidden(int isforbidden) {
            this.isforbidden = isforbidden;
        }

        public String getBuscontent() {
            return buscontent;
        }

        public void setBuscontent(String buscontent) {
            this.buscontent = buscontent;
        }

        public String getProvicename() {
            return provicename;
        }

        public void setProvicename(String provicename) {
            this.provicename = provicename;
        }

        public String getIdchardpositive() {
            return idchardpositive;
        }

        public void setIdchardpositive(String idchardpositive) {
            this.idchardpositive = idchardpositive;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getBuscode() {
            return buscode;
        }

        public void setBuscode(String buscode) {
            this.buscode = buscode;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getBusscale() {
            return busscale;
        }

        public void setBusscale(int busscale) {
            this.busscale = busscale;
        }

        public String getRegionalname() {
            return regionalname;
        }

        public void setRegionalname(String regionalname) {
            this.regionalname = regionalname;
        }

        public String getBusfullname() {
            return busfullname;
        }

        public void setBusfullname(String busfullname) {
            this.busfullname = busfullname;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public int getYjratio() {
            return yjratio;
        }

        public void setYjratio(int yjratio) {
            this.yjratio = yjratio;
        }

        public int getCateid() {
            return cateid;
        }

        public void setCateid(int cateid) {
            this.cateid = cateid;
        }

        public String getBushomepage() {
            return bushomepage;
        }

        public void setBushomepage(String bushomepage) {
            this.bushomepage = bushomepage;
        }

        public String getBusimg() {
            return busimg;
        }

        public void setBusimg(String busimg) {
            this.busimg = busimg;
        }

        public int getHdpj() {
            return hdpj;
        }

        public void setHdpj(int hdpj) {
            this.hdpj = hdpj;
        }

        public int getZprs() {
            return zprs;
        }

        public void setZprs(int zprs) {
            this.zprs = zprs;
        }

        public Object getFailurereason() {
            return failurereason;
        }

        public void setFailurereason(Object failurereason) {
            this.failurereason = failurereason;
        }

        public int getCertification() {
            return certification;
        }

        public void setCertification(int certification) {
            this.certification = certification;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
