package net.xxtime.activity;

import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.fragmet.HomeFragment;
import net.xxtime.fragmet.JobFragment;
import net.xxtime.fragmet.MsgFragment;
import net.xxtime.fragmet.MyFragment;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

/***
 * 首页
 */
public class HomeActivity extends BaseActivity implements AMapLocationListener {

    private LinearLayout llHome, llJob, llMsg ,llMy;
    private ImageView ivHome, ivJob, ivMsg, ivMy;
    private TextView tvHome, tvJob, tvMsg, tvMy;

    private HomeFragment homeFragment;
    private JobFragment jobFragment;
    private MsgFragment msgFragment;
    private MyFragment myFragment;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initViews() {
        llHome =(LinearLayout)findViewById(R.id.llHome) ;
        llJob =(LinearLayout)findViewById(R.id.llJob) ;
        llMsg  =(LinearLayout)findViewById(R.id.llMsg) ;
        llMy   =(LinearLayout)findViewById(R.id.llMy) ;
        ivHome =(ImageView) findViewById(R.id.ivHome) ;
        ivJob  =(ImageView)findViewById(R.id.ivJob) ;
        ivMsg  =(ImageView)findViewById(R.id.ivMsg) ;
        ivMy   =(ImageView)findViewById(R.id.ivMy) ;
        tvHome =(TextView) findViewById(R.id.tvHome) ;
        tvJob  =(TextView) findViewById(R.id.tvJob) ;
        tvMsg  =(TextView) findViewById(R.id.tvMsg) ;
        tvMy  =(TextView) findViewById(R.id.tvMy) ;
    }

    @Override
    public void initDatas() {
        setReplace(1);
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
        mLocationOption.setInterval(5000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        llHome.setOnClickListener(this);
        llJob.setOnClickListener(this);
        llMsg.setOnClickListener(this);
        llMy.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llHome:
                setReplace(1);
                setButtom(ivHome,tvHome,R.mipmap.home_p);
                break;
            case R.id.llJob:
                setReplace(2);
                setButtom(ivJob,tvJob,R.mipmap.job_p);
                break;
            case R.id.llMsg:
                setReplace(3);
                setButtom(ivMsg,tvMsg,R.mipmap.msg_p);
                break;
            case R.id.llMy:
                setReplace(4);
                setButtom(ivMy,tvMy,R.mipmap.my_p);
                break;
        }
    }

    private void setButtom(ImageView iv,TextView textView,int id){
        ivHome.setImageResource(R.mipmap.home_n);
        ivJob.setImageResource(R.mipmap.job_n);
        ivMy.setImageResource(R.mipmap.my_n);
        ivMsg.setImageResource(R.mipmap.msg_n);
        tvHome.setTextColor(getResources().getColor(R.color.txt_999));
        tvJob.setTextColor(getResources().getColor(R.color.txt_999));
        tvMy.setTextColor(getResources().getColor(R.color.txt_999));
        tvMsg.setTextColor(getResources().getColor(R.color.txt_999));
        iv.setImageResource(id);
        textView.setTextColor(getResources().getColor(R.color.blue));
    }

    @Override
    public void setReplace(int index) {
        transaction=fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index){
            case 1:
                if (homeFragment != null) {
                    transaction.show(homeFragment);
//                    serviceFragment.ResumeDatas();
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                }
                else {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.rlContent, homeFragment);
                }
                break;
            case 2:
                if (jobFragment != null) {
                    transaction.show(jobFragment);
                    jobFragment.Refresh();
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                }
                else {
                    jobFragment = new JobFragment();
                    transaction.add(R.id.rlContent, jobFragment);
                }

                break;
            case 3:
                if (msgFragment != null) {
                    transaction.show(msgFragment);
                    msgFragment.Refresh();
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                }else {
                    msgFragment = new MsgFragment();
                    transaction.add(R.id.rlContent, msgFragment);
                }
                break;
            case 4:
                if (myFragment != null)
                    transaction.show(myFragment);
                    // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    myFragment = new MyFragment();
                    transaction.add(R.id.rlContent, myFragment);
                }
                break;
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    // 当fragment已被实例化，就隐藏起来
    public void hideFragments(FragmentTransaction transaction) {

        if (msgFragment != null)
            transaction.hide(msgFragment);
        if (homeFragment != null)
            transaction.hide(homeFragment);
        if (myFragment != null)
            transaction.hide(myFragment);
        if (jobFragment != null)
            transaction.hide(jobFragment);
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
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
        super.onDestroy();
    }
}
