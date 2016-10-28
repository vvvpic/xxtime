package net.xxtime.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class DegreeBean implements Serializable {

    /**
     * status : 1
     * msg : 获取成功
     * degrees : [{"id":"76e52e01af3c4f06a3950b8678ef8e48","addTime":"2016-09-07 15:49:29","name":"初中","sequence":1,"enabled":1},{"id":"bba2831404954781ac84ea42bcc41600","addTime":"2016-09-07 15:49:40","name":"高中","sequence":2,"enabled":1},{"id":"7668a8ff9d164cd1a5a99762b6fb584c","addTime":"2016-09-07 15:49:52","name":"大专","sequence":3,"enabled":1},{"id":"dc0127ecbd6f490c9f7358b6c52e278c","addTime":"2016-09-07 15:49:57","name":"本科","sequence":4,"enabled":1},{"id":"41c13fa3524142099b5a3a0b42352a39","addTime":"2016-09-07 16:30:07","name":"硕士","sequence":5,"enabled":1},{"id":"19bc3e2643bb4ac5af3e2920743208fa","addTime":"2016-09-07 16:30:17","name":"博士","sequence":6,"enabled":1}]
     */

    private String status;
    private String msg;
    /**
     * id : 76e52e01af3c4f06a3950b8678ef8e48
     * addTime : 2016-09-07 15:49:29
     * name : 初中
     * sequence : 1
     * enabled : 1
     */

    private List<DegreesBean> degrees;

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

    public List<DegreesBean> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<DegreesBean> degrees) {
        this.degrees = degrees;
    }

    public static class DegreesBean implements Serializable{
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
