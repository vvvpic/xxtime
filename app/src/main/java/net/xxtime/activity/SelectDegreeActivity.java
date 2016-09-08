package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.DegreeAdapter;
import net.xxtime.adapter.SchoolAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.DegreeBean;

public class SelectDegreeActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvDegrees;

    private Message msg;

    private DegreeBean degreeBean;
    private DegreeAdapter degreeAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    degreeBean= JSONObject.parseObject(msg.obj.toString(),DegreeBean.class);
                    if (degreeBean!=null&&degreeBean.getBflag().equals("1")){
                        degreeAdapter=new DegreeAdapter(degreeBean.getDefaultAList(),SelectDegreeActivity.this);
                        lvDegrees.setAdapter(degreeAdapter);
                    }
                    break;
            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_selet_school);
    }

    @Override
    public void initViews() {
        lvDegrees =(ListView)findViewById(R.id.lvSchools);
    }

    @Override
    public void initDatas() {
        setTitle("选择学历");
        params=new RequestParams();
        params.put("reqCode","getDegree");
        pullpost("studentUser",params,"getDegree");
    }


    @Override
    public void setDatas() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent();
        intent.putExtra("degreename",degreeBean.getDefaultAList().get(position).getDegreename());
        intent.putExtra("degreeid",degreeBean.getDefaultAList().get(position).getDegreeid());
        setResult(DEGREE,intent);
        finish();
    }



    @Override
    public void setListener() {
        lvDegrees.setOnItemClickListener(this);
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
        if (requestname.equals("getDegree")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
