package net.xxtime.activity;

import android.content.Intent;
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
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.StudentAccountBean;
import net.xxtime.utils.SharedUtils;

/**
 * 提现
 */
public class ExtractActivity extends BaseActivity {

    private EditText etAccount;
    private TextView tvTpye;
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
                        finish();
                    }

                    ToastUtils.show(ExtractActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_extract);
    }

    @Override
    public void initViews() {
        etAccount =(EditText)findViewById(R.id.etAccount);
        tvTpye  =(TextView) findViewById(R.id.tvTpye);
        btnOk =(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        setTitle("提现");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvTpye.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTpye:
                intent=new Intent(this,SelectPayActivity.class);
                Jump(intent,PAY);
                break;
            case R.id.btnOk:
                if (StringUtils.isEmpty(etAccount.getText().toString())){
                    ToastUtils.show(this,"请输入提现金额");
                    return;
                }

                if (StringUtils.isEmpty(tvTpye.getText().toString())){
                    ToastUtils.show(this,"请选择提现方式");
                    return;
                }

                int channel=1;

                if (tvTpye.getText().toString().equals("支付宝")){
                    channel=1;
                }else {
                    channel=2;
                }

                params=new RequestParams();
                params.put("reqCode","save");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("amount",etAccount.getText().toString());
                params.put("channel",channel);
                Log.e("param==>",params.toString());
                post("studentWithdraw",params,"save");

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PAY&&resultCode==SelecctPAY){
            tvTpye.setText(data.getStringExtra("name"));
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("save")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
