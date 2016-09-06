package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.FocusBusAdapter;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.FocusBusBean;
import net.xxtime.bean.FocusNumBean;
import net.xxtime.bean.ForeignBean;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.utils.SharedUtils;

import java.util.List;

public class MyFollowActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private PullToRefreshListView  plFollows;
    private ListView lvFollows;
    private TextView tvFollowJob;
    private View viewFollowJob;
    private TextView tvFollowFirm;
    private View viewFollowFirm;

    private FocusNumBean focusNumBean;
    private FocusBusBean focusBusBean;
    private Message msg;

    private JobByConditionBean jobByConditionBean;

    private JobAdapter jobAdapter;

    private RelativeLayout rlEmpty;

    private int choose=0;

    private FocusBusAdapter focusBusAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    focusNumBean= JSONObject.parseObject(msg.obj.toString(), FocusNumBean.class);
                    if (focusNumBean!=null&&focusNumBean.getBflag().equals("1")){
                        tvFollowJob.setText("关注职位（"+focusNumBean.getDefaultAList().get(0).getPosNum()+"）");
                        tvFollowFirm.setText("关注公司（"+focusNumBean.getDefaultAList().get(0).getBusNum()+"）");
                    }
                    break;
                case 2:
                    jobByConditionBean=JSONObject.parseObject(msg.obj.toString(),JobByConditionBean.class);
                    if (jobByConditionBean!=null&&jobByConditionBean.getBflag().equals("1")){
                        if (jobByConditionBean.getDefaultAList()!=null&&jobByConditionBean.getDefaultAList().size()>0) {
                            jobAdapter=new JobAdapter(jobByConditionBean.getDefaultAList(),MyFollowActivity.this);
                            lvFollows.setAdapter(jobAdapter);
                            plFollows.setVisibility(View.VISIBLE);
                            rlEmpty.setVisibility(View.GONE);
                        }else {
                            plFollows.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.VISIBLE);
                        }
                    }else {
                        plFollows.setVisibility(View.GONE);
                        rlEmpty.setVisibility(View.VISIBLE);
                    }
                    break;
                case 3:
                    focusBusBean=JSONObject.parseObject(msg.obj.toString(),FocusBusBean.class);
                    if (focusBusBean!=null&&focusBusBean.getBflag().equals("1")){
                        focusBusAdapter=new FocusBusAdapter(focusBusBean.getDefaultAList(),MyFollowActivity.this);
                        lvFollows.setAdapter(focusBusAdapter);
                    }
                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_follow);
    }

    @Override
    public void initViews() {
        plFollows =(PullToRefreshListView)findViewById(R.id. plFollows);
        lvFollows=plFollows.getRefreshableView();
        tvFollowJob =(TextView) findViewById(R.id. tvFollowJob);
        viewFollowJob =(View) findViewById(R.id. viewFollowJob);
        tvFollowFirm =(TextView) findViewById(R.id. tvFollowFirm);
        viewFollowFirm=(View) findViewById(R.id. viewFollowFirm);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);
    }

    @Override
    public void initDatas() {
        setTitle("我的关注");
        params=new RequestParams();
        params.put("reqCode","getFocusNum");
        params.put("userid", SharedUtils.getUserId(this));
        post("userJob",params,"getFocusNum");

        getFocusPosition();

    }

    private void getFocusPosition(){
        params=new RequestParams();
        params.put("reqCode","getFocusPosition");
        params.put("userid", SharedUtils.getUserId(this));
        post("userJob",params,"getFocusPosition");
    }

    private void getFocusBus(){
        params=new RequestParams();
        params.put("reqCode","getFocusBus");
        params.put("userid", SharedUtils.getUserId(this));
        post("userJob",params,"getFocusBus");
    }

    @Override
    public void setDatas() {
        setChoose(tvFollowJob,viewFollowJob);
    }

    @Override
    public void setListener() {
        tvFollowFirm.setOnClickListener(this);
        tvFollowJob.setOnClickListener(this);
        lvFollows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    return;
                }

                if (choose==0){
                    intent=new Intent(MyFollowActivity.this,JobDetailsActivity.class);
                    intent.putExtra("jobcode",jobByConditionBean.getDefaultAList().get(position-1).getJobcode());
                    Jump(intent);
                }else {
                    intent=new Intent(MyFollowActivity.this,FirmHomeActivity.class);
                    intent.putExtra("buscode", focusBusBean.getDefaultAList().get(0).getCode());
                    intent.putExtra("busname", focusBusBean.getDefaultAList().get(0).getBusfullname());
                    Jump(intent);
                }

            }
        });
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvFollowJob:
                choose=0;
                setChoose(tvFollowJob,viewFollowJob);
                getFocusPosition();
                break;
            case R.id.tvFollowFirm:
                choose=1;
                setChoose(tvFollowFirm,viewFollowFirm);
                getFocusBus();
                break;
        }
    }

    private void setChoose(TextView tv,View view){
        tvFollowJob.setBackgroundColor(getResources().getColor(R.color.white));
        tvFollowFirm.setBackgroundColor(getResources().getColor(R.color.white));
        viewFollowJob.setVisibility(View.INVISIBLE);
        viewFollowFirm.setVisibility(View.INVISIBLE);
        tvFollowJob.setTextColor(getResources().getColor(R.color.txt_666));
        tvFollowFirm.setTextColor(getResources().getColor(R.color.txt_666));

        tv.setTextColor(getResources().getColor(R.color.blue));
        tv.setBackgroundColor(getResources().getColor(R.color.background));
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getFocusNum")){
            msg.what=1;
        }else if (requestname.equals("getFocusPosition")){
            msg.what=2;
        }else if (requestname.equals("getFocusBus")){
            msg.what=3;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==0){
            return;
        }

    }
}
