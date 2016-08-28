package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/28.
 */
public class PingpayBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 支付调用成功
     * success : true
     * defaultAList : [{"amount":50,"amountRefunded":0,"amountSettle":50,"app":"app_Tuzzr1CivX58bzP0","body":"诚意金充值","channel":"alipay","clientIp":"221.178.182.70","created":1472385349,"credential":{"object":"credential","alipay":{"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_XDSCKKGSS0SSCavnb1SaH48K\"&partner=\"2088221890785075\"&out_trade_no=\"20160828195549387\"&subject=\"诚意金充值\"&body=\"诚意金充值\"&total_fee=\"0.50\"&payment_type=\"1\"&seller_id=\"2088221890785075\"&it_b_pay=\"2016-08-29 19:55:49\"&sign=\"EfqHTkrerJMTOM7E03GGY46EGyPETTM8qNk30tRPQlCVAfkpfTEZpi3Hd8UQbXTtVteFnMZt8KZEt53O8CCCzkF9EJYbBOZ%2FszDTu14AoRt14njz8SJq6gnz1lnuAlRPPyHQuibIqvuox7NQtgNrbPzZMtq2t0YisaUnEDq1AzY%3D\"&sign_type=\"RSA\""}},"currency":"cny","description":"","extra":{},"failureCode":"","failureMsg":"","id":"ch_XDSCKKGSS0SSCavnb1SaH48K","livemode":true,"metadata":{"order_type":"studentOrder"},"object":"charge","orderNo":"20160828195549387","paid":false,"refunded":false,"refunds":{"URL":"/v1/charges/ch_XDSCKKGSS0SSCavnb1SaH48K/refunds","data":[],"hasMore":false,"object":"list"},"subject":"诚意金充值","timeExpire":1472471749,"timePaid":0,"timeSettle":0,"transactionNo":""}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * amount : 50
     * amountRefunded : 0
     * amountSettle : 50
     * app : app_Tuzzr1CivX58bzP0
     * body : 诚意金充值
     * channel : alipay
     * clientIp : 221.178.182.70
     * created : 1472385349
     * credential : {"object":"credential","alipay":{"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_XDSCKKGSS0SSCavnb1SaH48K\"&partner=\"2088221890785075\"&out_trade_no=\"20160828195549387\"&subject=\"诚意金充值\"&body=\"诚意金充值\"&total_fee=\"0.50\"&payment_type=\"1\"&seller_id=\"2088221890785075\"&it_b_pay=\"2016-08-29 19:55:49\"&sign=\"EfqHTkrerJMTOM7E03GGY46EGyPETTM8qNk30tRPQlCVAfkpfTEZpi3Hd8UQbXTtVteFnMZt8KZEt53O8CCCzkF9EJYbBOZ%2FszDTu14AoRt14njz8SJq6gnz1lnuAlRPPyHQuibIqvuox7NQtgNrbPzZMtq2t0YisaUnEDq1AzY%3D\"&sign_type=\"RSA\""}}
     * currency : cny
     * description :
     * extra : {}
     * failureCode :
     * failureMsg :
     * id : ch_XDSCKKGSS0SSCavnb1SaH48K
     * livemode : true
     * metadata : {"order_type":"studentOrder"}
     * object : charge
     * orderNo : 20160828195549387
     * paid : false
     * refunded : false
     * refunds : {"URL":"/v1/charges/ch_XDSCKKGSS0SSCavnb1SaH48K/refunds","data":[],"hasMore":false,"object":"list"}
     * subject : 诚意金充值
     * timeExpire : 1472471749
     * timePaid : 0
     * timeSettle : 0
     * transactionNo :
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
        private int amount;
        private int amountRefunded;
        private int amountSettle;
        private String app;
        private String body;
        private String channel;
        private String clientIp;
        private int created;
        /**
         * object : credential
         * alipay : {"orderInfo":"service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&notify_url=\"https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_XDSCKKGSS0SSCavnb1SaH48K\"&partner=\"2088221890785075\"&out_trade_no=\"20160828195549387\"&subject=\"诚意金充值\"&body=\"诚意金充值\"&total_fee=\"0.50\"&payment_type=\"1\"&seller_id=\"2088221890785075\"&it_b_pay=\"2016-08-29 19:55:49\"&sign=\"EfqHTkrerJMTOM7E03GGY46EGyPETTM8qNk30tRPQlCVAfkpfTEZpi3Hd8UQbXTtVteFnMZt8KZEt53O8CCCzkF9EJYbBOZ%2FszDTu14AoRt14njz8SJq6gnz1lnuAlRPPyHQuibIqvuox7NQtgNrbPzZMtq2t0YisaUnEDq1AzY%3D\"&sign_type=\"RSA\""}
         */

        private CredentialBean credential;
        private String currency;
        private String description;
        private ExtraBean extra;
        private String failureCode;
        private String failureMsg;
        private String id;
        private boolean livemode;
        /**
         * order_type : studentOrder
         */

        private MetadataBean metadata;
        private String object;
        private String orderNo;
        private boolean paid;
        private boolean refunded;
        /**
         * URL : /v1/charges/ch_XDSCKKGSS0SSCavnb1SaH48K/refunds
         * data : []
         * hasMore : false
         * object : list
         */

        private RefundsBean refunds;
        private String subject;
        private int timeExpire;
        private int timePaid;
        private int timeSettle;
        private String transactionNo;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmountRefunded() {
            return amountRefunded;
        }

        public void setAmountRefunded(int amountRefunded) {
            this.amountRefunded = amountRefunded;
        }

        public int getAmountSettle() {
            return amountSettle;
        }

        public void setAmountSettle(int amountSettle) {
            this.amountSettle = amountSettle;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getClientIp() {
            return clientIp;
        }

        public void setClientIp(String clientIp) {
            this.clientIp = clientIp;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public CredentialBean getCredential() {
            return credential;
        }

        public void setCredential(CredentialBean credential) {
            this.credential = credential;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ExtraBean getExtra() {
            return extra;
        }

        public void setExtra(ExtraBean extra) {
            this.extra = extra;
        }

        public String getFailureCode() {
            return failureCode;
        }

        public void setFailureCode(String failureCode) {
            this.failureCode = failureCode;
        }

        public String getFailureMsg() {
            return failureMsg;
        }

        public void setFailureMsg(String failureMsg) {
            this.failureMsg = failureMsg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isLivemode() {
            return livemode;
        }

        public void setLivemode(boolean livemode) {
            this.livemode = livemode;
        }

        public MetadataBean getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataBean metadata) {
            this.metadata = metadata;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }

        public boolean isRefunded() {
            return refunded;
        }

        public void setRefunded(boolean refunded) {
            this.refunded = refunded;
        }

        public RefundsBean getRefunds() {
            return refunds;
        }

        public void setRefunds(RefundsBean refunds) {
            this.refunds = refunds;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getTimeExpire() {
            return timeExpire;
        }

        public void setTimeExpire(int timeExpire) {
            this.timeExpire = timeExpire;
        }

        public int getTimePaid() {
            return timePaid;
        }

        public void setTimePaid(int timePaid) {
            this.timePaid = timePaid;
        }

        public int getTimeSettle() {
            return timeSettle;
        }

        public void setTimeSettle(int timeSettle) {
            this.timeSettle = timeSettle;
        }

        public String getTransactionNo() {
            return transactionNo;
        }

        public void setTransactionNo(String transactionNo) {
            this.transactionNo = transactionNo;
        }

        public static class CredentialBean {
            private String object;
            /**
             * orderInfo : service="mobile.securitypay.pay"&_input_charset="utf-8"&notify_url="https%3A%2F%2Fapi.pingxx.com%2Fnotify%2Fcharges%2Fch_XDSCKKGSS0SSCavnb1SaH48K"&partner="2088221890785075"&out_trade_no="20160828195549387"&subject="诚意金充值"&body="诚意金充值"&total_fee="0.50"&payment_type="1"&seller_id="2088221890785075"&it_b_pay="2016-08-29 19:55:49"&sign="EfqHTkrerJMTOM7E03GGY46EGyPETTM8qNk30tRPQlCVAfkpfTEZpi3Hd8UQbXTtVteFnMZt8KZEt53O8CCCzkF9EJYbBOZ%2FszDTu14AoRt14njz8SJq6gnz1lnuAlRPPyHQuibIqvuox7NQtgNrbPzZMtq2t0YisaUnEDq1AzY%3D"&sign_type="RSA"
             */

            private AlipayBean alipay;

            public String getObject() {
                return object;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public AlipayBean getAlipay() {
                return alipay;
            }

            public void setAlipay(AlipayBean alipay) {
                this.alipay = alipay;
            }

            public static class AlipayBean {
                private String orderInfo;

                public String getOrderInfo() {
                    return orderInfo;
                }

                public void setOrderInfo(String orderInfo) {
                    this.orderInfo = orderInfo;
                }
            }
        }

        public static class ExtraBean {
        }

        public static class MetadataBean {
            private String order_type;

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }
        }

        public static class RefundsBean {
            private String URL;
            private boolean hasMore;
            private String object;
            private List<?> data;

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public String getObject() {
                return object;
            }

            public void setObject(String object) {
                this.object = object;
            }

            public List<?> getData() {
                return data;
            }

            public void setData(List<?> data) {
                this.data = data;
            }
        }
    }
}
