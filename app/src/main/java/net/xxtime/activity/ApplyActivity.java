package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.ApplyTimeAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.ApplyTimeBean;
import net.xxtime.bean.CommonBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.DataUtils;
import net.xxtime.utils.SharedUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 申请职位
 */
public class ApplyActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private String jobcode;
    private String jobname;
    private String price;
    private String address;
    private int continuous;
    private String jobstartdate;
    private String jobenddate;
    private String jobstarttime;
    private String jobendtime;

    private TextView  tvName, tvDate, tvDayNumber, tvSalary, tvAddress;
    private Button btnApply;

    private PopupWindow dateWindow;
    private  String dates="";

    private List<ApplyTimeBean> listtimes;
    private ApplyTimeBean applyTimeBean;
    private ApplyTimeAdapter applyTimeAdapter;

    private int daynum=0;

    private String html="共<font size=\"3\" color=\"#17B2EB\">0</font>天";

    private Message msg;
    private CommonBean commonBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null){
                        if (commonBean.getBflag().equals("1")){
                            finish();
                        }
                        ToastUtils.show(ApplyActivity.this,commonBean.getMsg());
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_apply);
    }

    @Override
    public void initViews() {
        tvName =(TextView)findViewById(R.id.tvName);
        tvDate =(TextView)findViewById(R.id.tvDate);
        tvDayNumber =(TextView)findViewById(R.id.tvDayNumber);
        tvSalary =(TextView)findViewById(R.id.tvSalary);
        tvAddress=(TextView)findViewById(R.id.tvAddress);
        btnApply=(Button) findViewById(R.id.btnApply);
    }

    @Override
    public void initDatas() {
        jobname=getIntent().getStringExtra("jobname");
        jobcode=getIntent().getStringExtra("jobcode");
        price=getIntent().getStringExtra("price");
        address=getIntent().getStringExtra("address");
        continuous=getIntent().getIntExtra("continuous",0);
        jobstartdate=getIntent().getStringExtra("jobstartdate");
        jobenddate=getIntent().getStringExtra("jobenddate");
        jobstarttime=getIntent().getStringExtra("jobstarttime");
        jobendtime=getIntent().getStringExtra("jobendtime");

        setTitle("申请职位");

        if (!StringUtils.isEmpty(jobname)){
            tvName.setText(jobname);
        }

        if (!StringUtils.isEmpty(price)){
            tvSalary.setText(price);
        }

        if (!StringUtils.isEmpty(address)){
            tvAddress.setText(address);
        }

        int cha=0;

        if (continuous==1){
            if (!StringUtils.isEmpty(jobstartdate)&&!StringUtils.isEmpty(jobenddate)){
                html="共<font size=\"3\" color=\"#17B2EB\">"+ Contact.getDateCha(jobstartdate,jobenddate)+"</font>天";
                tvDate.setText(jobstartdate+"~"+jobenddate);
            }else {
                html="<font size=\"3\" color=\"#17B2EB\">无限制</font>";
                tvDate.setText("无限制");
            }
            tvDayNumber.setText(Html.fromHtml(html));

            tvDate.setEnabled(false);
        }else {
            tvDayNumber.setText(Html.fromHtml(html));

            if (!StringUtils.isEmpty(jobstartdate)&&!StringUtils.isEmpty(jobenddate)){

                    cha = Contact.getDateCha(jobstartdate, jobenddate);
            }else {
                cha=60;
            }
        }
        initDateWindow();

        if (!StringUtils.isEmpty(jobstarttime)&&!StringUtils.isEmpty(jobendtime)) {
            tvTime.setText(jobstarttime.substring(0,5)+"~"+jobendtime.substring(0,5));
        }

        listtimes=new ArrayList<>();

        if (!StringUtils.isEmpty(jobstartdate)&&!StringUtils.isEmpty(jobenddate)){
            Long startTime= DataUtils.getStringToDate(jobstartdate.substring(0,10));
            if (cha>0){
                for (int i=0;i<cha;i++){
                    applyTimeBean=new ApplyTimeBean();
                    applyTimeBean.applydate=startTime+(i*24*60*60*1000);
                    applyTimeBean.applytime=tvTime.getText().toString();
                    listtimes.add(applyTimeBean);
                }
            }
        }else {
            Long startTime= System.currentTimeMillis();
            if (cha>0){
                for (int i=0;i<cha;i++){
                    applyTimeBean=new ApplyTimeBean();
                    applyTimeBean.applydate=startTime+(i*24*60*60*1000);
                    applyTimeBean.applytime=tvTime.getText().toString();
                    listtimes.add(applyTimeBean);
                }
            }
        }

        if (listtimes!=null&&listtimes.size()>0){
            applyTimeAdapter=new ApplyTimeAdapter(listtimes,this);
            lvDates.setAdapter(applyTimeAdapter);
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvDate.setOnClickListener(this);
        lvDates.setOnItemClickListener(this);
        tvCancel.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        btnChoose.setOnClickListener(this);
        btnApply.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    SimpleDateFormat dformatter = new SimpleDateFormat ("yyyy-MM-dd");
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvDate:
                dateWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.tvCancel:
                dateWindow.dismiss();
                break;
            case R.id.tvOk:

                daynum=0;
                dates="";
                for (int i=0;i<listtimes.size();i++){
                    if (listtimes.get(i).choosebool){
                        daynum++;
                       dates=dates+dformatter.format(new Date(listtimes.get(i).applydate))+" "+listtimes.get(i).applytime+",";
                    }
                }

                if (StringUtils.isEmpty(dates)){
                    ToastUtils.show(this,"请选择申请时间");
                    return;
                }else {
                    dates=dates.substring(0,dates.length()-1);
                    dateWindow.dismiss();
                    html="共<font size=\"3\" color=\"#17B2EB\">"+daynum+"</font>天";
                    tvDayNumber.setText(Html.fromHtml(html));
                    tvDate.setText(dates);
                }
                break;
            case R.id.btnChoose:
                if (btnChoose.getText().toString().equals("全选")){
                    setChoose(true);
                    btnChoose.setText("取消全选");
                }else {
                    setChoose(false);
                    btnChoose.setText("全选");
                }
                break;
            case R.id.btnApply:
                if (StringUtils.isEmpty(dates)){
                    ToastUtils.show(this,"请选择申请日期");
                }
                params=new RequestParams();
                params.put("reqCode","registerJob");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("jobcode",jobcode);
                params.put("dates",dates);
                post("userJob",params,"registerJob");

                break;
        }
    }

    private void setChoose(boolean b){
        for (int i=0;i<listtimes.size();i++){
            listtimes.get(i).choosebool=b;
        }
        applyTimeAdapter.notifyDataSetChanged();
    }

    private TextView tvCancel, tvOk, tvTime;
    private Button btnChoose;
    private ListView lvDates;

    private void initDateWindow() {
        View moreView = getLayoutInflater().inflate(R.layout.choose_time_window, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        dateWindow = new PopupWindow(moreView);
        dateWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        dateWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        dateWindow.setFocusable(true);

        moreView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dateWindow.dismiss();
                return false;
            }
        });
        tvCancel  = (TextView) moreView.findViewById(R.id.tvCancel);
        tvOk  = (TextView) moreView.findViewById(R.id.tvOk);
        tvTime  = (TextView) moreView.findViewById(R.id.tvTime);
        btnChoose  = (Button) moreView.findViewById(R.id.btnChoose);
        lvDates = (ListView) moreView.findViewById(R.id.lvDates);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lvDates:
                listtimes.get(position).choosebool=!listtimes.get(position).choosebool;
                applyTimeAdapter.notifyDataSetChanged();
                setChooseBool();
                break;
        }
    }

    private void setChooseBool(){
        int num=0;
        for (int i=0;i<listtimes.size();i++){
            if (listtimes.get(i).choosebool){
                num++;
            }
        }
        if (num==listtimes.size()){
            btnChoose.setText("取消全选");
        }else {
            btnChoose.setText("全选");
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("registerJob")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
