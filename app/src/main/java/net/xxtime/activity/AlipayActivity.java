package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import net.xxtime.bean.StudentAccountBean;
import net.xxtime.utils.SharedUtils;

/**
 * 支付宝绑定
 */
public class AlipayActivity extends BaseActivity {

    private EditText etAlipay, etName;
    private Button btnOk;

    private StudentAccountBean studentAccountBean;
    private CommonBean commonBean;
    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    studentAccountBean= JSONObject.parseObject(msg.obj.toString(),StudentAccountBean.class);
                    if (studentAccountBean!=null&&studentAccountBean.getBflag().equals("1")){
                        setAlipay();
                    }
                    break;
                case 2:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);

                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        finish();
                    }

                    ToastUtils.show(AlipayActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };

    private void setAlipay(){
        if (studentAccountBean.getDefaultAList()!=null){
            int p=studentAccountBean.getDefaultAList().size()-1;
            if (!StringUtils.isEmpty(studentAccountBean.getDefaultAList().get(p).getAccountname())){
                etName.setText(studentAccountBean.getDefaultAList().get(p).getAccountname());
            }

            if (!StringUtils.isEmpty(studentAccountBean.getDefaultAList().get(p).getAccountid())){
                etAlipay.setText(studentAccountBean.getDefaultAList().get(p).getAccountid());
                btnOk.setText("修改");
            }

        }
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_alipay);
    }

    @Override
    public void initViews() {
        etAlipay =(EditText)findViewById(R.id.etAlipay);
        etName  =(EditText)findViewById(R.id.etName);
        btnOk =(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        params=new RequestParams();
        params.put("reqCode","getAccount");
        params.put("userid", SharedUtils.getUserId(this));
        params.put("type", 1);
        Log.e("param==>",params.toString());
        post("studentAccount",params,"getAccount");
    }

    @Override
    public void setDatas() {
        setTitle("绑定支付宝");
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

                if (StringUtils.isEmpty(etAlipay.getText().toString())){
                    ToastUtils.show(this,"请输入支付宝号");
                    return;
                }

                if (StringUtils.isEmpty(etName.getText().toString())){
                    ToastUtils.show(this,"请输入支付宝姓名");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","bindingAccount");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("type", 1);
                params.put("accountid", etAlipay.getText().toString());
                params.put("accountname", etName.getText().toString());
                Log.e("param==>",params.toString());
                post("studentAccount",params,"bindingAccount");

                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getAccount")){
            msg.what=1;
        }else if (requestname.equals("bindingAccount")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
