package net.xxtime.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;

public class RecruitActivity extends BaseActivity {

    private TextView  tvWelfare ,tvSocial, tvSchool;
    private ListView lvJobs;
    private String buscode;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_recruit);
    }

    @Override
    public void initViews() {
        tvWelfare =(TextView)findViewById(R.id.tvWelfare);
        tvSocial =(TextView)findViewById(R.id.tvSocial);
        tvSchool =(TextView)findViewById(R.id.tvSchool);
        lvJobs=(ListView) findViewById(R.id.lvJobs);
    }

    @Override
    public void initDatas() {
        buscode=getIntent().getStringExtra("buscode");
        setTitle("累计招聘岗位");
    }

    @Override
    public void setDatas() {
        setChoose(tvWelfare);
    }

    @Override
    public void setListener() {
        tvSchool.setOnClickListener(this);
        tvWelfare.setOnClickListener(this);
        tvSocial.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvWelfare:
                setChoose(tvWelfare);
                break;
            case R.id.tvSchool:
                setChoose(tvSchool);
                break;
            case R.id.tvSocial:
                setChoose(tvSocial);
                break;
        }
    }

    /**
     * 设置选中
     */
    private void setChoose(TextView tv){
        tvSocial.setTextColor(getResources().getColor(R.color.txt_666));
        tvSchool.setTextColor(getResources().getColor(R.color.txt_666));
        tvWelfare.setTextColor(getResources().getColor(R.color.txt_666));

        tvSocial.setBackgroundColor(getResources().getColor(R.color.white));
        tvSchool.setBackgroundColor(getResources().getColor(R.color.white));
        tvWelfare.setBackgroundColor(getResources().getColor(R.color.white));

        tv.setBackgroundColor(getResources().getColor(R.color.background));
        tv.setTextColor(getResources().getColor(R.color.blue));
    }
}
