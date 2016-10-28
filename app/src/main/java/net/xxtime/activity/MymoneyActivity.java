package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CardListBean;
import net.xxtime.utils.SharedUtils;

/***
 * 第二版修改版
 */
public class MymoneyActivity extends BaseActivity {

    public static int balance=0,earnestmoney=0;
    private TextView tvMoney, tvAlipay, tvWeixin ,tvCyj, tvTrade;
    private Button btnCash;
    private RelativeLayout  rlCyj, rlJy, rlTx;

    private CardListBean cardListBean;
    private Message msg;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    cardListBean= JSONObject.parseObject(msg.obj.toString(),CardListBean.class);
                    if (cardListBean!=null){
                        if (cardListBean.getStatus().equals("1")){
                            setAccount();
                        }else {
                            ToastUtils.show(MymoneyActivity.this,cardListBean.getMsg());
                        }
                    }
                    break;
            }
        }
    };

    private void setAccount(){
        tvMoney.setText(cardListBean.getCards().get(0).getMoney()+".00");
        earnestmoney=cardListBean.getCards().get(0).getBail();
        balance=cardListBean.getCards().get(0).getMoney();

    }

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
        rlCyj =(RelativeLayout) findViewById(R.id.rlCyj);
        rlJy =(RelativeLayout) findViewById(R.id.rlJy);
        rlTx=(RelativeLayout) findViewById(R.id.rlTx);
    }

    @Override
    public void initDatas() {
        setTitle("我的钱包");

    }

    @Override
    public void setDatas() {


    }

    @Override
    public void setListener() {
        tvAlipay.setOnClickListener(this);
        tvCyj.setOnClickListener(this);
        tvTrade.setOnClickListener(this);
        tvWeixin.setOnClickListener(this);
        btnCash.setOnClickListener(this);
        rlCyj.setOnClickListener(this);
        rlJy.setOnClickListener(this);
        rlTx.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        params=new RequestParams();
        params.put("accessToken", SharedUtils.getToken(this));
        params.put("query.userId",SharedUtils.getUserId(this));
        pullpost("card!list",params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlCyj:
                intent=new Intent(this,SincerityActivity.class);
                Jump(intent);
                break;
            case R.id.rlJy:
                Jump(TradeListActivity.class);
                break;
            case R.id.rlTx:

                if (balance>0) {
                    intent=new Intent(this,ExtractActivity.class);
                    Jump(intent);
                }else {
                    ToastUtils.show(this,"余额不足");
                }
                break;
            case R.id.tvAlipay:
                Jump(AlipayActivity.class);
                break;
            case R.id.tvWeixin:
                Jump(WeixinActivity.class);
                break;
            case R.id.tvCyj:
                intent=new Intent(this,SincerityActivity.class);
                Jump(intent);

                break;
            case R.id.tvTrade:
                Jump(TradeListActivity.class);
                break;
            case R.id.btnCash:
                if (balance>0) {
                    intent=new Intent(this,ExtractActivity.class);
                    Jump(intent);
                }else {
                    ToastUtils.show(this,"余额不足");
                }
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("card!list")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
