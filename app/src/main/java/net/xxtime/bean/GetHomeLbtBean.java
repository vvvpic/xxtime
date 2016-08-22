package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class GetHomeLbtBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 加载轮播图成功！
     * success : true
     * defaultAList : [{"releasetime":{"date":6,"day":3,"hours":14,"minutes":49,"month":6,"nanos":0,"seconds":7,"time":1467787747000,"timezoneOffset":-480,"year":116},"title":"测试1（全国）","area":null,"cityName":null,"province":null,"image":"http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016070626681.jpg","imageid":1,"serialno":1,"city":"0"},{"releasetime":{"date":6,"day":3,"hours":15,"minutes":2,"month":6,"nanos":0,"seconds":31,"time":1467788551000,"timezoneOffset":-480,"year":116},"title":"测试2（全国）","area":null,"cityName":null,"province":null,"image":"http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016070655764.jpg","imageid":2,"serialno":2,"city":"0"},{"releasetime":{"date":6,"day":3,"hours":15,"minutes":5,"month":6,"nanos":0,"seconds":23,"time":1467788723000,"timezoneOffset":-480,"year":116},"title":"测试3（全国）","area":null,"cityName":null,"province":null,"image":"http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016070611712.jpg","imageid":3,"serialno":3,"city":"0"},{"releasetime":{"date":6,"day":3,"hours":15,"minutes":6,"month":6,"nanos":0,"seconds":39,"time":1467788799000,"timezoneOffset":-480,"year":116},"title":"测试4（全国）","area":null,"cityName":null,"province":null,"image":"http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016070641506.jpg","imageid":4,"serialno":4,"city":"0"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * releasetime : {"date":6,"day":3,"hours":14,"minutes":49,"month":6,"nanos":0,"seconds":7,"time":1467787747000,"timezoneOffset":-480,"year":116}
     * title : 测试1（全国）
     * area : null
     * cityName : null
     * province : null
     * image : http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016070626681.jpg
     * imageid : 1
     * serialno : 1
     * city : 0
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
        /**
         * date : 6
         * day : 3
         * hours : 14
         * minutes : 49
         * month : 6
         * nanos : 0
         * seconds : 7
         * time : 1467787747000
         * timezoneOffset : -480
         * year : 116
         */

        private ReleasetimeBean releasetime;
        private String title;
        private Object area;
        private Object cityName;
        private Object province;
        private String image;
        private int imageid;
        private int serialno;
        private String city;

        public ReleasetimeBean getReleasetime() {
            return releasetime;
        }

        public void setReleasetime(ReleasetimeBean releasetime) {
            this.releasetime = releasetime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public Object getCityName() {
            return cityName;
        }

        public void setCityName(Object cityName) {
            this.cityName = cityName;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getImageid() {
            return imageid;
        }

        public void setImageid(int imageid) {
            this.imageid = imageid;
        }

        public int getSerialno() {
            return serialno;
        }

        public void setSerialno(int serialno) {
            this.serialno = serialno;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public static class ReleasetimeBean {
            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
