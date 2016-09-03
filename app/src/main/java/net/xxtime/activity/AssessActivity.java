package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.JobByCodeBean;
import net.xxtime.utils.SharedUtils;

/**
 * 评价
 */
public class AssessActivity extends BaseActivity {

    private int registerid;
    private String jobcode;

    private EditText etContent;
    private RatingBar rbAssess ;
    private Button btnOk;

    private CommonBean commonBean;
    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        intent=new Intent();
                        setResult(ADDASSESS,intent);
                        finish();
                    }
                    ToastUtils.show(AssessActivity.this,commonBean.getMsg());
                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_assess);
    }

    @Override
    public void initViews() {
        etContent=(EditText)findViewById(R.id.etContent);
        rbAssess =(RatingBar) findViewById(R.id.rbAssess);
        btnOk=(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        registerid=getIntent().getIntExtra("registerid",0);
        jobcode=getIntent().getStringExtra("jobcode");
        setTitle("评价");
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
                    ToastUtils.show(this,"请输入评价内容");
                    return;
                }

                if (rbAssess.getRating()==0){
                    ToastUtils.show(this,"请选择评价等级");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","addJobComment");
                params.put("registerid",registerid);
                params.put("content",etContent.getText().toString());
                params.put("jobcode",jobcode);
                params.put("level",rbAssess.getRating());
                params.put("userid", SharedUtils.getUserId(this));
                post("job",params,"addJobComment");
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("addJobComment")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
