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
import net.xxtime.bean.SendMsgCodeBean;
import net.xxtime.utils.SharedUtils;

public class AlterPwdActivity extends BaseActivity {

    private EditText etOldPwd ,etNewPwd, etconfirmPwd ;
    private Button btnOk;

    private Message msg;


    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 1:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);

                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        SharedUtils.setUserNamePwd(AlterPwdActivity.this, SharedUtils.getUserName(AlterPwdActivity.this),
                                etNewPwd.getText().toString(),"");
                        ToastUtils.show(AlterPwdActivity.this,"修改密码成功");
                        intent=new Intent(AlterPwdActivity.this,LoginActivity.class);
                        /**
                         * 顶部跳转结束之前所有Activity
                         */
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Jump(intent);
                    }else {
                        ToastUtils.show(AlterPwdActivity.this,"修改密码失败");
                    }

                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_alter_pwd);
    }

    @Override
    public void initViews() {
        etOldPwd =(EditText)findViewById(R.id.etOldPwd);
        etNewPwd  =(EditText)findViewById(R.id.etNewPwd);
        etconfirmPwd  =(EditText)findViewById(R.id.etconfirmPwd);
        btnOk =(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        setTitle("修改密码");
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
                if (StringUtils.isEmpty(etOldPwd.getText().toString())){
                    ToastUtils.show(this,"请输入原密码");
                    return;
                }
                if (StringUtils.isEmpty(etNewPwd.getText().toString())){
                    ToastUtils.show(this,"请输入新密码");
                    return;
                }
                if (StringUtils.isEmpty(etconfirmPwd.getText().toString())){
                    ToastUtils.show(this,"请再次输入新密码");
                    return;
                }

                if(!etconfirmPwd.getText().toString().equals(etNewPwd.getText().toString())){
                    ToastUtils.show(this,"请保持密码一致");
                    return;
                }
                params=new RequestParams();
                params.put("reqCode","modifyStudentUserPassword");
                params.put("userid",SharedUtils.getUserId(this));
                params.put("oldPassword",etOldPwd.getText().toString());
                params.put("password",etNewPwd.getText().toString());
                post("studentUser",params,"modifyStudentUserPassword");
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("modifyStudentUserPassword")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
