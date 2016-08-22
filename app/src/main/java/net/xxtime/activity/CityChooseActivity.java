package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.view.ScrollGridView;

import net.xxtime.R;
import net.xxtime.adapter.CitysAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.utils.Contact;
import net.xxtime.view.ReferenceListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 城市选择
 */
public class CityChooseActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lvCitys;
    private ReferenceListView rflCitys;
    private CitysAdapter citysAdapter;

    private View headView;
    private TextView tvCurrentCity;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (!StringUtils.isEmpty(Contact.City)){
                        tvCurrentCity.setText(Contact.City);
                    }else {
                        handler.sendEmptyMessageDelayed(1,500);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_citychoose);
    }

    @Override
    public void initViews() {
        lvCitys=(ListView)findViewById(R.id.lvCitys);
        rflCitys=(ReferenceListView)findViewById(R.id.rflCitys);

    }

    @Override
    public void initDatas() {

        headView= LayoutInflater.from(this).inflate(R.layout.city_header,null);
        tvCurrentCity =(TextView)headView.findViewById(R.id.tvCurrentCity);
        lvCitys.addHeaderView(headView);
        tvCurrentCity.setText("定位中...");
        handler.sendEmptyMessageDelayed(1,100);

    }

    @Override
    public void setDatas() {
        setTitle("城市选择");
        if (Contact.listAreas!=null) {
            citysAdapter = new CitysAdapter(Contact.listAreas, this);
            lvCitys.setAdapter(citysAdapter);
        }

    }

    @Override
    public void setListener() {
        rflCitys.setOnTouchingLetterChangedListener(new LetterListViewListener());
        lvCitys.setOnItemClickListener(this);
        tvCurrentCity.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCurrentCity:
                Contact.ChooseCity=tvCurrentCity.getText().toString();
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvCitys:
                if (position==0){
                    return;
                }
                Contact.ChooseCity=Contact.listAreas.get(position-1).getAddName();
                finish();
                break;

        }
    }

    private class LetterListViewListener implements ReferenceListView.OnTouchingLetterChangedListener
    {
        @Override
        public void onTouchingLetterChanged(final String s)
        {
            int len=Contact.listAreas.size();
            for (int i=0;i<len;i++){
                if (Contact.listAreas.get(i).Pinyin.substring(0,1).toUpperCase().equals(s.toUpperCase())){
                    lvCitys.setSelection(i+1);
                    break;
                }
            }
        }
    }

}
