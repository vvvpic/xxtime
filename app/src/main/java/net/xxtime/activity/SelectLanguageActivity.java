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
import net.xxtime.adapter.LanguageAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.ForeignBean;

public class SelectLanguageActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvLanguage;

    private Message msg;

    private ForeignBean foreignBean;

    private LanguageAdapter languageAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    foreignBean= JSONObject.parseObject(msg.obj.toString(),ForeignBean.class);
                    if (foreignBean!=null&&foreignBean.getBflag().equals("1")){
                        languageAdapter=new LanguageAdapter(foreignBean.getDefaultAList(),SelectLanguageActivity.this);
                        lvLanguage.setAdapter(languageAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_select_language);
    }

    @Override
    public void initViews() {
        lvLanguage=(ListView)findViewById(R.id.lvLanguage);
    }

    @Override
    public void initDatas() {
        setTitle("选择外语");
        params=new RequestParams();
        params.put("reqCode","getForeign");
        post("studentUser",params,"getForeign");

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvLanguage.setOnItemClickListener(this);
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
        if (requestname.equals("getForeign")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent();
        intent.putExtra("foreignname",foreignBean.getDefaultAList().get(position).getForeignname());
        intent.putExtra("foreignid",foreignBean.getDefaultAList().get(position).getForeignid());
        setResult(SelecctLanguge,intent);
        finish();
    }
}
