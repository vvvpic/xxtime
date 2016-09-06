package net.xxtime.activity;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.longtu.base.view.ScrollGridView;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.BusPAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.BusInfoBean;
import net.xxtime.bean.CommonBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司招聘首页
 */
public class FirmHomeActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private String buscode;
    private String busname;

    private Message msg;
    private BusInfoBean busInfoBean;

    private FrameLayout  flBring, flBringNumder, flAsses;
    private TextView tvBring, tvBringNumber, tvAsses, tvBusInfo;
    private GridView gvBusimgs;
    private BusPAdapter busPAdapter;
    private ScrollView svFirm;
    private List<String> listbus;
    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    busInfoBean= JSONObject.parseObject(msg.obj.toString(),BusInfoBean.class);
                    if (busInfoBean!=null&&busInfoBean.getBflag().equals("1")){
                        setBus();
                    }
                    break;
                case 2:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        ivRight.setImageResource(R.mipmap.ico_collect_p);
                        busInfoBean.getDefaultAList().get(0).setIsFocus(1);
                    }
                    ToastUtils.show(FirmHomeActivity.this,commonBean.getMsg());
                    break;
                case 3:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        ivRight.setImageResource(R.mipmap.ico_collect_n);
                        busInfoBean.getDefaultAList().get(0).setIsFocus(0);
                    }
                    ToastUtils.show(FirmHomeActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };

    private void setBus(){
        tvAsses.setText(busInfoBean.getDefaultAList().get(0).getHdpj()+"");
        tvBring.setText(busInfoBean.getDefaultAList().get(0).getZpgw()+"");
        tvBringNumber.setText(busInfoBean.getDefaultAList().get(0).getZprs()+"");

        if (!StringUtils.isEmpty(busInfoBean.getDefaultAList().get(0).getBuscontent())){
            tvBusInfo.setText(busInfoBean.getDefaultAList().get(0).getBuscontent());
        }else {
            tvBusInfo.setText("企业暂无介绍");
        }

        if (!StringUtils.isEmpty(busInfoBean.getDefaultAList().get(0).getBusimg())){
            listbus= Contact.getPhotos(busInfoBean.getDefaultAList().get(0).getBusimg());
            busPAdapter=new BusPAdapter(listbus,this);
            gvBusimgs.setAdapter(busPAdapter);
        }

        if (busInfoBean.getDefaultAList().get(0).getIsFocus()==1){
            ivRight.setImageResource(R.mipmap.ico_collect_p);
        }

        svFirm.smoothScrollTo(0,0);

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_firm_home);
    }

    @Override
    public void initViews() {
        flBring =(FrameLayout)findViewById(R.id.flBring);
        flBringNumder  =(FrameLayout)findViewById(R.id.flBringNumder);
        flAsses  =(FrameLayout)findViewById(R.id.flAsses);
        tvBring  =(TextView) findViewById(R.id.tvBring);
        tvBringNumber  =(TextView)findViewById(R.id.tvBringNumber);
        tvAsses  =(TextView)findViewById(R.id.tvAsses);
        tvBusInfo  =(TextView)findViewById(R.id.tvBusInfo);
        gvBusimgs =(ScrollGridView)findViewById(R.id.gvBusimgs);
        svFirm =(ScrollView) findViewById(R.id.svFirm);
    }

    @Override
    public void initDatas() {
        setRightResource(R.mipmap.ico_collect_n);
        setRightVisibility(View.VISIBLE);
        buscode=getIntent().getStringExtra("buscode");
        busname=getIntent().getStringExtra("busname");

        if (!StringUtils.isEmpty(busname)){
            setTitle(busname);
        }

        params=new RequestParams();
        params.put("reqCode","getBusInfoByBuscode");
        params.put("userid", SharedUtils.getUserId(this));
        params.put("buscode",buscode);
        post("job",params,"getBusInfoByBuscode");

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        gvBusimgs.setOnItemClickListener(this);
        flAsses.setOnClickListener(this);
        flBring.setOnClickListener(this);
        flBringNumder.setOnClickListener(this);

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flBring:
                intent=new Intent(this,RecruitActivity.class);
                if(busInfoBean!=null) {
                    intent.putExtra("buscode",busInfoBean.getDefaultAList().get(0).getBuscode());
                    intent.putExtra("welfare",busInfoBean.getDefaultAList().get(0).getGyCount());
                    intent.putExtra("social",busInfoBean.getDefaultAList().get(0).getJzCount());
                    intent.putExtra("bring",busInfoBean.getDefaultAList().get(0).getZpgw());
                }
                Jump(intent);
                break;
            case R.id.flBringNumder:
                intent=new Intent(this,RecruitNumberActivity.class);
                if(busInfoBean!=null) {
                    intent.putExtra("buscode",busInfoBean.getDefaultAList().get(0).getBuscode());
                }
                Jump(intent);
                break;
            case R.id.ivRight:
                params=new RequestParams();

                params.put("userid",SharedUtils.getUserId(this));
                params.put("type",1);
                if(busInfoBean!=null) {
                    params.put("code", busInfoBean.getDefaultAList().get(0).getBuscode());
                }

                if (busInfoBean.getDefaultAList().get(0).getIsFocus()==0) {
                    params.put("reqCode", "focusPosition");
                    post("userJob",params,"focusPosition");
                }else {
                    params.put("reqCode", "deleteFocusPosition");
                    post("userJob",params,"deleteFocusPosition");
                }

                break;
            case R.id.flAsses:
                intent=new Intent(this,CommentActivity.class);
                if(busInfoBean!=null) {
                    intent.putExtra("buscode",busInfoBean.getDefaultAList().get(0).getBuscode());
                }
                Jump(intent);
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getBusInfoByBuscode")){
            msg.what=1;
        }else if (requestname.equals("focusPosition")){
            msg.what=2;
        }else if (requestname.equals("deleteFocusPosition")){
            msg.what=3;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent = new Intent(this, PhotosFullActivity.class);
        intent.putExtra("position", position);
        intent.putStringArrayListExtra("urls", (ArrayList<String>) listbus);
        Jump(intent);
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
