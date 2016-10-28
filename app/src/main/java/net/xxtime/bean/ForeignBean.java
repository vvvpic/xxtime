package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/24.
 */
public class ForeignBean implements Serializable {

    /**
     * status : 1
     * msg : 获取成功
     * foreigns : [{"id":"ec991a3d8d33446a87c7ac39af11753e","addTime":"2016-09-07 15:47:49","name":"英语","sequence":1,"enabled":1},{"id":"24fa9e3ac37e48ac917cc14a37b13673","addTime":"2016-09-07 15:48:09","name":"德语","sequence":2,"enabled":1},{"id":"f1d6d404a5314934b8a9cdef414b0a94","addTime":"2016-09-07 15:48:12","name":"法语","sequence":3,"enabled":1},{"id":"9c4185403bc84bd1b08b603248c32026","addTime":"2016-09-07 15:48:21","name":"俄语","sequence":4,"enabled":1},{"id":"813e37ab1c3f412d8abfb7b5d456a8e5","addTime":"2016-09-07 15:48:30","name":"日语","sequence":5,"enabled":1},{"id":"bb25e976dccf4feaa1b61a5ac230e743","addTime":"2016-09-07 15:48:42","name":"韩语","sequence":6,"enabled":1},{"id":"036f6eb448ef4de48acf818d5124c02f","addTime":"2016-09-07 15:48:56","name":"西班牙语","sequence":7,"enabled":1},{"id":"9c21476140ac40c4878cca67e0ca9c55","addTime":"2016-09-07 15:49:07","name":"意大利语","sequence":8,"enabled":1}]
     */

    private String status;
    private String msg;
    /**
     * id : ec991a3d8d33446a87c7ac39af11753e
     * addTime : 2016-09-07 15:47:49
     * name : 英语
     * sequence : 1
     * enabled : 1
     */

    private List<ForeignsBean> foreigns;

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

    public List<ForeignsBean> getForeigns() {
        return foreigns;
    }

    public void setForeigns(List<ForeignsBean> foreigns) {
        this.foreigns = foreigns;
    }

    public static class ForeignsBean implements Serializable{
        private String id;
        private String addTime;
        private String name;
        private int sequence;
        private int enabled;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }
    }
}
