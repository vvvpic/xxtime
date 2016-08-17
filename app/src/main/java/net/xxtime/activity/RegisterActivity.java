package net.xxtime.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.SendMsgCodeBean;
import net.xxtime.bean.UserBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

/**
 * 注册页
 */
public class RegisterActivity extends BaseActivity {

    private EditText etPhone ,etCode ,etPwd ;
    private Button btnSend ,btnRegister;
    private TextView tvAgree;
    private TextView tvAgreement;

    private Message msg;

    private CommonBean commonBean;
    private SendMsgCodeBean sendMsgCodeBean;
    private UserBean userBean;

    private int second=60;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        params=new RequestParams();
                        params.put("reqCode","sendMsgCode");
                        params.put("telephone",etPhone.getText().toString());
                        params.put("type",1);
                        pullpost("studentUser",params,"sendMsgCode");
                    }else{
                        ToastUtils.show(RegisterActivity.this,commonBean.getMsg());
                    }

                    break;
                case 2:
                    sendMsgCodeBean=JSONObject.parseObject(msg.obj.toString(),SendMsgCodeBean.class);
                    if (sendMsgCodeBean!=null){
                         if (sendMsgCodeBean.getBflag().equals("1")){
                             btnSend.setEnabled(false);
                             sendEmptyMessage(3);
                         }
                        ToastUtils.show(RegisterActivity.this,sendMsgCodeBean.getMsg());
                    }

                    break;

                case 3:
                    if (second==0){
                        btnSend.setText("发送验证码");
                        second=60;
                        btnSend.setEnabled(true);
                        return;
                    }
                    second--;
                    btnSend.setText(second+"s");
                    sendEmptyMessageDelayed(3,1000);
                    break;
                case 4:
                    userBean=JSONObject.parseObject(msg.obj.toString(),UserBean.class);
                    if (userBean!=null&&userBean.getBflag().equals("1")){
                        intent=new Intent(RegisterActivity.this,HomeActivity.class);
                        /**
                         * 顶部跳转结束之前所有Activity
                         */
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Jump(intent);
                        SharedUtils.setUserNamePwd(RegisterActivity.this,etPhone.getText().toString(),etPwd.getText().toString(),userBean.getDefaultAList().get(0).getUserid());

                    }
                    ToastUtils.show(RegisterActivity.this,userBean.getMsg());
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initViews() {
        etPhone =(EditText)findViewById(R.id.etPhone) ;
        etCode =(EditText)findViewById(R.id.etCode) ;
        etPwd =(EditText)findViewById(R.id.etPwd) ;
        btnSend =(Button) findViewById(R.id.btnSend) ;
        btnRegister =(Button) findViewById(R.id.btnRegister) ;
        tvAgree=(TextView) findViewById(R.id.tvAgree) ;
        tvAgreement=(TextView)findViewById(R.id.tvAgreement);
    }

    @Override
    public void initDatas() {
        setTitle("注册");
        tvAgree.setTag(1);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnRegister.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        tvAgree.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    private int choose=1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAgree:
                choose= (int) tvAgree.getTag();
                if (choose==1){
                   tvAgree.setTag(0);
                   setChoose(R.mipmap.agree_n);
                }else {
                    tvAgree.setTag(1);
                    setChoose(R.mipmap.agree_p);
                }
                break;
            case R.id.btnSend:
                if (StringUtils.isEmpty(etPhone.getText().toString())){
                    ToastUtils.show(this,"请输入手机号");
                    return;
                }

                if(!Contact.isMobileNO(etPhone.getText().toString())){
                    ToastUtils.show(this,"手机号格式不正确");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","checkStudentUserByPhone");
                params.put("telephone",etPhone.getText().toString());
                pullpost("studentUser",params,"checkStudentUserByPhone");

                break;
            case R.id.btnRegister:

                if (StringUtils.isEmpty(etPhone.getText().toString())){
                    ToastUtils.show(this,"请输入手机号");
                    return;
                }

                if(!Contact.isMobileNO(etPhone.getText().toString())){
                    ToastUtils.show(this,"手机号格式不正确");
                    return;
                }

                if (StringUtils.isEmpty(etCode.getText().toString())){
                    ToastUtils.show(this,"请输入验证码");
                    return;
                }

                if (sendMsgCodeBean!=null&&sendMsgCodeBean.getDefaultAList()!=null&&sendMsgCodeBean.getDefaultAList().size()>0){
                    if (!sendMsgCodeBean.getDefaultAList().get(0).getRandomCode().equals(etCode.getText().toString())){
                        ToastUtils.show(this,"请输入正确验证码");
                        return;
                    }
                }else {
                    ToastUtils.show(this,"请输入正确验证码");
                    return;
                }

                if (StringUtils.isEmpty(etPwd.getText().toString())){
                    ToastUtils.show(this,"请输入密码");
                    return;
                }

                choose= (int) tvAgree.getTag();
                if (choose==0){
                    ToastUtils.show(this,"请点击同意用户协议");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","register");
                params.put("telephone",etPhone.getText().toString());
                params.put("password",etPwd.getText().toString());
                pullpost("studentUser",params,"register");

                break;
            case R.id.tvAgreement:
                intent=new Intent(this,WebH5Activity.class);
                intent.putExtra("url","http://www.xxtime.net/ht-qysj/yonghuxieyi.html");
                intent.putExtra("title","用户注册协议");
                Jump(intent);
                break;
        }
    }

    private void setChoose(int id){
        Drawable nav_up=getResources().getDrawable(id);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        tvAgree.setCompoundDrawables(nav_up, null, null, null);
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("checkStudentUserByPhone")){
            msg.what=1;
        }else if (requestname.equals("sendMsgCode")){
            msg.what=2;
        }else if (requestname.equals("register")){
            msg.what=4;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
