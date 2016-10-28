package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class AllSchoolsBean implements Serializable {


    /**
     * status : 1
     * msg : 获取成功
     * colleges : [{"id":"1","name":"南开大学","pinyin":null,"enabled":1},{"id":"1018","name":"淮南联合大学","pinyin":null,"enabled":1},{"id":"1025","name":"淮南职业技术学院","pinyin":null,"enabled":1},{"id":"1090","name":"淮南市职工大学","pinyin":null,"enabled":1},{"id":"1109","name":"闽南师范大学","pinyin":null,"enabled":1},{"id":"1117","name":"闽南理工学院","pinyin":null,"enabled":1},{"id":"1118","name":"福建师范大学闽南科技学院","pinyin":null,"enabled":1},{"id":"1136","name":"福建华南女子职业学院","pinyin":null,"enabled":1},{"id":"1172","name":"厦门南洋职业学院","pinyin":null,"enabled":1},{"id":"1187","name":"南昌大学","pinyin":null,"enabled":1},{"id":"1190","name":"南昌航空大学","pinyin":null,"enabled":1},{"id":"1195","name":"赣南医学院","pinyin":null,"enabled":1},{"id":"1199","name":"赣南师范学院","pinyin":null,"enabled":1},{"id":"1206","name":"南昌工程学院","pinyin":null,"enabled":1},{"id":"1211","name":"南昌理工学院","pinyin":null,"enabled":1},{"id":"1214","name":"南昌工学院","pinyin":null,"enabled":1},{"id":"1215","name":"南昌大学科学技术学院","pinyin":null,"enabled":1},{"id":"1216","name":"南昌大学共青学院","pinyin":null,"enabled":1},{"id":"1219","name":"南昌航空大学科技学院","pinyin":null,"enabled":1},{"id":"1222","name":"江西农业大学南昌商学院","pinyin":null,"enabled":1},{"id":"1225","name":"赣南师范学院科技学院","pinyin":null,"enabled":1},{"id":"1228","name":"南昌师范学院","pinyin":null,"enabled":1},{"id":"1248","name":"南昌职业学院","pinyin":null,"enabled":1},{"id":"1256","name":"南昌师范高等专科学校","pinyin":null,"enabled":1},{"id":"1283","name":"南昌影视传播职业学院","pinyin":null,"enabled":1},{"id":"1286","name":"南昌钢铁有限责任公司职工大学","pinyin":null,"enabled":1},{"id":"1287","name":"南昌市业余大学","pinyin":null,"enabled":1},{"id":"1288","name":"南昌市职工科技大学","pinyin":null,"enabled":1},{"id":"1290","name":"南昌教育学院","pinyin":null,"enabled":1},{"id":"1292","name":"南京大学","pinyin":null,"enabled":1},{"id":"1294","name":"东南大学","pinyin":null,"enabled":1},{"id":"1295","name":"南京航空航天大学","pinyin":null,"enabled":1},{"id":"1296","name":"南京理工大学","pinyin":null,"enabled":1},{"id":"1299","name":"南京工业大学","pinyin":null,"enabled":1},{"id":"1301","name":"南京邮电大学","pinyin":null,"enabled":1},{"id":"1303","name":"江南大学","pinyin":null,"enabled":1},{"id":"1304","name":"南京林业大学","pinyin":null,"enabled":1},{"id":"1306","name":"南京信息工程大学","pinyin":null,"enabled":1},{"id":"1307","name":"南通大学","pinyin":null,"enabled":1},{"id":"1309","name":"南京农业大学","pinyin":null,"enabled":1},{"id":"1310","name":"南京医科大学","pinyin":null,"enabled":1},{"id":"1312","name":"南京中医药大学","pinyin":null,"enabled":1},{"id":"1314","name":"南京师范大学","pinyin":null,"enabled":1},{"id":"1318","name":"南京财经大学","pinyin":null,"enabled":1},{"id":"1320","name":"南京体育学院","pinyin":null,"enabled":1},{"id":"1321","name":"南京艺术学院","pinyin":null,"enabled":1},{"id":"1328","name":"南京工程学院","pinyin":null,"enabled":1},{"id":"1329","name":"南京审计学院","pinyin":null,"enabled":1},{"id":"1330","name":"南京晓庄学院","pinyin":null,"enabled":1},{"id":"1334","name":"南京特殊教育师范学院","pinyin":null,"enabled":1},{"id":"1335","name":"南通理工学院","pinyin":null,"enabled":1},{"id":"1336","name":"南京森林警察学院","pinyin":null,"enabled":1},{"id":"1337","name":"东南大学成贤学院","pinyin":null,"enabled":1},{"id":"1342","name":"南京大学金陵学院","pinyin":null,"enabled":1},{"id":"1343","name":"南京理工大学紫金学院","pinyin":null,"enabled":1},{"id":"1344","name":"南京航空航天大学金城学院","pinyin":null,"enabled":1}]
     */

    private String status;
    private String msg;
    /**
     * id : 1
     * name : 南开大学
     * pinyin : null
     * enabled : 1
     */

    private List<CollegesBean> colleges;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CollegesBean> getColleges() {
        return colleges;
    }

    public void setColleges(List<CollegesBean> colleges) {
        this.colleges = colleges;
    }

    public static class CollegesBean {
        private String id;
        private String name;
        private String pinyin;
        private int enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }
    }
}
