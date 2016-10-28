package net.xxtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.longtu.base.util.ToastUtils;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.utils.SharedUtils;

public class SettingActivity extends BaseActivity {

    private RelativeLayout  rlMobile, rlPwd ,rlFeedback ,rlJoin, rlAbout;
    private Button btnQuit;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initViews() {
        rlMobile =(RelativeLayout)findViewById(R.id.rlMobile);
        rlPwd  =(RelativeLayout)findViewById(R.id.rlPwd);
        rlFeedback  =(RelativeLayout)findViewById(R.id.rlFeedback);
        rlJoin =(RelativeLayout)findViewById(R.id.rlJoin);
        rlAbout =(RelativeLayout)findViewById(R.id.rlAbout);
        btnQuit=(Button)findViewById(R.id.btnQuit);
    }

    @Override
    public void initDatas() {
        setTitle("软件设置");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnQuit.setOnClickListener(this);
        rlMobile.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlFeedback.setOnClickListener(this);
        rlJoin.setOnClickListener(this);
        rlPwd.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlAbout://关于
                Jump(AboutActivity.class);
                break;
            case R.id.rlMobile://更换手机
                Jump(AlterMobileActivity.class);
                break;
            case R.id.rlFeedback://意见反馈
                Jump(FeedbackActivity.class);
                break;
            case R.id.rlJoin://加入我们
                Jump(JoinActivity.class);
                break;
            case R.id.rlPwd://修改密码
                Jump(AlterPwdActivity.class);
                break;
            case R.id.btnQuit://退出登录
                SharedUtils.setUserNamePwd(this, SharedUtils.getUserName(this),
                        SharedUtils.getUserPwd(this),"");
                SharedUtils.setToken(this,"");
                ToastUtils.show(this,"退出成功");
                intent=new Intent(this,LoginActivity.class);
                /**
                 * 顶部跳转结束之前所有Activity
                 */
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                Jump(intent);
                break;
        }
    }
}
