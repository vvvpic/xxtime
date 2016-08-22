package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.longtu.base.util.StringUtils;
import com.longtu.base.view.ScrollGridView;
import com.longtu.base.view.ScrollListView;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.AccountAdapter;
import net.xxtime.adapter.DateAdapter;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.adapter.SortAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.AccountBean;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.bean.SortBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.DataUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocialActivity extends BaseActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    private PopupWindow dateWindow,accountWindow, sortWindow;

    private List<AccountBean> listAccounts;
    private  AccountBean accountBean;

    private List<SortBean> listsorts;
    private SortBean sortBean;
    private DateAdapter dateAdapter;
    private AccountAdapter accountAdapter;
    private SortAdapter sortAdapter;

    private List<Long> listdates;
    private long currttime;
    private View view_line;

    private int datecurpos=0;
    private int accountpos=4;
    private int sortpos=0;
    private String choosedate;
    private int choosesort=-1;
    private int chooseaccount=-1;

    private RelativeLayout rlAccount, rlDate, rlSort;

    private int indexPage=1;
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

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (indexPage==1){
                        listDefaults.clear();
                    }
                    jobByConditionBean=JSONObject.parseObject(msg.obj.toString(),JobByConditionBean.class);
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
        setContentView(R.layout.activity_social);
    }

    @Override
    public void initViews() {
        rlAccount  =(RelativeLayout)findViewById(R.id.rlAccount);
        rlDate  =(RelativeLayout)findViewById(R.id.rlDate);
        rlSort =(RelativeLayout)findViewById(R.id.rlSort);
        view_line=(View)findViewById(R.id.view_line);
        plJobs=(PullToRefreshListView)findViewById(R.id.plJobs);
        lvJobs=plJobs.getRefreshableView();
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);

        FooterView= LayoutInflater.from(this).inflate(R.layout.list_footer,null);
        ivLoading =(ImageView) FooterView.findViewById(R.id.ivLoading);
        tvLoading=(TextView)FooterView.findViewById(R.id.tvLoading);
        lvJobs.addFooterView(FooterView);
    }

    @Override
    public void initDatas() {
        if (!StringUtils.isEmpty(Contact.ChooseCityCode)){
            CityCode=Contact.ChooseCityCode;
        }else {
            if (!StringUtils.isEmpty(Contact.CityCode)){
                CityCode=Contact.CityCode;
            }
        }
        setTitle("社会兼职");
        initDateWindow();
        initAccountWindow();
        initSortWindow();
        listdates=new ArrayList<>();
        currttime=System.currentTimeMillis();
        Contact.CurTime=formatter.format(new Date(currttime));
        Log.e("Week==>", DataUtils.getWeek()+"");
        int week=DataUtils.getWeek()-1>1?DataUtils.getWeek()-1:6;
        for (int i=0;i<week-1;i++){
            listdates.add((long) 0);
        }

        for (int i=0;i<15;i++){
            listdates.add(currttime+(i*24*60*60*1000));
        }
        datecurpos=week-1;
        dateAdapter=new DateAdapter(this,listdates,week-1);
        gvDates.setAdapter(dateAdapter);

        listAccounts=new ArrayList<>();
        accountBean=new AccountBean();
        accountBean.name="日结";
        accountBean.settlementtime=1;
        listAccounts.add(accountBean);

        accountBean=new AccountBean();
        accountBean.name="周结";
        accountBean.settlementtime=2;
        listAccounts.add(accountBean);

        accountBean=new AccountBean();
        accountBean.name="月结";
        accountBean.settlementtime=3;
        listAccounts.add(accountBean);

        accountBean=new AccountBean();
        accountBean.name="完工结算";
        accountBean.settlementtime=4;
        listAccounts.add(accountBean);

        accountBean=new AccountBean();
        accountBean.name="全部方式";
        accountBean.settlementtime=-1;
        listAccounts.add(accountBean);

        accountAdapter=new AccountAdapter(listAccounts,this,4);
        lvAccount.setAdapter(accountAdapter);

        listsorts=new ArrayList<>();
        sortBean=new SortBean();
        sortBean.name="推荐排序";
        sortBean.sortType=-1;
        listsorts.add(sortBean);

        sortBean=new SortBean();
        sortBean.name="最新发布 ";
        sortBean.sortType=1;
        listsorts.add(sortBean);

        sortBean=new SortBean();
        sortBean.name="好评优先";
        sortBean.sortType=2;
        listsorts.add(sortBean);

        sortBean=new SortBean();
        sortBean.name="人气优先";
        sortBean.sortType=3;
        listsorts.add(sortBean);
        sortAdapter=new SortAdapter(listsorts,this,0);
        lvSort.setAdapter(sortAdapter);

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
        rlAccount.setOnClickListener(this);
        rlDate.setOnClickListener(this);
        rlSort.setOnClickListener(this);
        gvDates.setOnItemClickListener(this);
        lvAccount.setOnItemClickListener(this);
        lvSort.setOnItemClickListener(this);
        plJobs.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(SocialActivity.this, System.currentTimeMillis(),
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
                intent=new Intent(SocialActivity.this,JobDetailsActivity.class);
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
        switch (v.getId()){
            case R.id.rlAccount:
                accountWindow.showAsDropDown(view_line);
                break;
            case R.id.rlDate:
                dateWindow.showAsDropDown(view_line);
                break;
            case R.id.rlSort:
                sortWindow.showAsDropDown(view_line);
                break;

        }
    }

    private ScrollGridView gvDates;
    private void initDateWindow() {
        View moreView = getLayoutInflater().inflate(R.layout.down_date, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        dateWindow = new PopupWindow(moreView);
        dateWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        dateWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        dateWindow.setFocusable(true);

        moreView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dateWindow.dismiss();
                return false;
            }
        });
        gvDates = (ScrollGridView) moreView.findViewById(R.id.gvDates);
    }

    private ScrollListView lvAccount;
    private void initAccountWindow() {
        View moreView = getLayoutInflater().inflate(R.layout.account_window, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        accountWindow = new PopupWindow(moreView);
        accountWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        accountWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        accountWindow.setFocusable(true);

        moreView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                accountWindow.dismiss();
                return false;
            }
        });
        lvAccount = (ScrollListView) moreView.findViewById(R.id.lvAccount);
    }


    private ScrollListView lvSort;
    private void initSortWindow() {
        View moreView = getLayoutInflater().inflate(R.layout.sort_window, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        sortWindow = new PopupWindow(moreView);
        sortWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        sortWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        sortWindow.setFocusable(true);

        moreView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sortWindow.dismiss();
                return false;
            }
        });
        lvSort = (ScrollListView) moreView.findViewById(R.id.lvSort);
    }

    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss ");
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.gvDates:
                if (listdates.get(position)==0){
                    return;
                }
                datecurpos=position;
                dateAdapter.setCur(position);
                dateWindow.dismiss();
                choosedate=formatter.format(new Date(listdates.get(position)));
                indexPage=1;
                getgetJobByCondition();
                break;
            case R.id.lvAccount:
                accountpos=position;
                accountAdapter.setCur(position);
                accountWindow.dismiss();
                chooseaccount=listAccounts.get(position).settlementtime;
                indexPage=1;
                getgetJobByCondition();
                break;
            case R.id.lvSort:
                sortpos=position;
                sortAdapter.setCur(position);
                sortWindow.dismiss();
                choosesort=listsorts.get(position).sortType;
                indexPage=1;
                getgetJobByCondition();
                break;
        }
    }

    private void getgetJobByCondition(){
        params=new RequestParams();
        params.put("reqCode","getJobByCondition");
        params.put("indexPage",indexPage);
        params.put("city",CityCode);
        params.put("jobtype",1);
        if (chooseaccount>0) {
            params.put("settlementtime",chooseaccount);
        }

        if (choosesort>0) {
            params.put("sortType",choosesort);
        }

        if (!StringUtils.isEmpty(choosedate)){
            params.put("jobdate",choosedate);
        }
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
