package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
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
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

public class AlterMobileActivity extends BaseActivity {

    private TextView  tvMoblie;
    private EditText  etNewMobile ,etCode;
    private Button btnSend,  btnAlter;

    private Message msg;

    private SendMsgCodeBean sendMsgCodeBean;

    private int second=60;

    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    sendMsgCodeBean= JSONObject.parseObject(msg.obj.toString(),SendMsgCodeBean.class);
                    if (sendMsgCodeBean!=null){
                        if (sendMsgCodeBean.getBflag().equals("1")){
                            btnSend.setEnabled(false);
                            sendEmptyMessage(3);
                        }
                        ToastUtils.show(AlterMobileActivity.this,sendMsgCodeBean.getMsg());
                    }

                    break;
                case 2:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);

                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        SharedUtils.setUserNamePwd(AlterMobileActivity.this,etNewMobile.getText().toString(),
                                SharedUtils.getUserPwd(AlterMobileActivity.this),SharedUtils.getUserId(AlterMobileActivity.this));
                        ToastUtils.show(AlterMobileActivity.this,"更换手机号成功");
                        finish();
                    }else {
                        ToastUtils.show(AlterMobileActivity.this,"更换手机号失败");
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
        setContentView(R.layout.activity_atler_mobile);
    }

    @Override
    public void initViews() {
        tvMoblie =(TextView)findViewById(R.id.tvMoblie);
        etNewMobile  =(EditText) findViewById(R.id.etNewMobile);
        etCode  =(EditText) findViewById(R.id.etCode);
        btnSend   =(Button) findViewById(R.id.btnSend);
        btnAlter =(Button) findViewById(R.id.btnAlter);
    }

    @Override
    public void initDatas() {
        setTitle("更换手机号");

    }

    @Override
    public void setDatas() {
        tvMoblie.setText(SharedUtils.getUserName(this));
    }

    @Override
    public void setListener() {
        btnAlter.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSend:
                params=new RequestParams();
                params.put("reqCode","sendMsgCode");
                params.put("telephone",tvMoblie.getText().toString());
                params.put("type",2);
                post("studentUser",params,"sendMsgCode");
                break;
            case R.id.btnAlter:

                if (StringUtils.isEmpty(etNewMobile.getText().toString())){
                    ToastUtils.show(this,"请输入新手机号");
                    return;
                }

                if(!Contact.isMobileNO(etNewMobile.getText().toString())){
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
                params=new RequestParams();
                params.put("reqCode","modifyStudentUserInfo");
                params.put("userid",SharedUtils.getUserId(this));
                params.put("telephone",etNewMobile.getText().toString());
                pullpost("studentUser",params,"modifyStudentUserInfo");

                break;
             }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("sendMsgCode")){
            msg.what=1;
        }else if (requestname.equals("modifyStudentUserInfo")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
