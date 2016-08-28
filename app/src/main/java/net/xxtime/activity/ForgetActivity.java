package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import net.xxtime.bean.UserBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

public class ForgetActivity extends BaseActivity {
    private EditText etPhone ,etCode  ;
    private Button btnSend ,btnNext;

    private Message msg;

    private SendMsgCodeBean sendMsgCodeBean;

    private int second=60;

    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    sendMsgCodeBean=JSONObject.parseObject(msg.obj.toString(),SendMsgCodeBean.class);
                    if (sendMsgCodeBean!=null){
                        if (sendMsgCodeBean.getBflag().equals("1")){
                            btnSend.setEnabled(false);
                            btnNext.setEnabled(true);
                            btnNext.setBackgroundResource(R.drawable.btn_blue_seletor);
                            sendEmptyMessage(3);
                        }
                        ToastUtils.show(ForgetActivity.this,sendMsgCodeBean.getMsg());
                    }

                    break;
                case 2:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getMsg().indexOf("手机号已存在")>-1){
                        params=new RequestParams();
                        params.put("reqCode","sendMsgCode");
                        params.put("telephone",etPhone.getText().toString());
                        params.put("type",2);
                        pullpost("studentUser",params,"sendMsgCode");
                    }else{
                        ToastUtils.show(ForgetActivity.this,commonBean.getMsg());
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

            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void initViews() {
        etPhone =(EditText)findViewById(R.id.etPhone) ;
        etCode =(EditText)findViewById(R.id.etCode) ;
        btnSend =(Button) findViewById(R.id.btnSend) ;
        btnNext=(Button) findViewById(R.id.btnNext) ;
    }

    @Override
    public void initDatas() {
        setTitle("重置密码");
        btnNext.setEnabled(false);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnNext.setOnClickListener(this);
        btnSend.setOnClickListener(this);
//        etCode.addTextChangedListener(new EditTextWatcher(R.id.etCode));
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
            case R.id.btnNext:

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

                intent=new Intent(this,ResetActivity.class);
                intent.putExtra("telephone",etPhone.getText().toString());
                Jump(intent);

                break;
        }
    }

  /*  public class EditTextWatcher implements TextWatcher {
        int code;
        public EditTextWatcher(int i) {
            code = i;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            if (StringUtils.isEmpty(etCode.getText().toString())){
                btnNext.setEnabled(false);
                btnNext.setBackgroundResource(R.drawable.btn_garey);
            }else {
                btnNext.setEnabled(true);
                btnNext.setBackgroundResource(R.drawable.btn_blue_seletor);
            }
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }
    }
*/
    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("sendMsgCode")){
            msg.what=1;
        }else if (requestname.equals("checkStudentUserByPhone")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
