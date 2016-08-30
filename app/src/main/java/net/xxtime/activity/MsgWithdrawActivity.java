package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.MsgFollowAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.StudentUserMsgBean;
import net.xxtime.utils.SharedUtils;

public class MsgWithdrawActivity extends BaseActivity {

    private PullToRefreshListView plFollows;
    private ListView lvFollows;
    private RelativeLayout rlEmpty;

    private LinearLayout llChoose;
    private TextView tvChoose, tvDel;

    private MsgFollowAdapter msgFollowAdapter;

    private Message msg;
    private StudentUserMsgBean studentUserMsgBean;
    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    studentUserMsgBean= JSONObject.parseObject(msg.obj.toString(), StudentUserMsgBean.class);
                    if (studentUserMsgBean!=null&&studentUserMsgBean.getBflag().equals("1")){
                        if (studentUserMsgBean.getDefaultAList()!=null&&studentUserMsgBean.getDefaultAList().size()>0) {
                            msgFollowAdapter = new MsgFollowAdapter(studentUserMsgBean.getDefaultAList(), MsgWithdrawActivity.this);
                            lvFollows.setAdapter(msgFollowAdapter);
                            rlEmpty.setVisibility(View.GONE);
                            plFollows.setVisibility(View.VISIBLE);
                        }else {
                            rlEmpty.setVisibility(View.VISIBLE);
                            plFollows.setVisibility(View.GONE);
                            llChoose.setVisibility(View.GONE);
                        }
                    }else {
                        rlEmpty.setVisibility(View.VISIBLE);
                        plFollows.setVisibility(View.GONE);
                        llChoose.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        getFollow();
                    }
                    ToastUtils.show(MsgWithdrawActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_msg_withdraw);
    }

    @Override
    public void initViews() {
        plFollows =(PullToRefreshListView)findViewById(R.id.plFollows);
        lvFollows=plFollows.getRefreshableView();

        rlEmpty =(RelativeLayout) findViewById(R.id.rlEmpty);
        ivRight=(ImageView)findViewById(R.id.ivRight);
        ivRight.setVisibility(View.VISIBLE);
//        setRightResource(R.mipmap.ic_share);
        ivRight.setImageResource(R.mipmap.ico_edit);

        llChoose =(LinearLayout) findViewById(R.id.llChoose);
        tvChoose =(TextView) findViewById(R.id.tvChoose);
        tvDel=(TextView) findViewById(R.id.tvDel);
        llChoose.setVisibility(View.GONE);
    }

    @Override
    public void initDatas() {
        setTitle("提现消息");
        getFollow();
        rlEmpty.setVisibility(View.GONE);
    }

    private void getFollow(){
        params = new RequestParams();
        params.put("reqCode", "getStudentUserMsg");
        params.put("userid", SharedUtils.getUserId(this));
        params.put("msgtype", 4);
        Log.e("params==>",params.toString());
        post("studentUser", params, "getStudentUserMsg");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        ivRight.setOnClickListener(this);

        plFollows.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(MsgWithdrawActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        plFollows.onRefreshComplete();
                        getFollow();
                    }
                },500);

            }
        });

        lvFollows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0||del==1){
                    return;
                }
                studentUserMsgBean.getDefaultAList().get(position-1).del=!studentUserMsgBean.getDefaultAList().get(position-1).del;
                msgFollowAdapter.notifyDataSetChanged();
               /* intent=new Intent(SocialActivity.this,JobDetailsActivity.class);
                intent.putExtra("jobcode",listDefaults.get(position-1).getJobcode());
                Jump(intent);*/
            }
        });

        tvChoose.setOnClickListener(this);
        tvDel.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    private int del=1;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivRight:
                if (studentUserMsgBean.getDefaultAList()!=null&&studentUserMsgBean.getDefaultAList().size()>0) {
                    if (del == 1) {
                        del = 2;
                        llChoose.setVisibility(View.VISIBLE);
                    } else {
                        del = 1;
                        setNo();
                        llChoose.setVisibility(View.GONE);
                    }
                    msgFollowAdapter.setDel(del);
                }

                break;
            case R.id.tvChoose:
                setChoose();
                break;
            case R.id.tvDel:
                String msgids=getChoose();
                if (StringUtils.isEmpty(msgids)){
                    ToastUtils.show(this,"请选择要删除消息");
                    return;
                }
                params = new RequestParams();
                params.put("reqCode", "deleteStudentUserMsgMulti");
                params.put("msgids", msgids);
                post("studentUser", params, "deleteStudentUserMsgMulti");
                break;
        }
    }

    private String getChoose(){
        String msgids="";
        for (int i=0;i<studentUserMsgBean.getDefaultAList().size();i++){
            if (studentUserMsgBean.getDefaultAList().get(i).del) {
                msgids=msgids+studentUserMsgBean.getDefaultAList().get(i).getMsgid()+",";
            }
        }

        if (!StringUtils.isEmpty(msgids)){
            msgids=msgids.substring(0,msgids.length()-1);
        }

        return msgids;
    }

    private void setNo(){
        for (int i=0;i<studentUserMsgBean.getDefaultAList().size();i++){
            studentUserMsgBean.getDefaultAList().get(i).del=false;
        }
        msgFollowAdapter.notifyDataSetChanged();
    }

    private void setChoose(){
        boolean choose=false;
        if (tvChoose.getText().toString().equals("全选")){
            choose=true;
            tvChoose.setText("取消全选");
        }else {
            tvChoose.setText("全选");
        }
        for (int i=0;i<studentUserMsgBean.getDefaultAList().size();i++){
            studentUserMsgBean.getDefaultAList().get(i).del=choose;
        }
        msgFollowAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getStudentUserMsg")){
            msg.what=1;
        }else if (requestname.equals("deleteStudentUserMsgMulti")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
