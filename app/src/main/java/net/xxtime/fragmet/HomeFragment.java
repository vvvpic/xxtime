package net.xxtime.fragmet;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.longtu.base.view.ScrollGridView;
import com.longtu.base.view.ScrollListView;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.activity.AuthenticationActivity;
import net.xxtime.activity.CityChooseActivity;
import net.xxtime.activity.JobDetailsActivity;
import net.xxtime.activity.JobSearchActivity;
import net.xxtime.activity.OnlineJobActivity;
import net.xxtime.activity.PerfectInfoActivity;
import net.xxtime.activity.SchoolJobActivity;
import net.xxtime.activity.SocialActivity;
import net.xxtime.activity.WelfareActivity;
import net.xxtime.adapter.AccountAdapter;
import net.xxtime.adapter.BannerAdapter;
import net.xxtime.adapter.DateAdapter;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.adapter.SortAdapter;
import net.xxtime.base.fragment.BaseFragment;
import net.xxtime.bean.AccountBean;
import net.xxtime.bean.CheckStudentBean;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.GetHomeLbtBean;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.bean.SortBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.DataUtils;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.TopGallery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    private PullToRefreshListView plHome;
    private ListView lvHome;

    private Message msg;
    private String CityCode;

    private GetHomeLbtBean getHomeLbtBean;

    /**
     * 头部
     */
    private View headView;
    private TopGallery gyBanner ;
    private TextView tvCity ;
    private ImageView ivSearch;
    private LinearLayout llPoint;
    private RelativeLayout rlAccount, rlDate, rlSort;
    private BannerAdapter bannerAdapter;
    private LinearLayout  llWelfare, llSocial, llSchool, llOnline;
    private List<ImageView> listimageviews;
    private ImageView imageView;
    private TextView tvSort, tvDate, tvAccount;

    private int curpos=0;

    private List<Long> listdates;
    private long currttime;
    private View view_line;

    private DateAdapter dateAdapter;
    private AccountAdapter accountAdapter;
    private SortAdapter sortAdapter;

    private PopupWindow dateWindow,accountWindow, sortWindow;

    private List<AccountBean> listAccounts;
    private  AccountBean accountBean;

    private List<SortBean> listsorts;
    private SortBean sortBean;

    private View FooterView;
    private ImageView ivLoading ;
    private TextView tvLoading;
    private int indexPage=1;

    private JobByConditionBean jobByConditionBean;
    private List<JobByConditionBean.DefaultAListBean> listDefaults;

    private JobAdapter jobAdapter;

    private Dialog personaldialog;

    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    getHomeLbtBean= JSONObject.parseObject(msg.obj.toString(),GetHomeLbtBean.class);
                    if (getHomeLbtBean!=null&&getHomeLbtBean.getBflag().equals("1")){
                        if (getHomeLbtBean.getDefaultAList()!=null) {
                            bannerAdapter = new BannerAdapter(getHomeLbtBean.getDefaultAList(), getActivity());
                            gyBanner.setAdapter(bannerAdapter);
                            initPoint();
                            handler.sendEmptyMessageDelayed(3,4000);
                        }
                    }
                    getgetJobByCondition();
                    break;
                case 2:
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
                        }else {
                            ivLoading.setVisibility(View.GONE);
                            tvLoading.setText("加载完毕");
                        }
                    }else {
                        ivLoading.setVisibility(View.GONE);
                        tvLoading.setText("加载完毕");
                    }
                    jobAdapter.notifyDataSetChanged();
                    break;
                case 3:
                    curpos++;
                    if (curpos==getHomeLbtBean.getDefaultAList().size()){
                        curpos=0;
                    }
                    gyBanner.setSelection(curpos);
                    handler.sendEmptyMessageDelayed(3,4000);
                    break;

                case 4:
                    Contact.checkStudentBean=JSONObject.parseObject(msg.obj.toString(), CheckStudentBean.class);
                    if (Contact.checkStudentBean!=null) {
                        if (Contact.checkStudentBean.isSuccess()) {
                            params.put("reqCode","isOpenSchool");
                            params.put("userid", SharedUtils.getUserId(getActivity()));
                            Log.e("param==>",params.toString());
                            HomeFragment.this.post("studentUser",params,"isOpenSchool");
                        } else {
                            personaldialog.show();
                        }
                    }
                    break;
                case 5:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        homeActivity.Jump(SchoolJobActivity.class);
                    }else {
                        ToastUtils.show(getActivity(), commonBean.getMsg());
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        layout= R.layout.fgment_home;
    }

    @Override
    public void initViews() {
        plHome=(PullToRefreshListView)view.findViewById(R.id.plHome);
        lvHome=plHome.getRefreshableView();
        headView= LayoutInflater.from(getActivity()).inflate(R.layout.home_header,null);
        gyBanner =(TopGallery) headView.findViewById(R.id.gyBanner);
        tvCity  =(TextView) headView.findViewById(R.id.tvCity);
        ivSearch =(ImageView) headView.findViewById(R.id.ivSearch);
        llPoint =(LinearLayout) headView.findViewById(R.id.llPoint);
        llWelfare  =(LinearLayout) headView.findViewById(R.id.llWelfare);
        llSocial  =(LinearLayout) headView.findViewById(R.id.llSocial);
        llSchool  =(LinearLayout) headView.findViewById(R.id.llSchool);
        llOnline =(LinearLayout) headView.findViewById(R.id.llOnline);
        rlAccount  =(RelativeLayout) headView.findViewById(R.id.rlAccount);
        rlDate  =(RelativeLayout) headView.findViewById(R.id.rlDate);
        rlSort =(RelativeLayout) headView.findViewById(R.id.rlSort);
        view_line=(View)headView.findViewById(R.id.view_line);
        tvSort =(TextView) headView.findViewById(R.id.tvSort);
        tvDate  =(TextView) headView.findViewById(R.id.tvDate);
        tvAccount =(TextView) headView.findViewById(R.id.tvAccount);
        lvHome.addHeaderView(headView);

        FooterView= LayoutInflater.from(getActivity()).inflate(R.layout.list_footer,null);
        ivLoading =(ImageView) FooterView.findViewById(R.id.ivLoading);
        tvLoading=(TextView)FooterView.findViewById(R.id.tvLoading);
        lvHome.addFooterView(FooterView);

        loading(ivLoading);
        initPerson();

    }

    @Override
    public void initDatas() {
        initDateWindow();
        getgetHomeLbt();
        initAccountWindow();
        initSortWindow();
        listdates=new ArrayList<>();
        currttime=System.currentTimeMillis();
        Contact.CurTime=formatter.format(new Date(currttime));
        Log.e("Week==>", DataUtils.getWeek()+"");
        int week=DataUtils.getWeek()-1>1?DataUtils.getWeek()-1:7;
        for (int i=0;i<week-1;i++){
            listdates.add((long) 0);
        }

        for (int i=0;i<15;i++){
            listdates.add(currttime+(i*24*60*60*1000));
        }
        datecurpos=week-1;
        dateAdapter=new DateAdapter(getActivity(),listdates,week-1);
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

        accountAdapter=new AccountAdapter(listAccounts,getActivity(),4);
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
        sortAdapter=new SortAdapter(listsorts,getActivity(),0);
        lvSort.setAdapter(sortAdapter);

        listDefaults=new ArrayList<>();
        jobAdapter=new JobAdapter(listDefaults,getActivity());
        lvHome.setAdapter(jobAdapter);
    }

    /**
     * 初始化滑动点
     */
    private void initPoint(){
        listimageviews=new ArrayList<>();
        llPoint.removeAllViews();
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,2,5,2);
        for (int i=0;i<getHomeLbtBean.getDefaultAList().size();i++){
            imageView=new ImageView(getActivity());
            imageView.setLayoutParams(params);
            if (i==0){
                imageView.setImageResource(R.mipmap.dot_white);
            }else {
                imageView.setImageResource(R.mipmap.dot_gray);
            }
            listimageviews.add(imageView);
            llPoint.addView(imageView);
        }

        if (getHomeLbtBean.getDefaultAList().size()<=1){
            llPoint.setVisibility(View.GONE);
        }
    }

    /**
     * 设置选中点
     * @param position
     */
    private void setPoint(int position){
        for (int i=0;i<listimageviews.size();i++){
            if (i==position){
                listimageviews.get(i).setImageResource(R.mipmap.dot_white);
            }else {
                listimageviews.get(i).setImageResource(R.mipmap.dot_gray);
            }
        }
    }

    /***
     * 获取首页轮播图
     */
    private void getgetHomeLbt(){
        if (!StringUtils.isEmpty(Contact.ChooseCity)){
            Contact.ChooseCityCode=Contact.getCode(Contact.ChooseCity);
            tvCity.setText(Contact.ChooseCity);
            CityCode=Contact.ChooseCityCode;
        }else {
            if (!StringUtils.isEmpty(Contact.City)){
                Contact.CityCode=Contact.getCode(Contact.City);
                tvCity.setText(Contact.City);
                CityCode=Contact.CityCode;
            }
        }
        params=new RequestParams();
        params.put("reqCode","getHomeLbt");
        params.put("city",CityCode);
        pullpost("job",params,"getHomeLbt");
        choosedate="";
        choosesort=-1;
        chooseaccount=-1;

    }

    private void getgetJobByCondition(){
        params=new RequestParams();
        params.put("reqCode","getJobByCondition");
        params.put("indexPage",indexPage);
        params.put("city",CityCode);
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
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvHome.setOnScrollListener(this);
        plHome.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        plHome.onRefreshComplete();
                        indexPage=1;
                        getgetJobByCondition();
                    }
                },500);

            }
        });
        gyBanner.setOnItemSelectedListener(this);
        tvCity.setOnClickListener(this);
        rlAccount.setOnClickListener(this);
        rlDate.setOnClickListener(this);
         rlSort.setOnClickListener(this);
        gvDates.setOnItemClickListener(this);
        lvAccount.setOnItemClickListener(this);
        lvSort.setOnItemClickListener(this);
        ivSearch.setOnClickListener(this);
        llWelfare.setOnClickListener(this);
        llOnline.setOnClickListener(this);
        llSchool.setOnClickListener(this);
        llSocial.setOnClickListener(this);
        lvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0||position==1||position==listDefaults.size()+2){
                    return;
                }
                intent=new Intent(getActivity(),JobDetailsActivity.class);
                intent.putExtra("jobcode",listDefaults.get(position-2).getJobcode());
                homeActivity.Jump(intent);
            }
        });
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }


    @Override
    public void ResumeDatas() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCity:
                intent=new Intent(getActivity(), CityChooseActivity.class);
                startActivityForResult(intent,CITYCHOOSE);
            break;
            case R.id.rlAccount:
                accountWindow.showAsDropDown(view_line);
                break;
            case R.id.rlDate:
                dateWindow.showAsDropDown(view_line);
                break;
            case R.id.rlSort:
                sortWindow.showAsDropDown(view_line);
                break;
            case R.id.ivSearch:
                homeActivity.Jump(JobSearchActivity.class);
                break;
            case R.id.llWelfare:
                homeActivity.Jump(WelfareActivity.class);
                break;
            case R.id.llSocial:
                homeActivity.Jump(SocialActivity.class);
                break;
            case R.id.llOnline:
                homeActivity.Jump(OnlineJobActivity.class);
                break;
            case R.id.llSchool:
                params=new RequestParams();
                params.put("reqCode","checkStudentUserInfo");
                params.put("userid", SharedUtils.getUserId(getActivity()));
                Log.e("param==>",params.toString());
                pullpost("studentUser",params,"checkStudentUserInfo");
                break;
            case R.id.btnCancel:
                personaldialog.dismiss();
                break;
            case R.id.btnOk:
                homeActivity.Jump(PerfectInfoActivity.class);
                personaldialog.dismiss();
                break;
        }
    }


    @Override
    public void Receive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getHomeLbt")){
            msg.what=1;
        }else if (requestname.equals("getJobByCondition")){
            msg.what=2;
        }else if (requestname.equals("checkStudentUserInfo")){
            msg.what=4;
        }else if (requestname.equals("isOpenSchool")){
            msg.what=5;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        curpos=position;
        setPoint(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CITYCHOOSE){
            getgetHomeLbt();
        }
    }

    private ScrollGridView gvDates;
    private void initDateWindow() {
        View moreView = getActivity().getLayoutInflater().inflate(R.layout.down_date, null, false);
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
        View moreView = getActivity().getLayoutInflater().inflate(R.layout.account_window, null, false);
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
        View moreView = getActivity().getLayoutInflater().inflate(R.layout.sort_window, null, false);
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

    private int datecurpos=0;
    private int accountpos=4;
    private int sortpos=0;
    private String choosedate;
    private int choosesort=-1;
    private int chooseaccount=-1;
    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss ");

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.gvDates:

                datecurpos=position;
                dateAdapter.setCur(position);
                dateWindow.dismiss();
                indexPage=1;
                if (position==listdates.size()){
                    choosedate="";
                    tvDate.setText("全部日期");
                }else {
                    if (listdates.get(position)==0){
                        return;
                    }
                    choosedate=formatter.format(new Date(listdates.get(position)));
                    tvDate.setText(formatter.format(new Date(listdates.get(position))).substring(0,10));
                }
                getgetJobByCondition();
                break;
            case R.id.lvAccount:
                accountpos=position;
                accountAdapter.setCur(position);
                accountWindow.dismiss();
                chooseaccount=listAccounts.get(position).settlementtime;
                tvAccount.setText(listAccounts.get(position).name);
                indexPage=1;
                getgetJobByCondition();
                break;
            case R.id.lvSort:
                sortpos=position;
                sortAdapter.setCur(position);
                sortWindow.dismiss();
                choosesort=listsorts.get(position).sortType;
                tvSort.setText(listsorts.get(position).name);
                indexPage=1;
                getgetJobByCondition();
                break;

        }
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

    private Button btnOk, btnCancel;
    private void initPerson() {
        personaldialog = new Dialog(getActivity(), R.style.loadingDialog);
        LinearLayout layout = new LinearLayout(getActivity());

        layout.setBackgroundColor(getActivity().getResources().getColor(
                R.color.transparent));
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.personal_dialog, null);
        layout.addView(view);
        personaldialog.setContentView(layout);
        personaldialog.setCanceledOnTouchOutside(false);
        personaldialog.setCancelable(false);
        btnOk =(Button)view.findViewById(R.id.btnOk);
        btnCancel=(Button)view.findViewById(R.id.btnCancel);
    }
}
