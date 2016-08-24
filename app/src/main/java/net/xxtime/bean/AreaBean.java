package net.xxtime.bean;

import java.io.Serializable;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class AreaBean implements Serializable {

    /**
     * code : 130121
     * addName : 井陉县
     */

    private String code;
    private String addName;
    public String Pinyin;
    public boolean nextbool=false;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }
}
