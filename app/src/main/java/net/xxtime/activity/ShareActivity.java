package net.xxtime.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.ShareBean;
import net.xxtime.bean.ShareWayBean;
import net.xxtime.utils.ImageUtils;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.ShareDialog;

import java.io.File;

public class ShareActivity extends BaseActivity {

    private ImageView ivQr;
    private Button btnShare;

    private TextView tvRight;

    private Message msg;

    private ShareWayBean shareWayBean;

    private ShareDialog shareDialog;
    private ShareBean shareBean;

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
        shareDialog=new ShareDialog(this,R.style.loadingDialog);
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
                dialog();

                return false;
            }
        });
    }

    /***
     * 保存图片通知手机扫描
     */
    public void sdScan() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/chakeshe/"));
        intent.setData(uri);
        sendBroadcast(intent);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRight:
                Jump(InviteActivity.class);
                break;
            case R.id.btnShare:
                if (shareDialog!=null){
                    shareBean=new ShareBean();
                    shareBean.title="闲暇时光好友邀请";
                    shareBean.IMAGE_URL=shareWayBean.getDefaultAList().get(0).getQRcodeUrl();
                    shareBean.SUMMARY="我在玩闲暇时光，里面很多岗位，一起来玩吧";
                    shareBean.url=shareWayBean.getDefaultAList().get(0).getShareUrl();
                    shareDialog.setShare(shareBean);
                    shareDialog.show();
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, intent);

        /***
         * QQ
         */
        if (requestCode == Constants.REQUEST_QQ_SHARE) {
            Tencent.onActivityResultData(requestCode, resultCode, data, shareDialog);
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("保存图片到相册");  builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {   @Override
        public void onClick(DialogInterface dialog, int which) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (!StringUtils.isEmpty(shareWayBean.getDefaultAList().get(0).getQRcodeUrl())) {
                            Bitmap bitmap = ImageUtils.getImage(shareWayBean.getDefaultAList().get(0).getQRcodeUrl());

                            ImageUtils.saveFile(bitmap,
                                    shareWayBean.getDefaultAList().get(0).getQRcodeUrl().substring(
                                            shareWayBean.getDefaultAList().get(0).getQRcodeUrl()
                                                    .lastIndexOf("/") + 1),ShareActivity.this);
                            Log.e("load==>", "保存成功2！");

                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    ToastUtils.show(ShareActivity.this,
                                            "已经保存到手机");
                                    sdScan();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            dialog.dismiss();
        }
        });  builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {   @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
        });  builder.create().show();
    }
}
