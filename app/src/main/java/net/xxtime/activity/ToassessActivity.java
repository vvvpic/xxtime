package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.utils.SharedUtils;

/***
 * 待评价
 */
public class ToassessActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvJobs;

    private Message msg;

    private RelativeLayout rlEmpty;

    private JobByConditionBean jobByConditionBean;
    private JobAdapter jobAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jobByConditionBean= JSONObject.parseObject(msg.obj.toString(),JobByConditionBean.class);
                    if (jobByConditionBean!=null&&jobByConditionBean.getBflag().equals("1")){
                        if (jobByConditionBean.getDefaultAList()!=null&&jobByConditionBean.getDefaultAList().size()>0) {
                            jobAdapter=new JobAdapter(jobByConditionBean.getDefaultAList(),ToassessActivity.this);
                            lvJobs.setAdapter(jobAdapter);
                            jobAdapter.setStatus("待评价");
                            lvJobs.setVisibility(View.VISIBLE);
                            rlEmpty.setVisibility(View.GONE);
                        }else {
                            lvJobs.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.VISIBLE);
                        }
                    }else {
                        lvJobs.setVisibility(View.GONE);
                        rlEmpty.setVisibility(View.VISIBLE);
                    }
                    break;

            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_toassess);
    }

    @Override
    public void initViews() {
        lvJobs=(ListView)findViewById(R.id.lvJobs);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);

    }

    @Override
    public void initDatas() {
        setTitle("待评价");
    }

    private void getRegisterJobByCondition(){
        params=new RequestParams();
        params.put("reqCode","getRegisterJobByCondition");
        params.put("userid", SharedUtils.getUserId(this));
        params.put("postStatus",4);
        Log.e("param==>",params.toString());
        pullpost("userJob",params,"getRegisterJobByCondition");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvJobs.setOnItemClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        getRegisterJobByCondition();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getRegisterJobByCondition")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent(this,JobStatusActivity.class);
        intent.putExtra("postStatus",4);
        intent.putExtra("codeid",jobByConditionBean.getDefaultAList().get(position).registerid);
        Jump(intent);
    }
}
