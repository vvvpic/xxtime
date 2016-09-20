package net.xxtime.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import net.xxtime.bean.AppraisalLabelsBean;
import net.xxtime.bean.AreaBean;
import net.xxtime.bean.CheckStudentBean;
import net.xxtime.bean.CitysBean;
import net.xxtime.bean.StudentUserInfoBean;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 设计院 on 2016/6/30.
 * 通用信息
 */
public class Contact {

    public final static String USERINFO="userinfo";
    public static double Latitude;
    public static double Longitude;
    public static String City="无锡市";
    public static String ChooseCity;
    public static String CityCode;
    public static String ChooseCityCode;

    public static String CurTime;

    public static CitysBean citysBean;

    public static CitysBean choosecitysBean;

    public static AppraisalLabelsBean appraisalLabelsBean;

    public static StudentUserInfoBean studentUserInfoBean;

    public static CheckStudentBean checkStudentBean;

    public static List<AreaBean> listAreas=new ArrayList<>();
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

    public static int getDateCha(String startTime,String endTime){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date  begin = dfs.parse(startTime);
            java.util.Date end = dfs.parse(endTime);
            long diff = end.getTime()-begin.getTime();//这样得到的差值是微秒级别
            return (int) (diff / (1000 * 60 * 60 * 24));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取用户标签
     * @param str
     * @return
     */
    public static String getLables(String str){
        String lables="";
        str=","+str+",";
        if (appraisalLabelsBean!=null&&appraisalLabelsBean.getDefaultAList()!=null) {
            for (int i = 0; i < appraisalLabelsBean.getDefaultAList().size(); i++) {
                if (str.indexOf("," + appraisalLabelsBean.getDefaultAList().get(i).getLabelid() + ",") > -1) {
                    lables = lables + appraisalLabelsBean.getDefaultAList().get(i).getLabelcontent() + ",";
                }
            }
        }

        if (!StringUtils.isEmpty(lables)){
            lables=lables.substring(0,lables.length()-1);
        }
        return lables;
    }

    /**
     * 时间比较
     * @return
     */
    public static int getStatus(String startTime,String middleTime,String endTime){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date  begin = dfs.parse(startTime);
            java.util.Date  middle = dfs.parse(middleTime);
            java.util.Date end = dfs.parse(endTime);

            if (end.getTime()-middle.getTime()<0){
                return -1;
            }else if (middle.getTime()-begin.getTime()>=0&&end.getTime()-middle.getTime()>=0){
                return 1;
            }else if (middle.getTime()-begin.getTime()<0){
                return 2;
            }else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getPinYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        char[] input = inputString.trim().toCharArray();
        StringBuffer output = new StringBuffer("");

        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output.append(temp[0]);
                    output.append(" ");
                } else
                    output.append(Character.toString(input[i]));
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output.toString();
    }


    /**
     * 获取城市code
     * @param city
     * @return
     */
    public static String getCode(String city){

        if (Contact.citysBean!=null){
            if (Contact.citysBean.getProvince()!=null) {
                for (int i = 0; i < Contact.citysBean.getProvince().size(); i++) {
                   /* if (Contact.citysBean.getProvince().get(i).getAddName().equals(city)){
                        return Contact.citysBean.getProvince().get(i).getCode();
                    }*/
                    if (Contact.citysBean.getProvince().get(i).getCity()!=null){
                        for (int j=0;j<Contact.citysBean.getProvince().get(i).getCity().size();j++){
                            if (Contact.citysBean.getProvince().get(i).getCity().get(j).getAddName().equals(city)){
                                return Contact.citysBean.getProvince().get(i).getCity().get(j).getCode();
                            }
                            if (Contact.citysBean.getProvince().get(i).getCity().get(j).getArea()!=null){
                                for (int z=0;z<Contact.citysBean.getProvince().get(i).getCity().get(j).getArea().size();z++){
                                    if (Contact.citysBean.getProvince().get(i).getCity().get(j).getArea().get(z).getAddName().equals(city)){
                                        return Contact.citysBean.getProvince().get(i).getCity().get(j).getArea().get(z).getCode();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    /**
     * 将图片联合路径处理成单个
     *
     * @param imgs
     * @return
     */
    public static List<String> getPhotos(String imgs) {
        List<String> listphotos = new ArrayList<String>();
        int index = imgs.indexOf(",");
        String imgurl;
        while (index > -1) {
            imgurl = imgs.substring(0, index);
            listphotos.add(imgurl);
            Log.e("imgUrl==>", imgurl);
            imgs = imgs.substring(index + 1);
            index = imgs.indexOf(",");
        }
        Log.e("imgs==>", imgs);
        listphotos.add(imgs);
        return listphotos;
    }


    public static void get(final String locationName, final Context context, final TextView textView){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    List<Address> addressList = geocoder.getFromLocationName(locationName, 5);
                    if (addressList != null && addressList.size() > 0) {
                        Log.e("lat-lng->", addressList.get(0).getLatitude()+"-"+addressList.get(0).getLongitude())  ;
                        int lng = (int) (addressList.get(0).getLongitude() * 1E6);
                        textView.append(" 相距"+getDistance(addressList.get(0).getLongitude(),addressList.get(0).getLatitude(),Longitude,Latitude)+"km");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static final double EARTH_RADIUS = 6378137.0;
    public static String getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000.0/1000.0;
        DecimalFormat df=new DecimalFormat("#.00");

        return df.format(s);
    }
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static boolean isNetworkAvailable(Context context)
    {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
