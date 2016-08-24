package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class HomeLbtDetailBean implements Serializable {

    /**
     * bflag : 1
     * prompt : 成功
     * msg : 获取详情成功！
     * success : true
     * defaultAList : [{"detailid":10,"details":"fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fg个人头算刚刚的广泛地是否第三个范德萨功夫大使馆梵蒂冈","imageid":4,"detailimg":"http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016082136095.png"},{"detailid":11,"details":"fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj ","imageid":4,"detailimg":null}]
     */

    private String bflag;
    private String prompt;
    private String msg;
    private boolean success;
    /**
     * detailid : 10
     * details : fdsafdsafdwaufeiouoiyiutrewh678068(^0fpsahfkahb;{}[]s]aufoibp anudsioau890/;dskg;f[id)(7reuoip*)^)*hoire897jhjkhjdjska;ouj fg个人头算刚刚的广泛地是否第三个范德萨功夫大使馆梵蒂冈
     * imageid : 4
     * detailimg : http://www.xxtime.net:80/images/ht-allimg/ppt/IMG2016082136095.png
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
        private int detailid;
        private String details;
        private int imageid;
        private String detailimg;

        public int getDetailid() {
            return detailid;
        }

        public void setDetailid(int detailid) {
            this.detailid = detailid;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public int getImageid() {
            return imageid;
        }

        public void setImageid(int imageid) {
            this.imageid = imageid;
        }

        public String getDetailimg() {
            return detailimg;
        }

        public void setDetailimg(String detailimg) {
            this.detailimg = detailimg;
        }
    }
}
