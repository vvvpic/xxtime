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
import net.xxtime.adapter.PayAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.ForeignBean;

import java.util.ArrayList;
import java.util.List;

public class SelectPayActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvPays;

    private Message msg;

    private List<String> listpays;
    private PayAdapter payAdapter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_select_pay);
    }

    @Override
    public void initViews() {
        lvPays=(ListView)findViewById(R.id.lvPays);
    }

    @Override
    public void initDatas() {
        listpays=new ArrayList<>();
        listpays.add("支付宝");
        listpays.add("微信");
        listpays.add("银行卡");
        payAdapter=new PayAdapter(listpays,this);
        lvPays.setAdapter(payAdapter);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvPays.setOnItemClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnReceive(String requestname, String response) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent();
        intent.putExtra("name",listpays.get(position));
        setResult(SelecctPAY,intent);
        finish();
    }
}
