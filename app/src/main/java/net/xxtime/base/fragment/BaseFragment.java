package net.xxtime.base.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longtu.base.notice.InitListener;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.activity.HomeActivity;
import net.xxtime.listener.ReceiveListener;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public abstract class BaseFragment extends Fragment implements InitListener,OnClickListener,ReceiveListener {
    
    public View view;
    
    public int layout;
    
    public Intent intent;
    
    public RequestParams params;

    public static final int CITYCHOOSE=1;
    
    public HomeActivity homeActivity;
    private final static String QINIU_URL="http://7sbsl4.com1.z0.glb.clouddn.com/";
    private ImageView ivBack;
    public TextView tvTitle;
    private ImageView ivRight;
     
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setContentView();
       
        view=inflater.inflate(layout, null);
        
        homeActivity=(HomeActivity) getActivity();
       
        initViews();

        initHead();
        
        initDatas();
        
        setDatas();
        
        setListener();

        
        return view;
    }

    public void loading(ImageView iv){
        iv.setImageResource(R.drawable.loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();
    }

    public  void initHead(){
        ivBack=(ImageView)view.findViewById(R.id.ivBack);
        tvTitle=(TextView)view.findViewById(R.id.tvTitle);
        ivRight=(ImageView)view.findViewById(R.id.ivRight);
        if (ivBack!=null) {
            ivBack.setVisibility(View.GONE);
        }
        if (ivRight!=null) {
            ivRight.setOnClickListener(this);
        }

        if (tvTitle!=null) {
            tvTitle.setOnClickListener(this);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        if (tvTitle!=null)
        tvTitle.setText(title);
    }

    /**
     * 设置右边是否显示
     * @param visible
     */
    public void setRightVisibility(int visible){
        if (ivRight!=null)
            ivRight.setVisibility(visible);
    }

    public void setRightResource(int id){
        if (ivRight!=null)
            ivRight.setImageResource(id);
    }


    public void post(final String requestname, RequestParams params,final String requestCode){

        homeActivity.httpClient.post(getActivity(), homeActivity.BASE_URL+requestname+".h8", params,  new AsyncHttpResponseHandler()
        {

            @Override
            public void onStart() {
                homeActivity.show();
            }

            @Override
            public void onSuccess(int statusCode , Header[] headers, byte[] responseBody) {
                Log.e(requestname, new String(responseBody));
                Log.e("statusCode==>", statusCode+"");
                Receive(requestCode, new String(responseBody));
                homeActivity.disMiss();
                for (Header h : headers) {
                    Log.e(h.getName(), h.getValue());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("statusCode==>", statusCode+"");
                if(statusCode==0){
                    ToastUtils.show(getActivity(), "请检查你的网络状况");
                }
               homeActivity. disMiss();
            }

        });

    }

    public  void pullpost(final String requestname, RequestParams params,final String requestCode){

        homeActivity.httpClient.post(getActivity(), homeActivity.BASE_URL+requestname+".h8", params,  new AsyncHttpResponseHandler()
        {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("responseBody==>",new String(responseBody));

                Receive(requestCode, new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("onFailure==>",statusCode+"");
                if(statusCode==0){
                    ToastUtils.show(getActivity(), "请检查你的网络状况");
                }
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
            }


        });
    }



    @Override
    public void onResume() {
        ResumeDatas();
        super.onResume();
    }
    
}
