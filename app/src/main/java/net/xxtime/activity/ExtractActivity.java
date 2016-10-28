package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.StudentAccountBean;
import net.xxtime.bean.XxtimeBean;
import net.xxtime.utils.SharedUtils;

/**
 * 提现
 * 第二版本
 */
public class ExtractActivity extends BaseActivity {

    private EditText etAccount;
    private TextView tvTpye;
    private Button btnOk;

    private EditText etName ,etNumber, etBank;
    private LinearLayout rlBank;

    private XxtimeBean xxtimeBean;
    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 1:
                    xxtimeBean=JSONObject.parseObject(msg.obj.toString(),XxtimeBean.class);

                    if (xxtimeBean!=null&&xxtimeBean.getStatus().equals("1")){
                        MymoneyActivity.balance=MymoneyActivity.balance-Integer.valueOf(etAccount.getText().toString());
                        finish();
                    }

                    ToastUtils.show(ExtractActivity.this,xxtimeBean.getMsg());
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
        etName =(EditText)findViewById(R.id.etName);
        etNumber =(EditText)findViewById(R.id.etNumber);
        etBank=(EditText)findViewById(R.id.etBank);
        rlBank=(LinearLayout) findViewById(R.id.rlBank);
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

    private   int channel=1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTpye:
                intent=new Intent(this,SelectPayActivity.class);
                Jump(intent,PAY);
                break;
            case R.id.btnOk:
                if (StringUtils.isEmpty(tvTpye.getText().toString())){
                    ToastUtils.show(this,"请选择提现方式");
                    return;
                }

                if (StringUtils.isEmpty(etName.getText().toString())){
                    ToastUtils.show(this,"请输入用户名或姓名");
                    return;
                }

                if (StringUtils.isEmpty(etNumber.getText().toString())){
                    ToastUtils.show(this,"请输入支付宝、微信账号或银行卡卡号");
                    return;
                }

                if (StringUtils.isEmpty(etAccount.getText().toString())){
                    ToastUtils.show(this,"请输入提现金额");
                    return;
                }

                if (tvTpye.getText().toString().equals("银行卡")){
                    if (StringUtils.isEmpty(etBank.getText().toString())){
                        ToastUtils.show(this,"请输入开户行名称");
                        return;
                    }
                }

                if (Integer.valueOf(etAccount.getText().toString())>MymoneyActivity.balance){
                    ToastUtils.show(this,"提现方式金额不足，账号余额"+MymoneyActivity.balance+"元");
                    return;
                }

                params=new RequestParams();
                params.put("accessToken",SharedUtils.getToken(this));
                params.put("withdraw.name",etName.getText().toString());
                params.put("withdraw.channel",tvTpye.getText().toString());
                params.put("withdraw.account",etNumber.getText().toString());
                params.put("withdraw.amount",etAccount.getText().toString());
                if (tvTpye.getText().toString().equals("银行卡")){
                    params.put("withdraw.banks",etBank.getText().toString());
                }
                post("withdraw!save",params);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PAY&&resultCode==SelecctPAY){
            tvTpye.setText(data.getStringExtra("name"));
            if (tvTpye.getText().toString().equals("银行卡")){
                rlBank.setVisibility(View.VISIBLE);
            }else {
                rlBank.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("withdraw!save")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
