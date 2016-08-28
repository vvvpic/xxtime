package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/28.
 */
public class TradeListBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 查找交易明细成功！
     * success : true
     * defaultAList : [{"amounts":0.01,"withdrawal":null,"tradename":"充值诚意金(支付宝支付)","userid":"YJZ1608152434501","isdelete":0,"tradeway":4,"ordercode":"2016082736744361458","tradetime":"2016-08-27 14:36:44","tradetype":1,"tradestate":0},{"amounts":0.01,"withdrawal":null,"tradename":"充值诚意金(支付宝支付)","userid":"YJZ1608152434501","isdelete":0,"tradeway":4,"ordercode":"2016082748841361425","tradetime":"2016-08-27 14:36:41","tradetype":1,"tradestate":0}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * amounts : 0.01
     * withdrawal : null
     * tradename : 充值诚意金(支付宝支付)
     * userid : YJZ1608152434501
     * isdelete : 0
     * tradeway : 4
     * ordercode : 2016082736744361458
     * tradetime : 2016-08-27 14:36:44
     * tradetype : 1
     * tradestate : 0
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
        private double amounts;
        private Object withdrawal;
        private String tradename;
        private String userid;
        private int isdelete;
        private int tradeway;
        private String ordercode;
        private String tradetime;
        private int tradetype;
        private int tradestate=0;

        public double getAmounts() {
            return amounts;
        }

        public void setAmounts(double amounts) {
            this.amounts = amounts;
        }

        public Object getWithdrawal() {
            return withdrawal;
        }

        public void setWithdrawal(Object withdrawal) {
            this.withdrawal = withdrawal;
        }

        public String getTradename() {
            return tradename;
        }

        public void setTradename(String tradename) {
            this.tradename = tradename;
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

        public int getTradeway() {
            return tradeway;
        }

        public void setTradeway(int tradeway) {
            this.tradeway = tradeway;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getTradetime() {
            return tradetime;
        }

        public void setTradetime(String tradetime) {
            this.tradetime = tradetime;
        }

        public int getTradetype() {
            return tradetype;
        }

        public void setTradetype(int tradetype) {
            this.tradetype = tradetype;
        }

        public int getTradestate() {
            return tradestate;
        }

        public void setTradestate(int tradestate) {
            this.tradestate = tradestate;
        }
    }
}
