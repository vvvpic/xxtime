package net.xxtime.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.longtu.base.util.ToastUtils;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;

public class MymoneyActivity extends BaseActivity {

    private int balance,earnestmoney;
    private TextView tvMoney, tvAlipay, tvWeixin ,tvCyj, tvTrade;
    private Button btnCash;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_mymoney);
    }

    @Override
    public void initViews() {
        tvMoney =(TextView)findViewById(R.id.tvMoney);
        tvAlipay =(TextView)findViewById(R.id.tvAlipay);
        tvWeixin =(TextView)findViewById(R.id.tvWeixin);
        tvCyj =(TextView)findViewById(R.id.tvCyj);
        tvTrade =(TextView)findViewById(R.id.tvTrade);
        btnCash=(Button) findViewById(R.id.btnCash);
    }

    @Override
    public void initDatas() {
        setTitle("我的钱包");
        balance=getIntent().getIntExtra("balance",0);
        earnestmoney=getIntent().getIntExtra("earnestmoney",0);
    }

    @Override
    public void setDatas() {
        tvMoney.setText(Double.valueOf(balance)+"0");

    }

    @Override
    public void setListener() {
        tvAlipay.setOnClickListener(this);
        tvCyj.setOnClickListener(this);
        tvTrade.setOnClickListener(this);
        tvWeixin.setOnClickListener(this);
        btnCash.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAlipay:
                Jump(AlipayActivity.class);
                break;
            case R.id.tvWeixin:
                Jump(WeixinActivity.class);
                break;
            case R.id.tvCyj:
                intent=new Intent(this,SincerityActivity.class);
                intent.putExtra("earnestmoney",earnestmoney);
                Jump(intent);

                break;
            case R.id.tvTrade:
                Jump(TradeListActivity.class);
                break;
            case R.id.btnCash:
                if (balance>0) {
                    intent=new Intent(this,ExtractActivity.class);
                    intent.putExtra("balance",balance);
                    Jump(intent);
                }else {
                    ToastUtils.show(this,"余额不足");
                }
                break;
        }
    }
}
