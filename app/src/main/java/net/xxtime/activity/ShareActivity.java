package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.ShareWayBean;
import net.xxtime.utils.SharedUtils;

public class ShareActivity extends BaseActivity {

    private ImageView ivQr;
    private Button btnShare;

    private TextView tvRight;

    private Message msg;

    private ShareWayBean shareWayBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    shareWayBean= JSONObject.parseObject(msg.obj.toString(),ShareWayBean.class);
                    if (shareWayBean!=null&&shareWayBean.getBflag().equals("1")){
                        setShare();
                    }
                    break;
            }
        }
    };

    private void setShare(){
        if (!StringUtils.isEmpty(shareWayBean.getDefaultAList().get(0).getQRcodeUrl())){
            ImageLoader.getInstance().displayImage(shareWayBean.getDefaultAList().get(0).getQRcodeUrl(),ivQr);
        }

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_share);
    }

    @Override
    public void initViews() {
        ivQr =(ImageView)findViewById(R.id.ivQr);
        btnShare =(Button) findViewById(R.id.btnShare);
        tvRight=(TextView)findViewById(R.id.tvRight);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("邀请记录");
    }

    @Override
    public void initDatas() {
        setTitle("分享好友");
        params = new RequestParams();
        params.put("reqCode", "getShareWay");
        params.put("userid", SharedUtils.getUserId(this));
        pullpost("studentUser", params, "getShareWay");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnShare.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivQr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRight:

                break;
            case R.id.btnShare:

                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getShareWay")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
