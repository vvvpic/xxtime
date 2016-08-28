package net.xxtime.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.FileUtils;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.PhotoRAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.utils.ImageUtils;
import net.xxtime.utils.SharedUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class JoinActivity extends BaseActivity {

    private TextView tvCity;
    private EditText etContent;
    private Button btnOk;

    private Message msg;


    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 1:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);

                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        finish();
                    }
                    ToastUtils.show(JoinActivity.this,commonBean.getMsg());

                    break;

            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_join);
    }

    @Override
    public void initViews() {
        tvCity =(TextView) findViewById(R.id.tvCity);
        etContent  =(EditText)findViewById(R.id.etContent);
        btnOk =(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        setTitle("加入我们");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvCity.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCity:
                intent=new Intent(this,SelectAreaActivity.class);
                Jump(intent,AREA);
                break;
            case R.id.btnOk:
                if (StringUtils.isEmpty(city)){
                    ToastUtils.show(this,"请选择城市");
                    return;
                }
                if (StringUtils.isEmpty(etContent.getText().toString())){
                    ToastUtils.show(this,"请输入申请理由");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","applyCooperation");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("cooperateProvince",provinecode);
                params.put("cooperateCity",citycode);
                params.put("applyReason",etContent.getText().toString());
                post("studentUser",params,"applyCooperation");
                break;
        }
    }

    private String city;
    private String provinecode;
    private String citycode;
    private String areacode;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AREA&&resultCode==SELECTAREA){
            city=data.getStringExtra("city");
            provinecode=data.getStringExtra("provinecode");
            citycode=data.getStringExtra("citycode");
            areacode=data.getStringExtra("areacode");
            tvCity.setText(city);
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("applyCooperation")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

}
