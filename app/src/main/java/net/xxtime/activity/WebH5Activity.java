package net.xxtime.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;

/**
 * 统一H5页面加载
 */
public class WebH5Activity extends BaseActivity {

    private ProgressBar pbCash;
    private WebView wvh5;

    private String url;
    private String title;

    private ImageView ivBack, ivRight;
    private TextView tvTitle;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_web);
    }

    @Override
    public void initViews() {
        ivBack =(ImageView)findViewById(R.id.ivBack);
        ivRight=(ImageView)findViewById(R.id.ivRight);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        pbCash=(ProgressBar) findViewById(R.id.pbCash);
        wvh5=(WebView) findViewById(R.id.Wvh5);
    }

    @Override
    public void initDatas() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        if (!StringUtils.isEmpty(title)){
            tvTitle.setText(title);
        }

        //支持javascript
        wvh5.getSettings().setJavaScriptEnabled(true);
        wvh5.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);// 支持通过JS打开新窗口
        // 设置可以支持缩放
        wvh5.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        wvh5.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        wvh5.getSettings().setUseWideViewPort(true);
        wvh5.getSettings().setAllowFileAccess(true);
        wvh5.getSettings().setLoadsImagesAutomatically(true);// 支持自动加载图片
        //自适应屏幕
        wvh5.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvh5.getSettings().setLoadWithOverviewMode(true);

        wvh5.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                Log.e("progress==>", progress + "");
                pbCash.setProgress(progress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url,
                                     String message, JsResult result) {
                result.confirm();

                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url,
                                       String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url,
                                            String message, JsResult result) {
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url,
                                      String message, String defaultValue,
                                      JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

        });

        wvh5.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

//                    view.loadUrl(url);
                if (url.indexOf("http://api199.yinwan.bangqu.com/steward/waiting")>-1){
                    finish();
                    return false;
                }
                intent=new Intent(WebH5Activity.this,WebH5Activity.class);
                intent.putExtra("url",url);
                intent.putExtra("title",title);
                Jump(intent);
                Log.e("url==>",url);

//              /*  if (url.startsWith("http:") || url.startsWith("https:")) {
//                    return false;
//                }
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);*/
                return false;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pbCash.setVisibility(View.VISIBLE);
            }

            public void onPageFinished(WebView view, String url) {
                pbCash.setVisibility(View.GONE);
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                pbCash.setVisibility(View.GONE);

            }
        });

        wvh5.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        if (!StringUtils.isEmpty(url))
            wvh5.loadUrl(url);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }
}
