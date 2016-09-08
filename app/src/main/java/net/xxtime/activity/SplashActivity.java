package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.longtu.base.util.StringUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.AppraisalLabelsBean;
import net.xxtime.bean.AreaBean;
import net.xxtime.bean.CitysBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity implements AMapLocationListener {

    private Message msg;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private List<AreaBean> listAreas;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (StringUtils.isEmpty(SharedUtils.getUserId(SplashActivity.this))){
                        Jump(LoginActivity.class);
                    }else {
                        Jump(HomeActivity.class);
                    }
                    finish();
                    break;
                case 2:
                    Contact.appraisalLabelsBean=JSONObject.parseObject(msg.obj.toString(), AppraisalLabelsBean.class);
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

        params=new RequestParams();
        params.put("reqCode","loadAppraisalLabels");
        pullpost("studentUser",params,"loadAppraisalLabels");
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);

        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);

        if(mLocationOption.isOnceLocationLatest()){
            mLocationOption.setOnceLocationLatest(true);
            //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
            //如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会。
        }

        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(100);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        handler.sendEmptyMessageDelayed(1,500);

    }

    @Override
    public void setDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                listAreas=new ArrayList<AreaBean>();
                String json="";
                try{
                    InputStream in=getAssets().open("city.txt");
                    int size=in.available();
                    byte[] buffer=new byte[size];
                    in.read(buffer);
                    in.close();
                    json =new  String (buffer);
                }catch (Exception e){

                }
                Contact.citysBean= JSONObject.parseObject(json,CitysBean.class);
                if (Contact.citysBean!=null){
                    if (Contact.citysBean.getProvince()!=null) {
                        for (int i = 0; i < Contact.citysBean.getProvince().size(); i++) {
                            if (Contact.citysBean.getProvince().get(i).getCity()!=null){
                                for (int j=0;j<Contact.citysBean.getProvince().get(i).getCity().size();j++){

                                    Contact.citysBean.getProvince().get(i).getCity().get(j).Pinyin=Contact.getPinYin(
                                            Contact.citysBean.getProvince().get(i).getCity().get(j).getAddName()
                                    ).toUpperCase();

                                    if ( Contact.citysBean.getProvince().get(i).getCity().get(j).getAddName().indexOf("长治市")>-1||
                                            Contact.citysBean.getProvince().get(i).getCity().get(j).getAddName().indexOf("长沙市")>-1||
                                            Contact.citysBean.getProvince().get(i).getCity().get(j).getAddName().indexOf("重庆市")>-1||
                                            Contact.citysBean.getProvince().get(i).getCity().get(j).getAddName().indexOf("长春市")>-1){
                                        Contact.citysBean.getProvince().get(i).getCity().get(j).Pinyin="C"+Contact.citysBean.getProvince().get(i).getCity().get(j).Pinyin.substring(1);
                                    }

                                    listAreas.add( Contact.citysBean.getProvince().get(i).getCity().get(j));
                                   /* if (Contact.citysBean.getProvince().get(i).getCity().get(j).getArea()!=null){
                                        for (int z=0;z<Contact.citysBean.getProvince().get(i).getCity().get(j).getArea().size();z++){

                                        }
                                    }*/
                                }
                            }
                        }
                    }
                }

                for (int i=0;i<26;i++){
                    for (int j=0;j<listAreas.size();j++){
                        if (listAreas.get(j).Pinyin.charAt(0)=='A'+i){
                          //  Log.e("Areas==>",listAreas.get(j).getAddName()+"--"+listAreas.get(j).Pinyin);
                            Contact.listAreas.add(listAreas.get(j));
                            listAreas.remove(j);
                            j--;
                        }
                    }
                }
            }
        }).start();

    }

    @Override
    public void setListener() {

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
//                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                Contact.Latitude= amapLocation.getLatitude();//获取纬度
                Contact.Longitude=amapLocation.getLongitude();//获取经度
                Log.e("Latitude--Longitude-->",Contact.Latitude+"--"+Contact.Longitude);

               /* amapLocation.getAccuracy();//获取精度信息
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息*/
                Contact.City=amapLocation.getCity();//城市信息
               /* amapLocation.getDistrict();//城区信息
                amapLocation.getStreet();//街道信息
                amapLocation.getStreetNum();//街道门牌号信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码
                amapLocation.getAoiName();//获取当前定位点的AOI信息*/
                mLocationClient.stopLocation();//停止定位
                mLocationClient.onDestroy();//销毁定位客户端。
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError","location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("loadAppraisalLabels")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
