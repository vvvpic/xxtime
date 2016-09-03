package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.longtu.base.view.ScrollGridView;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.DateAdapter;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.listener.SignListener;
import net.xxtime.utils.Contact;
import net.xxtime.utils.DataUtils;
import net.xxtime.utils.SharedUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 待结算
 */
public class ToSignActivity extends BaseActivity implements AdapterView.OnItemClickListener,SignListener {

    private ListView lvJobs;

    private Message msg;

    private RelativeLayout rlEmpty;

    private JobByConditionBean jobByConditionBean;
    private JobAdapter jobAdapter;

    private ScrollGridView gvDates;
    private RelativeLayout rlDel;
    private ImageView ivDel;

    private List<Long> listdates;
    private long currttime;
    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss ");
    private DateAdapter dateAdapter;
    private int datecurpos=0;
    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jobByConditionBean= JSONObject.parseObject(msg.obj.toString(),JobByConditionBean.class);
                    if (jobByConditionBean!=null&&jobByConditionBean.getBflag().equals("1")){
                        if (jobByConditionBean.getDefaultAList()!=null&&jobByConditionBean.getDefaultAList().size()>0) {
                            jobAdapter=new JobAdapter(jobByConditionBean.getDefaultAList(),ToSignActivity.this);
                            lvJobs.setAdapter(jobAdapter);
                            jobAdapter.setStatus("待签到");
                            jobAdapter.setSignListener(ToSignActivity.this);
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
                case 2:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        jobByConditionBean.getDefaultAList().remove(qdpos);
                    }
                    jobAdapter.notifyDataSetChanged();
                    ToastUtils.show(ToSignActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_tosign);
    }

    @Override
    public void initViews() {
        lvJobs=(ListView)findViewById(R.id.lvJobs);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);
        gvDates =(ScrollGridView) findViewById(R.id.gvDates);
        rlDel =(RelativeLayout)findViewById(R.id.rlDel);
        ivDel=(ImageView) findViewById(R.id.ivDel);

    }

    @Override
    public void initDatas() {

        setTitle("待签到");
        listdates=new ArrayList<>();
        currttime=System.currentTimeMillis();
        Contact.CurTime=formatter.format(new Date(currttime));
        getRegisterJobByCondition(Contact.CurTime.substring(0,10));
        Log.e("Week==>", DataUtils.getWeek()+"");
        int week=DataUtils.getWeek()-1>1?DataUtils.getWeek()-1:6;
        for (int i=0;i<week-1;i++){
            listdates.add((long) 0);
        }

        for (int i=0;i<15;i++){
            listdates.add(currttime+(i*24*60*60*1000));
        }
        datecurpos=week-1;
        dateAdapter=new DateAdapter(this,listdates,week-1);
        gvDates.setAdapter(dateAdapter);
    }

    private void getRegisterJobByCondition(String jobdate){
        params=new RequestParams();
        params.put("reqCode","getRegisterJobByCondition");
        params.put("userid", SharedUtils.getUserId(this));
        params.put("postStatus",2);
        params.put("jobdate",jobdate);
        Log.e("param==>",params.toString());
        post("userJob",params,"getRegisterJobByCondition");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        ivDel.setOnClickListener(this);
        gvDates.setOnItemClickListener(this);
        lvJobs.setOnItemClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivDel:
                rlDel.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getRegisterJobByCondition")){
            msg.what=1;
        }else if (requestname.equals("modifyWorkingDate")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.gvDates:
                if (listdates.get(position)==0){
                    return;
                }
                datecurpos=position;
                dateAdapter.setCur(position);
                getRegisterJobByCondition(formatter.format(new Date(listdates.get(position))).substring(0,10));
                break;
            case R.id.lvJobs:
                intent=new Intent(this,JobStatusActivity.class);
                intent.putExtra("postStatus",2);
                intent.putExtra("curtime",formatter.format(new Date(listdates.get(datecurpos))));
                intent.putExtra("codeid",jobByConditionBean.getDefaultAList().get(position).workid);
                Jump(intent);
                break;
        }
    }

    @Override
    public void sign(int position) {
        qdpos=position;
             if (formatter.format(new Date(listdates.get(datecurpos))).
                     indexOf(formatter.format(new Date(System.currentTimeMillis())).substring(0,10))<0){
                 ToastUtils.show(this,"只能签到当天工作！");
                 return;
             }
        intent=new Intent(this,ScanActivity.class);
        Jump(intent,QCODE);
    }

    private int qdpos=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==QCODE&&resultCode==88){
            String result=data.getStringExtra("result");
            if (!StringUtils.isEmpty(result)&&result.indexOf("&")>-1){
                params = new RequestParams();
                params.put("reqCode", "modifyWorkingDate");
                params.put("curDate", result.substring(result.indexOf("&")+1));
                params.put("jobcode", result.substring(0,result.indexOf("&")));
                params.put("userid", SharedUtils.getUserId(this));
                post("userJob", params, "modifyWorkingDate");
            }else{
                ToastUtils.show(this,"扫描内容不符合签到");
            }
        }
    }
}
