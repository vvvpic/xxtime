package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.XxtimeBean;
import net.xxtime.utils.SharedUtils;

public class FeedbackActivity extends BaseActivity {

    private EditText etContent;
    private Button btnOk;

    private Message msg;
    private XxtimeBean xxtimeBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 1:
                    xxtimeBean= JSONObject.parseObject(msg.obj.toString(),XxtimeBean.class);

                    if (xxtimeBean!=null&&xxtimeBean.getStatus().equals("1")){
                       finish();
                    }
                    ToastUtils.show(FeedbackActivity.this,xxtimeBean.getMsg());

                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void initViews() {
        etContent  =(EditText)findViewById(R.id.etContent);
        btnOk =(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        setTitle("意见反馈");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnOk.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk:

                if (StringUtils.isEmpty(etContent.getText().toString())){
                    ToastUtils.show(this,"请输入反馈内容");
                    return;
                }

              /*  params=new RequestParams();
                params.put("reqCode","addFeedbackInfo");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("content",etContent.getText().toString());
                post("studentUser",params,"addFeedbackInfo");*/

                params=new RequestParams();
                params.put("accessToken",SharedUtils.getToken(this));
                params.put("feedback.content",etContent.getText().toString());
                post("feedback!save",params);

                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("feedback!save")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
