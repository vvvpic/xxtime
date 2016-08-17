package net.xxtime.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 设计院 on 2016/6/30.
 * 通用信息
 */
public class Contact {

    public final static String USERINFO="userinfo";

    /**
     * 判断是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((11[0-9])|(12[0-9])|(13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
