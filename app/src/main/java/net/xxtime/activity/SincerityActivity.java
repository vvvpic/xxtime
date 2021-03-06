package net.xxtime.activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.XxtimeBean;
import net.xxtime.utils.SharedUtils;

/**
 * 第二版本
 */
public class SincerityActivity extends BaseActivity {

    private TextView  tvPrice;
    private Button btnTq, btnRecharge;

    private Dialog tqdialog;

    private XxtimeBean xxtimeBean;

    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

                case 1:
                    xxtimeBean= JSONObject.parseObject(msg.obj.toString(),XxtimeBean.class);

                    if (xxtimeBean!=null&&xxtimeBean.getStatus().equals("1")){
                        finish();
                    }

                    ToastUtils.show(SincerityActivity.this,xxtimeBean.getMsg());
                    break;
            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_sincerity);
    }

    @Override
    public void initViews() {
        tvPrice =(TextView)findViewById(R.id.tvPrice);
        btnTq =(Button) findViewById(R.id.btnTq);
        btnRecharge=(Button) findViewById(R.id.btnRecharge);
        initPerson();
    }

    @Override
    public void initDatas() {
        setTitle("诚意金");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnTq.setOnClickListener(this);
        btnRecharge.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        tvPrice.setText("诚意金："+Double.valueOf(MymoneyActivity.earnestmoney>0?MymoneyActivity.earnestmoney:0));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTq:
                if (MymoneyActivity.earnestmoney>0) {
                    tqdialog.show();
                }else {
                    ToastUtils.show(this,"诚意金余额不足，无法提现");
                }
                break;
            case R.id.btnRecharge:
                intent=new Intent(this,RechargeActivity.class);
//                intent.putExtra("earnestmoney",earnestmoney);
                Jump(intent);

                break;
            case R.id.btnCancel:
                tqdialog.dismiss();
                break;
            case R.id.btnOk:

                if (MymoneyActivity.earnestmoney>0) {
                    params = new RequestParams();
                    params.put("accessToken", SharedUtils.getToken(this));
                    params.put("amount", MymoneyActivity.earnestmoney);
                    post("card!bailToBalance", params);
                }else {
                    ToastUtils.show(this,"诚意金余额不足，无法提现");
                }
                tqdialog.dismiss();
                break;
        }
    }


    private Button  btnOk, btnCancel;
    private TextView tvDialogContent;
    private void initPerson() {
        tqdialog = new Dialog(this, R.style.loadingDialog);
        LinearLayout layout = new LinearLayout(this);

        layout.setBackgroundColor(getResources().getColor(
                R.color.transparent));
        View view = LayoutInflater.from(this).inflate(R.layout.personal_dialog, null);
        layout.addView(view);
        tqdialog.setContentView(layout);
        tqdialog.setCanceledOnTouchOutside(false);
        tqdialog.setCancelable(false);
        tvDialogContent =(TextView) view.findViewById(R.id.tvDialogContent);
        btnOk =(Button)view.findViewById(R.id.btnOk);
        btnCancel=(Button)view.findViewById(R.id.btnCancel);
        tvDialogContent.setText("诚意金提现后直接会到你的账户余额，是否继续？");
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("card!bailToBalance")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
