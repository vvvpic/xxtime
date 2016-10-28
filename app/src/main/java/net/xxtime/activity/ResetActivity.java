package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import net.xxtime.bean.UseBean;
import net.xxtime.utils.SharedUtils;

public class ResetActivity extends BaseActivity {

    private String telephone;
    private EditText  etPwd ,etconfirmPwd ;
    private Button btnReset;

    private Message msg;

    private CommonBean commonBean;
    private UseBean userBean;
    private String Userid="";

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    userBean= JSONObject.parseObject(msg.obj.toString(),UseBean.class);
                   /* if (userBean!=null&&userBean.getBflag().equals("1")){
                       Userid=userBean.getDefaultAList().get(0).getUserid();
                    }*/
                    break;
                case 2:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        intent=new Intent(ResetActivity.this,LoginActivity.class);
                        /**
                         * 顶部跳转结束之前所有Activity
                         */
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Jump(intent);
//                        SharedUtils.setUserNamePwd(ResetActivity.this,telephone,etPwd.getText().toString(),userBean.getDefaultAList().get(0).getUserid());

                    }
                     ToastUtils.show(ResetActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_reset);
    }

    @Override
    public void initViews() {
        etPwd  =(EditText)findViewById(R.id.etPwd) ;
        etconfirmPwd  =(EditText)findViewById(R.id.etconfirmPwd) ;
        btnReset =(Button) findViewById(R.id.btnReset) ;
    }

    @Override
    public void initDatas() {
        telephone=getIntent().getStringExtra("telephone");
        setTitle("设置密码");

        params=new RequestParams();
        params.put("reqCode","getStudentUserIdByPhone");
        params.put("telephone",telephone);
        pullpost("studentUser",params,"getStudentUserIdByPhone");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnReset.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnReset:

                if (StringUtils.isEmpty(etPwd.getText().toString())){
                    ToastUtils.show(this,"请输入密码");
                    return;
                }

                if (StringUtils.isEmpty(etconfirmPwd.getText().toString())){
                    ToastUtils.show(this,"请输入确认密码");
                    return;
                }

                if (etconfirmPwd.getText().equals(etPwd.getText().toString())){
                    ToastUtils.show(this,"密码不一致");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","modifyStudentUserPassword");
                params.put("userid",Userid);
                params.put("password",etPwd.getText().toString());
                pullpost("studentUser",params,"modifyStudentUserPassword");
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getStudentUserIdByPhone")){
            msg.what=1;
        }else if (requestname.equals("modifyStudentUserPassword")){
            msg.what=2;
        }

        msg.obj=response;
        handler.sendMessage(msg);
    }
}
