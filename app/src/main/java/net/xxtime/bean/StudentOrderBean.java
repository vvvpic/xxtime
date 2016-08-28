package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/28.
 */
public class StudentOrderBean implements Serializable{

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 订单保存成功
     * success : true
     * defaultAList : [{"amount":"50","price":"50","reqCode":"save","state":0,"userid":"YJZ1608152434501","isdelete":0,"user_id":"YJZ1608152434501","add_time":{"date":28,"day":0,"hours":18,"minutes":42,"month":7,"seconds":42,"time":1472380962659,"timezoneOffset":-480,"year":116},"ordercode":"20160828184242659","type":"2","channel":"alipay"}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * amount : 50
     * price : 50
     * reqCode : save
     * state : 0
     * userid : YJZ1608152434501
     * isdelete : 0
     * user_id : YJZ1608152434501
     * add_time : {"date":28,"day":0,"hours":18,"minutes":42,"month":7,"seconds":42,"time":1472380962659,"timezoneOffset":-480,"year":116}
     * ordercode : 20160828184242659
     * type : 2
     * channel : alipay
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
        private String amount;
        private String price;
        private String reqCode;
        private int state;
        private String userid;
        private int isdelete;
        private String user_id;
        /**
         * date : 28
         * day : 0
         * hours : 18
         * minutes : 42
         * month : 7
         * seconds : 42
         * time : 1472380962659
         * timezoneOffset : -480
         * year : 116
         */

        private AddTimeBean add_time;
        private String ordercode;
        private String type;
        private String channel;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getReqCode() {
            return reqCode;
        }

        public void setReqCode(String reqCode) {
            this.reqCode = reqCode;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public int getIsdelete() {
            return isdelete;
        }

        public void setIsdelete(int isdelete) {
            this.isdelete = isdelete;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public AddTimeBean getAdd_time() {
            return add_time;
        }

        public void setAdd_time(AddTimeBean add_time) {
            this.add_time = add_time;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public static class AddTimeBean {
            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
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
