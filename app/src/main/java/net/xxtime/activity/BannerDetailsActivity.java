package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.BannerListAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.HomeLbtDetailBean;
import net.xxtime.utils.DataUtils;

public class BannerDetailsActivity extends BaseActivity {

    private int bid;
    private String title;
    private long time;

    private HomeLbtDetailBean homeLbtDetailBean;

    private Message msg;
    private BannerListAdapter bannerListAdapter;
    private ListView lvDetals;

    private View headView;
    private TextView tvBTitle, tvTime;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    homeLbtDetailBean= JSONObject.parseObject(msg.obj.toString(),HomeLbtDetailBean.class);
                    if (homeLbtDetailBean!=null&&homeLbtDetailBean.getBflag().equals("1")){
                        bannerListAdapter=new BannerListAdapter(homeLbtDetailBean.getDefaultAList(),BannerDetailsActivity.this);
                        lvDetals.setAdapter(bannerListAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_banner_details);
    }

    @Override
    public void initViews() {
        lvDetals=(ListView)findViewById(R.id.lvDetals);
        headView= LayoutInflater.from(this).inflate(R.layout.banner_header,null);
        tvBTitle=(TextView)headView.findViewById(R.id.tvBTitle);
        tvTime=(TextView)headView.findViewById(R.id.tvTime);
        lvDetals.addHeaderView(headView);
    }

    @Override
    public void initDatas() {
        setTitle("活动详情");
        bid=getIntent().getIntExtra("bid",0);
        title=getIntent().getStringExtra("title");
        time=getIntent().getLongExtra("time",0);

        params=new RequestParams();
        params.put("reqCode","getHomeLbtDetail");
        params.put("imageid",bid);
        Log.e("param==>",params.toString());
        pullpost("job",params,"getHomeLbtDetail");
        tvBTitle.setText(title+"");
        tvTime.setText("发布时间："+ DataUtils.getDate(time));
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
        if (requestname.equals("getHomeLbtDetail")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
