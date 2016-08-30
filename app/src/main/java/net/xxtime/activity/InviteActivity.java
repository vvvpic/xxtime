package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.InviteAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.InviteRecordBean;
import net.xxtime.utils.SharedUtils;

public class InviteActivity extends BaseActivity {

    private ListView lvInvites;

    private Message msg;
    private InviteRecordBean inviteRecordBean;

    private InviteAdapter inviteAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    inviteRecordBean= JSONObject.parseObject(msg.obj.toString(),InviteRecordBean.class);
                    if (inviteRecordBean!=null&&inviteRecordBean.getBflag().equals("1")){
                        inviteAdapter=new InviteAdapter(inviteRecordBean.getDefaultAList(),InviteActivity.this);
                        lvInvites.setAdapter(inviteAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_invite);
    }

    @Override
    public void initViews() {
        lvInvites=(ListView)findViewById(R.id.lvInvites);
    }

    @Override
    public void initDatas() {
        setTitle("邀请记录");
        params=new RequestParams();
        params.put("reqCode","getInviteRecord");
        params.put("referrer", SharedUtils.getUserId(this));
        pullpost("studentUser",params,"getInviteRecord");
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

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getInviteRecord")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
