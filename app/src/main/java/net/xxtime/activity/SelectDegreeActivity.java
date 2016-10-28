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
import net.xxtime.utils.SharedUtils;

/**
 * 选择学历
 */
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
                    if (degreeBean!=null&&degreeBean.getStatus().equals("1")){
                        degreeAdapter=new DegreeAdapter(degreeBean.getDegrees(),SelectDegreeActivity.this);
                        lvDegrees.setAdapter(degreeAdapter);
                    }
                    break;
            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_selet_drgree);
    }

    @Override
    public void initViews() {
        lvDegrees =(ListView)findViewById(R.id.lvSchools);
    }

    @Override
    public void initDatas() {
        setTitle("选择学历");
        params=new RequestParams();
        params.put("accessToken", SharedUtils.getToken(this));
        pullpost("degree!list",params);
    }


    @Override
    public void setDatas() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent();
        intent.putExtra("degreename",degreeBean.getDegrees().get(position).getName());
        intent.putExtra("degreeid",degreeBean.getDegrees().get(position).getId());
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
        if (requestname.equals("degree!list")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
