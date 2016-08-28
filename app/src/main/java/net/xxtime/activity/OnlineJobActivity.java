package net.xxtime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.longtu.base.util.StringUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.utils.Contact;

import java.util.ArrayList;
import java.util.List;

public class OnlineJobActivity extends BaseActivity implements AbsListView.OnScrollListener{

    private PullToRefreshListView plJobs;
    private ListView lvJobs;

    private JobByConditionBean jobByConditionBean;
    private List<JobByConditionBean.DefaultAListBean> listDefaults;

    private JobAdapter jobAdapter;
    private View FooterView;
    private ImageView ivLoading ;
    private TextView tvLoading;
    private String CityCode;

    private Message msg;

    private RelativeLayout rlEmpty;
    private int indexPage=1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (indexPage==1){
                        listDefaults.clear();
                    }
                    jobByConditionBean= JSONObject.parseObject(msg.obj.toString(),JobByConditionBean.class);
                    if (jobByConditionBean!=null&&jobByConditionBean.getBflag().equals("1")){
                        if (jobByConditionBean.getDefaultAList()!=null&&jobByConditionBean.getDefaultAList().size()>0) {
                            listDefaults.addAll(jobByConditionBean.getDefaultAList());
                            if (jobByConditionBean.getDefaultAList().size()==10){
                                ivLoading.setVisibility(View.VISIBLE);
                                tvLoading.setText("加载中...");
                            }else {
                                ivLoading.setVisibility(View.GONE);
                                tvLoading.setText("加载完毕");
                            }
                            plJobs.setVisibility(View.VISIBLE);
                            rlEmpty.setVisibility(View.GONE);
                        }else {
                            ivLoading.setVisibility(View.GONE);
                            tvLoading.setText("加载完毕");
                            if (indexPage==1){
                                plJobs.setVisibility(View.GONE);
                                rlEmpty.setVisibility(View.VISIBLE);
                            }
                        }
                    }else {
                        ivLoading.setVisibility(View.GONE);
                        tvLoading.setText("加载完毕");
                        if (indexPage==1){
                            plJobs.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                    jobAdapter.notifyDataSetChanged();
                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_online_job);
    }

    @Override
    public void initViews() {
        plJobs=(PullToRefreshListView)findViewById(R.id.plJobs);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);
        lvJobs=plJobs.getRefreshableView();
        FooterView= LayoutInflater.from(this).inflate(R.layout.list_footer,null);
        ivLoading =(ImageView) FooterView.findViewById(R.id.ivLoading);
        tvLoading=(TextView)FooterView.findViewById(R.id.tvLoading);
        lvJobs.addFooterView(FooterView);
        loading(ivLoading);
    }

    @Override
    public void initDatas() {
        setTitle("在线兼职");
        if (!StringUtils.isEmpty(Contact.ChooseCityCode)){
            CityCode=Contact.ChooseCityCode;
        }else {
            if (!StringUtils.isEmpty(Contact.CityCode)){
                CityCode=Contact.CityCode;
            }
        }

        listDefaults=new ArrayList<>();
        jobAdapter=new JobAdapter(listDefaults,this);
        lvJobs.setAdapter(jobAdapter);
        getgetJobByCondition();
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        plJobs.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(OnlineJobActivity.this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        plJobs.onRefreshComplete();
                        indexPage=1;
                        getgetJobByCondition();
                    }
                },500);

            }
        });
        lvJobs.setOnScrollListener(this);

        lvJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0||position==listDefaults.size()+1){
                    return;
                }
                intent=new Intent(OnlineJobActivity.this,JobDetailsActivity.class);
                intent.putExtra("jobcode",listDefaults.get(position-1).getJobcode());
                Jump(intent);
            }
        });
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    private void getgetJobByCondition(){
        params=new RequestParams();
        params.put("reqCode","getJobByCondition");
        params.put("indexPage",indexPage);
        params.put("city",CityCode);
        params.put("publisher",3);
        Log.e("param==>",params.toString());
        pullpost("job",params,"getJobByCondition");
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            // 判断是否滚动到底部
            if (view.getLastVisiblePosition() == view.getCount() - 1&&ivLoading.getVisibility()==View.VISIBLE) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        indexPage++;
                        getgetJobByCondition();
                    }
                }, 500);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getJobByCondition")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
