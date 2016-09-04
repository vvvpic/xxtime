package net.xxtime.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.longtu.base.util.ToastUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.ConstantsUI;
import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import net.xxtime.R;
import net.xxtime.base.activity.XxtimeApplication;
import net.xxtime.bean.ShareBean;
import net.xxtime.bean.ShareWayBean;
import net.xxtime.utils.ImageUtils;

import org.json.JSONException;

import java.net.URL;

/**
 * Created by 唯图 on 2016/9/4.
 */
public class ShareDialog extends Dialog implements View.OnClickListener,IUiListener{

    private View view;
    /**
     * QQ
     */
    public Tencent tencent;
    public Bundle param;

    private LinearLayout  llShareQQ, llShareQzone, llShareWeixin, llShareWeixincircle;
    private Button btnCancel;

    private Context context;

    private ShareBean shareBean;

    public void setShare(ShareBean shareBean){
        this.shareBean=shareBean;
    }


    public ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
        view= LayoutInflater.from(context).inflate(R.layout.share_window,null);
        setContentView(view);
        llShareQQ=(LinearLayout)view.findViewById(R.id.llShareQQ);
        llShareQzone =(LinearLayout)view.findViewById(R.id.llShareQzone);
        llShareWeixin=(LinearLayout)view.findViewById(R.id.llShareWeixin);
        llShareWeixincircle =(LinearLayout)view.findViewById(R.id.llShareWeixincircle);
        btnCancel=(Button) view.findViewById(R.id.btnCancel);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        /***
         * QQ
         */
        tencent = Tencent.createInstance("1105651308", context);


        btnCancel.setOnClickListener(this);
        llShareQQ.setOnClickListener(this);
        llShareQzone.setOnClickListener(this);
        llShareWeixin.setOnClickListener(this);
        llShareWeixincircle.setOnClickListener(this);
    }

    public ShareDialog(Context context) {
        this(context,  R.style.loadingDialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancel:
                if (isShowing()){
                    dismiss();
                }
                break;
            case R.id.llShareQQ:
                param = new Bundle();
                param.putString(QQShare.SHARE_TO_QQ_TITLE, shareBean.title);// 标题
                param.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                        shareBean.url);// 连接地址
                param.putString(QQShare.SHARE_TO_QQ_SUMMARY,shareBean.SUMMARY); // 内容
                param.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareBean.IMAGE_URL); // 网络图片
                param.putString(QQShare.SHARE_TO_QQ_APP_NAME, shareBean.APP_NAME);
                param.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
//                param.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
                if (tencent != null) {
                    tencent.shareToQQ((Activity)context, param, this);
                } else {
                    tencent = Tencent.createInstance("1105651308", context);
                    tencent.shareToQQ((Activity)context, param, this);
                }
                dismiss();
                break;
            case R.id.llShareQzone:
                param = new Bundle();
                param.putString(QQShare.SHARE_TO_QQ_TITLE, shareBean.title);// 标题
                param.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                        shareBean.url);// 连接地址
                param.putString(QQShare.SHARE_TO_QQ_SUMMARY,shareBean.SUMMARY); // 内容
                param.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareBean.IMAGE_URL); // 网络图片
                param.putString(QQShare.SHARE_TO_QQ_APP_NAME, shareBean.APP_NAME);
                param.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                param.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
                if (tencent != null) {
                    tencent.shareToQQ((Activity)context, param, this);
                } else {
                    tencent = Tencent.createInstance("1105651308", context);
                    tencent.shareToQQ((Activity)context, param, this);
                }
                dismiss();
                break;
            case R.id.llShareWeixin:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            WXWebpageObject webpage = new WXWebpageObject();
                            webpage.webpageUrl = shareBean.url;
                            WXMediaMessage msg = new WXMediaMessage(webpage);
                            msg.title = shareBean.title;
                            msg.description = shareBean.SUMMARY;

                            Bitmap bmp = BitmapFactory.decodeStream(new URL(shareBean.IMAGE_URL).openStream());
                            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                            bmp.recycle();
                            msg.thumbData = ImageUtils.bmpToByteArray(thumbBmp, true);

                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.transaction = buildTransaction("webpage");
                            req.message = msg;
                            req.scene = SendMessageToWX.Req.WXSceneSession;
                            XxtimeApplication.api.sendReq(req);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                dismiss();
                break;
            case R.id.llShareWeixincircle:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            WXWebpageObject webpage = new WXWebpageObject();
                            webpage.webpageUrl = shareBean.url;
                            WXMediaMessage msg = new WXMediaMessage(webpage);
                            msg.title = shareBean.title;
                            msg.description = shareBean.SUMMARY;

                            Bitmap bmp = BitmapFactory.decodeStream(new URL(shareBean.IMAGE_URL).openStream());
                            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                            bmp.recycle();
                            msg.thumbData = ImageUtils.bmpToByteArray(thumbBmp, true);

                            SendMessageToWX.Req req = new SendMessageToWX.Req();
                            req.transaction = buildTransaction("webpage");
                            req.message = msg;
                            req.scene = SendMessageToWX.Req.WXSceneTimeline;
                            XxtimeApplication.api.sendReq(req);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                dismiss();
                break;
        }
    }


    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    @Override
    public void onComplete(Object values) {
        if (values == null) {
            ToastUtils.show(context, "分享失败！");
            return;
        }
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(values.toString());
            if (jsonObject.has("ret")) {
                if (jsonObject.getString("ret").equals("0")) {
                    ToastUtils.show(context, "分享成功！");
                } else {
                    ToastUtils.show(context, "分享失败！");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {
            ToastUtils.show(context, "取消分享！");
    }
}
