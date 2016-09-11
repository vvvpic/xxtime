package net.xxtime.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;
import com.pingplusplus.android.Pingpp;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.PingpayBean;
import net.xxtime.bean.StudentOrderBean;
import net.xxtime.utils.SharedUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * 诚意金充值
 */
public class RechargeActivity extends BaseActivity {

    private RelativeLayout  rlAlipay ;
    private ImageView ivAlipay ;
    private RelativeLayout rlAccount;
    private ImageView ivAccount;
    private Button btnOk;

    private int pay=1;
//    private int earnestmoney;

    private Message msg;

    private StudentOrderBean studentOrderBean;

    private CommonBean commonBean;

    private PingpayBean pingpayBean;

    private String charge_url;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    studentOrderBean= JSONObject.parseObject(msg.obj.toString(),StudentOrderBean.class);
                    if (studentOrderBean!=null&&studentOrderBean.getBflag().equals("1")){
                        params = new RequestParams();
                        params.put("reqCode", "pingpay");
                        params.put("order_type","studentOrder");
                        params.put("body", "诚意金充值");
                        params.put("subject", "诚意金充值"+SharedUtils.getUserId(RechargeActivity.this));
                        params.put("amount", 5000);
                       // params.put("price", 50);
                        if (pay==1) {
                            params.put("channel", "alipay");
                        }
                        params.put("order_no",studentOrderBean.getDefaultAList().get(0).getOrdercode());
                        RechargeActivity.this.post("pingpay", params, "pingpay");
                    }
                    break;
                case 2:
                  /*  pingpayBean=JSONObject.parseObject(msg.obj.toString(),PingpayBean.class);
                    if (pingpayBean!=null&&pingpayBean.getBflag().equals("1")){*/
                    try {
                        org.json.JSONObject jsonObject=new org.json.JSONObject(msg.obj.toString());
                        Intent intent = new Intent();
                        String packageName = getPackageName();
                        ComponentName componentName = new ComponentName(packageName, packageName + ".wxapi.WXPayEntryActivity");
                        intent.setComponent(componentName);
                        Log.e("defaultAList==>",jsonObject.getString("defaultAList").substring(1,jsonObject.getString("defaultAList").length()-1));
                        intent.putExtra(com.pingplusplus.android.PaymentActivity.EXTRA_CHARGE,jsonObject.getString("defaultAList").substring(1,jsonObject.getString("defaultAList").length()-1) );
                        startActivityForResult(intent, Pingpp.REQUEST_CODE_PAYMENT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                         }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_recharge);
    }

    @Override
    public void initViews() {
        rlAlipay =(RelativeLayout)findViewById(R.id.rlAlipay);
        ivAlipay =(ImageView) findViewById(R.id.ivAlipay);
        rlAccount=(RelativeLayout)findViewById(R.id.rlAccount);
        ivAccount=(ImageView) findViewById(R.id.ivAccount);
        btnOk=(Button) findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        setTitle("支付");
//        earnestmoney=getIntent().getIntExtra("earnestmoney",0);

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        rlAccount.setOnClickListener(this);
        rlAlipay.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
          case  R.id.rlAccount:
            setChoose(ivAccount,2);
            break;
           case  R.id.rlAlipay:
               setChoose(ivAlipay,1);
            break;
            case R.id.btnOk:
                params = new RequestParams();
                params.put("reqCode", "save");
                params.put("type",2);
                params.put("userid", SharedUtils.getUserId(this));
                params.put("price", 50);
                if (pay==1) {
                    params.put("channel", "alipay");
                }
                params.put("amount",50);
                pullpost("studentOrder", params, "save");
                break;
        }
    }

    private void setChoose(ImageView iv,int pay){
        this.pay=pay;
        ivAccount.setImageResource(R.mipmap.agree_n);
        ivAlipay.setImageResource(R.mipmap.agree_n);
        iv.setImageResource(R.mipmap.agree_p);
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("save")){
            msg.what=1;
        }else if (requestname.equals("pingpay")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }


    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                showMsg(result, errorMsg, extraMsg);
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }

        if (title.equals("success")){
            MymoneyActivity.earnestmoney=MymoneyActivity.earnestmoney+50;
            ToastUtils.show(this,"支付成功");
           finish();
        }else {
            ToastUtils.show(this,str);
        }

    }



}
