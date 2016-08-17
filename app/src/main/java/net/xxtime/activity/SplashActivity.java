package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.utils.SharedUtils;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (StringUtils.isEmpty(SharedUtils.getUserId(SplashActivity.this))){
                        Jump(LoginActivity.class);
                    }else {
                        Jump(HomeActivity.class);
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {
        handler.sendEmptyMessageDelayed(1,500);

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
