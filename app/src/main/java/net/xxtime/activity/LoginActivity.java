package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import net.xxtime.bean.UseBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity {
    private EditText etPhone, etPwd;
    private Button btnLogin;
    private TextView tvForget ,tvRegister;

    private Message msg;
    private UseBean userBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    userBean= JSONObject.parseObject(msg.obj.toString(),UseBean.class);
                    if (userBean!=null&&userBean.getStatus().equals("1")){
                        SharedUtils.setUserNamePwd(LoginActivity.this,etPhone.getText().toString(),etPwd.getText().toString(),userBean.getUser().getId());
                        SharedUtils.setToken(LoginActivity.this,userBean.getAccessToken().getAccessToken());
                        Jump(HomeActivity.class);
                        finish();
                    }
                    ToastUtils.show(LoginActivity.this,userBean.getMsg());
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initViews() {
        etPhone =(EditText)findViewById(R.id.etPhone) ;
        etPwd  =(EditText)findViewById(R.id.etPwd) ;
        btnLogin  =(Button) findViewById(R.id.btnLogin) ;
        tvForget  =(TextView) findViewById(R.id.tvForget) ;
        tvRegister =(TextView) findViewById(R.id.tvRegister) ;
    }

    @Override
    public void initDatas() {
        setTitle("闲暇时光登录");
        seBackVisibility(View.INVISIBLE);

        Log.e("SharedUtils==>",SharedUtils.getUserName(this)+"--"+SharedUtils.getUserPwd(this));

        if (!StringUtils.isEmpty(SharedUtils.getUserName(this))){
            etPhone.setText(SharedUtils.getUserName(this));
        }

        if (!StringUtils.isEmpty(SharedUtils.getUserPwd(this))){
            etPwd.setText(SharedUtils.getUserPwd(this));
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvForget.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        if (!StringUtils.isEmpty(SharedUtils.getUserId(this))){
          /*  intent=new Intent(this,HomeActivity.class);
            Jump(intent);
            finish();*/
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRegister:
                Jump(RegisterActivity.class);
                break;
            case R.id.btnLogin:
                if (StringUtils.isEmpty(etPhone.getText().toString())){
                    ToastUtils.show(this,"请输入手机号");
                    return;
                }
                if (StringUtils.isEmpty(etPwd.getText().toString())){
                    ToastUtils.show(this,"请输入密码");
                    return;
                }
                if(!Contact.isMobileNO(etPhone.getText().toString())){
                    ToastUtils.show(this,"手机号格式不正确");
                    return;
                }

                if (!Contact.isNetworkAvailable(this)){
                    ToastUtils.show(this,"请检查你的网络状况");
                    return;
                }

               /* params=new RequestParams();
                params.put("reqCode","login");
                params.put("telephone",etPhone.getText().toString());
                params.put("password",etPwd.getText().toString());
                post("studentUser",params,"login");*/

                params=new RequestParams();
                params.put("user.username",etPhone.getText().toString());
                params.put("user.password",etPwd.getText().toString());
                post("user!login",params);
                break;
            case R.id.tvForget:
                Jump(ForgetActivity.class);
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("user!login")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
