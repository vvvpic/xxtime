package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.data.ChakesheData;
import com.longtu.base.data.DatafieldBean;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.BannerAdapter;
import net.xxtime.adapter.HistoryAdapter;
import net.xxtime.adapter.JobAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.GetHomeLbtBean;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.utils.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobSearchActivity extends BaseActivity implements AbsListView.OnScrollListener,AdapterView.OnItemClickListener{

    private EditText et_search;
    private ListView lvHistorys, lvJobs;
    private ChakesheData historydata;
    private DatafieldBean datafieldBean;
    private View historyHeadView;
    private View historyFooterView;
    private TextView tvHostoryClear,tvHostoryClose;

    private List<Map<String,Object>> listHistorys;
    private List<Map<String,Object>> listseaHistorys;
    private Map<String ,Object> maphis;
    private HistoryAdapter historyAdapter;
    private int indexPage=1;
    private String CityCode;

    private View FooterView;
    private ImageView ivLoading ;
    private TextView tvLoading;
    private Message msg;

    private JobAdapter jobAdapter;

    private JobByConditionBean jobByConditionBean;
    private List<JobByConditionBean.DefaultAListBean> listDefaults;
    private String search;
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
                            lvJobs.setVisibility(View.VISIBLE);
                            lvHistorys.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.GONE);
                        }else {
                            ivLoading.setVisibility(View.GONE);
                            tvLoading.setText("加载完毕");
                            if (indexPage==1){
                                lvJobs.setVisibility(View.GONE);
                                lvHistorys.setVisibility(View.GONE);
                                rlEmpty.setVisibility(View.VISIBLE);
                            }
                        }
                    }else {
                        ivLoading.setVisibility(View.GONE);
                        tvLoading.setText("加载完毕");
                        if (indexPage==1){
                            lvJobs.setVisibility(View.GONE);
                            lvHistorys.setVisibility(View.GONE);
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
        setContentView(R.layout.activity_job_search);
    }

    @Override
    public void initViews() {
        et_search =(EditText)findViewById(R.id.et_search);
        lvHistorys  =(ListView) findViewById(R.id.lvHistorys);
        lvJobs =(ListView) findViewById(R.id.lvJobs);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);
        historyHeadView= LayoutInflater.from(this).inflate(R.layout.history_header,null);
        lvHistorys.addHeaderView(historyHeadView);

        historyFooterView=LayoutInflater.from(this).inflate(R.layout.history_footer,null);
        tvHostoryClear =(TextView)historyFooterView.findViewById(R.id.tvHostoryClear);
        tvHostoryClose =(TextView)historyFooterView.findViewById(R.id.tvHostoryClose);
        lvHistorys.addFooterView(historyFooterView);

        FooterView= LayoutInflater.from(this).inflate(R.layout.list_footer,null);
        ivLoading =(ImageView) FooterView.findViewById(R.id.ivLoading);
        tvLoading=(TextView)FooterView.findViewById(R.id.tvLoading);
        lvJobs.addFooterView(FooterView);
    }

    @Override
    public void initDatas() {
        datafieldBean=new DatafieldBean();
        datafieldBean.table="histoty";
        datafieldBean.listfield.add("jobname");

        historydata=new ChakesheData(this,datafieldBean);
        historydata.onSetup();
        setHistory();

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
    }

    private void setHistory(){
        listHistorys=historydata.ReadAll();
        historyAdapter=new HistoryAdapter(listHistorys,this);
        lvHistorys.setAdapter(historyAdapter);
        lvHistorys.setVisibility(View.VISIBLE);
        lvJobs.setVisibility(View.GONE);
        rlEmpty.setVisibility(View.GONE);
    }

    @Override
    public void setDatas() {

    }

    private void addHistory(String jobname){
        maphis=new HashMap<>();
        maphis.put("jobname",jobname);
        listseaHistorys=historydata.SearchAll(maphis,"jobname",null,null,false);
        if (listseaHistorys==null||listseaHistorys.size()==0){
            historydata.Add(maphis);
        }
    }

    @Override
    public void setListener() {
        et_search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        et_search.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (!StringUtils.isEmpty(et_search.getText().toString())) {
                        indexPage = 1;
                        addHistory(et_search.getText().toString());
                        search=et_search.getText().toString();
                        getgetJobByCondition(search);
                        et_search.setText("");
                        hideSoftInput(et_search);
                    }else {
                        ToastUtils.show(JobSearchActivity.this,"请输入搜索内容");
                    }
                    return false;
                }
                return false;
            }
        });
//        et_search.addTextChangedListener(new EditTextWatcher(R.id.et_search));
        et_search.setOnClickListener(this);
        lvJobs.setOnScrollListener(this);
        lvJobs.setOnItemClickListener(this);
        lvHistorys.setOnItemClickListener(this);
        tvHostoryClear.setOnClickListener(this);
        tvHostoryClose.setOnClickListener(this);
    }

    private void getgetJobByCondition(String jobname){
        if (StringUtils.isEmpty(jobname)){
            lvJobs.setVisibility(View.GONE);
            lvHistorys.setVisibility(View.GONE);
            rlEmpty.setVisibility(View.VISIBLE);
            return;
        }
        params=new RequestParams();
        params.put("reqCode","getJobByCondition");
        params.put("indexPage",indexPage);
        params.put("city",CityCode);
        params.put("jobname",jobname);

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
                        getgetJobByCondition(search);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvJobs:

                if (listDefaults.size()==position){
                    return;
                }

                intent=new Intent(this,JobDetailsActivity.class);
                intent.putExtra("jobcode",listDefaults.get(position).getJobcode());
                Jump(intent);

                break;
            case R.id.lvHistorys:
                if (listHistorys==null&&listHistorys.size()==0){
                    return;
                }

                if (position==0||position==listHistorys.size()+1
                        ){
                    return;
                }
                search=listHistorys.get(position-1).get("jobname").toString();
                indexPage=1;
                getgetJobByCondition(search);
                hideSoftInput(et_search);
                break;
        }

    }

    public class EditTextWatcher implements TextWatcher {
        int code;

        public EditTextWatcher(int i) {
            code = i;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            Log.e("afterTextChanged==>","afterTextChanged");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            Log.e("beforeTextChanged==>","beforeTextChanged");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO 自动生成的方法存根
            Log.e("onTextChanged==>","onTextChanged");

        }

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        Log.e("v==>",v.getId()+"");
        switch (v.getId()){
            case R.id.et_search:
                setHistory();
                break;
            case R.id.tvHostoryClear:
                clear();
                setHistory();
                break;
            case R.id.tvHostoryClose:
                getgetJobByCondition(search);
                hideSoftInput(et_search);
                break;
        }
    }

    private void clear(){
        if (listHistorys!=null){
            for (int i=0;i<listHistorys.size();i++){
                historydata.Delete(listHistorys.get(i),"_id");
            }
        }

    }
}
