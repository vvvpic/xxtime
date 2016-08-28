package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.BsInfoBean;
import net.xxtime.bean.CommonBean;
import net.xxtime.utils.SharedUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class WalksActivity extends BaseActivity {

    private EditText etDayprice ,etTimeprice;
    private Button btnSet;

    private Message msg;
    private CommonBean commonBean;
    private BsInfoBean bsInfoBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null){
//                        ToastUtils.show(PerfectInfoActivity.this,commonBean.getMsg());
                        if (commonBean.getBflag().equals("1")){
                           ToastUtils.show(WalksActivity.this,"提交成功");
                            finish();
                        }
                    }
                    break;
                case 2:
                    bsInfoBean=JSONObject.parseObject(msg.obj.toString(),BsInfoBean.class);
                    if (bsInfoBean!=null&&bsInfoBean.getBflag().equals("1")&&bsInfoBean.getDefaultAList()!=null){
                        if(bsInfoBean.getDefaultAList().get(0).getExpectsalaryday()>0) {
                            etDayprice.setText(bsInfoBean.getDefaultAList().get(0).getExpectsalaryday() + "");
                        }

                        if(bsInfoBean.getDefaultAList().get(0).getExpectsalaryhour()>0) {
                            etTimeprice.setText(bsInfoBean.getDefaultAList().get(0).getExpectsalaryhour() + "");
                        }
                    }
                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_walks);
    }

    @Override
    public void initViews() {
        etDayprice =(EditText)findViewById(R.id.etDayprice);
        etTimeprice  =(EditText)findViewById(R.id.etTimeprice);
        btnSet =(Button) findViewById(R.id.btnSet);
    }

    @Override
    public void initDatas() {
        setTitle("申请保送");
        params=new RequestParams();
        params.put("reqCode","getBsInfo");
        params.put("userid",SharedUtils.getUserId(this));
        post("studentUser",params,"getBsInfo");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnSet.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSet:
                if (StringUtils.isEmpty(etDayprice.getText().toString())||StringUtils.isEmpty(etTimeprice.getText().toString())){
                    ToastUtils.show(WalksActivity.this,"保送薪资");
                    return;
                }
                params=new RequestParams();
                params.put("reqCode","modifyStudentUserInfo");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("expectsalaryday",etDayprice.getText().toString());
                params.put("expectsalaryhour",etTimeprice.getText().toString());
                post("studentUser",params,"modifyStudentUserInfo");
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("modifyStudentUserInfo")){
            msg.what=1;
        }else if (requestname.equals("getBsInfo")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

}
