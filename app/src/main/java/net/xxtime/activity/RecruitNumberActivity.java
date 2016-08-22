package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.RecruitAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.EmployedStudentBean;

public class RecruitNumberActivity extends BaseActivity {

    private ListView lvNumbers;
    private String buscode;
    private EmployedStudentBean employedStudentBean;
    private RecruitAdapter recruitAdapter;

    private Message msg;

    private RelativeLayout rlEmpty;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    employedStudentBean= JSONObject.parseObject(msg.obj.toString(),EmployedStudentBean.class);
                    if (employedStudentBean!=null&&employedStudentBean.getBflag().equals("1")){
                        if (employedStudentBean.getDefaultAList().size()>0) {
                            recruitAdapter = new RecruitAdapter(employedStudentBean.getDefaultAList(), RecruitNumberActivity.this);
                            lvNumbers.setAdapter(recruitAdapter);
                        }else {
                            lvNumbers.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.VISIBLE);
                        }
                    }else {
                        lvNumbers.setVisibility(View.GONE);
                        rlEmpty.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_recruit_number);
    }

    @Override
    public void initViews() {
        lvNumbers=(ListView)findViewById(R.id.lvNumbers);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);
    }

    @Override
    public void initDatas() {
        buscode=getIntent().getStringExtra("buscode");
        setTitle("累计招聘人数");
        params=new RequestParams();
        params.put("reqCode","getEmployedStudentByBuscode");
        params.put("buscode",buscode);
        post("userJob",params,"getEmployedStudentByBuscode");

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
        if (requestname.equals("getEmployedStudentByBuscode")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
