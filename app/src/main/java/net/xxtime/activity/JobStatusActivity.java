package net.xxtime.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.bean.JobByCodeBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.OptionsUtils;
import net.xxtime.utils.SharedUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobStatusActivity extends BaseActivity {

    private ImageView ivBuslogo;
    private TextView tvAppy;
    private RatingBar rbAssess;
    private TextView tvJobname, tvPrice, tvUint, tvTime ,tvLucency,  tvAdress, tvStatus;
    private TextView tvDetails, tvAddress, tvContact, tvDStatus;

    public int postStatus;
    public int codeid;

    private CommonBean commonBean;
    private String curtime;

    private Message msg;
    private JobByCodeBean jobByCodeBean;

    private LinearLayout llDly;

    private RelativeLayout rlQd;
    private TextView tvQdDetails;

    /**
     * 待结算
     */
    private LinearLayout  llDjs;
    private TextView tvDjsAddress, tvDjsName, tvDjsTel ,tvDjsEmail,  tvDjsPhone;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jobByCodeBean= JSONObject.parseObject(msg.obj.toString(),JobByCodeBean.class);
                    if (jobByCodeBean!=null&&jobByCodeBean.getBflag().equals("1")){
                        setJob();
                    }
                    break;
                case 2:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        finish();
                    }
                    ToastUtils.show(JobStatusActivity.this,commonBean.getMsg());
                    break;
                case 3:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        finish();
                    }
                    ToastUtils.show(JobStatusActivity.this,commonBean.getMsg());
                    break;
                case 4:
                    commonBean=JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null&&commonBean.getBflag().equals("1")){
                        finish();
                    }
                    ToastUtils.show(JobStatusActivity.this,commonBean.getMsg());
                    break;
            }
        }
    };

    private void setJob(){
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getBuslogo())){
            ImageLoader.getInstance().displayImage(jobByCodeBean.getDefaultAList().get(0).getBuslogo(),ivBuslogo, OptionsUtils.getSimpleOptions(10));
        }

        Drawable drawable = getResources().getDrawable(R.mipmap.ico_cheng);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        if (jobByCodeBean.getDefaultAList().get(0).getScreenmsg1()>0){
            tvJobname.setCompoundDrawables(null, null, drawable, null); //分别对应 左上右下
        }else {
            tvJobname.setCompoundDrawables(null, null, null, null); //分别对应 左上右下
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobname())){
            tvJobname.setText(jobByCodeBean.getDefaultAList().get(0).getJobname());
        }

        tvPrice.setText(jobByCodeBean.getDefaultAList().get(0).getSalary()+"");

        if (jobByCodeBean.getDefaultAList().get(0).getSalarytype()==1){
            tvUint.setText("元/时");
        }else if (jobByCodeBean.getDefaultAList().get(0).getSalarytype()==2){
            tvUint.setText("元/天");
        }else if (jobByCodeBean.getDefaultAList().get(0).getSalarytype()==3){
            tvUint.setText("元/单");
        }

        tvAdress.setText("");
        tvAddress.setText("");
        tvDjsAddress.setText("");

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getProvicename())){
            tvAddress.append(jobByCodeBean.getDefaultAList().get(0).getProvicename());
            tvAdress.append(jobByCodeBean.getDefaultAList().get(0).getProvicename());
            tvDjsAddress.append(jobByCodeBean.getDefaultAList().get(0).getProvicename());
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getCityname())){
            tvAddress.append(jobByCodeBean.getDefaultAList().get(0).getCityname());
            tvAdress.append(jobByCodeBean.getDefaultAList().get(0).getCityname());
            tvDjsAddress.append(jobByCodeBean.getDefaultAList().get(0).getCityname());
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getAreaname())){
            tvAdress.append(jobByCodeBean.getDefaultAList().get(0).getAreaname());
            tvAddress.append(jobByCodeBean.getDefaultAList().get(0).getAreaname());
            tvDjsAddress.append(jobByCodeBean.getDefaultAList().get(0).getAreaname());
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getAddress())){
            tvAddress.append(jobByCodeBean.getDefaultAList().get(0).getAddress());
            tvAdress.append(jobByCodeBean.getDefaultAList().get(0).getAddress());
            tvDjsAddress.append(jobByCodeBean.getDefaultAList().get(0).getAddress());
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getContacts())){
            tvDjsName.append(jobByCodeBean.getDefaultAList().get(0).getContacts());
        }else {
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getContactmphone())){
            tvDjsPhone.append(jobByCodeBean.getDefaultAList().get(0).getContactmphone());
        }else {
            tvDjsPhone.setText("无");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).email)){
            tvDjsEmail.append(jobByCodeBean.getDefaultAList().get(0).email);
        }else {
            tvDjsEmail.setText("无");
        }


        if (jobByCodeBean.getDefaultAList().get(0).getSettlementtime()==1){
            tvStatus.setText("日结");
        }else if (jobByCodeBean.getDefaultAList().get(0).getSettlementtime()==2){
            tvStatus.setText("周结");
        }else if (jobByCodeBean.getDefaultAList().get(0).getSettlementtime()==3){
            tvStatus.setText("月结");
        }else if (jobByCodeBean.getDefaultAList().get(0).getSettlementtime()==4){
            tvStatus.setText("完工结算");
        }

        tvTime.setText("");
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getApplystartdate())&&
                !StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getApplyenddate())){
            tvTime.append(jobByCodeBean.getDefaultAList().get(0).getApplystartdate().substring(0,10)+"~"+
                    jobByCodeBean.getDefaultAList().get(0).getApplyenddate().substring(0,10));

        }else {
            tvTime.setText("报名日期不限");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobstartdate())&&
                !StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobenddate())){
            tvDetails.append(jobByCodeBean.getDefaultAList().get(0).getJobstartdate().substring(0,10)+"~"+
                    jobByCodeBean.getDefaultAList().get(0).getJobenddate().substring(0,10));

        }else {
            tvDetails.setText("具体工作时间由商家跟您协商");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getLabelnames())){
            tvLucency.setText(jobByCodeBean.getDefaultAList().get(0).getLabelnames().replace(","," "));
        }else {
            tvLucency.setText("无附加说明");
        }

        rbAssess.setRating(jobByCodeBean.getDefaultAList().get(0).getStarNum());

        if (postStatus==1||postStatus==4) {
            tvContact.setText("需企业录用后才会显示");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).dates)){
            tvQdDetails.setText(jobByCodeBean.getDefaultAList().get(0).dates.replace(",","\n"));
        }else {
            tvQdDetails.setText("日期不限");
        }

    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_job_status);
    }

    @Override
    public void initViews() {
        ivBuslogo=(ImageView)findViewById(R.id.ivBuslogo);
        tvAppy=(TextView) findViewById(R.id.tvAppy);
        rbAssess=(RatingBar) findViewById(R.id.rbAssess);
        tvJobname=(TextView) findViewById(R.id.tvJobname);
        tvPrice=(TextView) findViewById(R.id.tvPrice);
        tvUint=(TextView) findViewById(R.id.tvUint);
        tvTime=(TextView) findViewById(R.id.tvTime);
        tvLucency=(TextView) findViewById(R.id.tvLucency);
        tvAdress=(TextView) findViewById(R.id.tvAdress);
        tvStatus=(TextView) findViewById(R.id.tvStatus);

        tvDetails =(TextView) findViewById(R.id.tvDetails);
        tvAddress =(TextView) findViewById(R.id.tvAddress);
        tvContact =(TextView) findViewById(R.id.tvContact);
        tvDStatus=(TextView) findViewById(R.id.tvDStatus);
        llDly=(LinearLayout)findViewById(R.id.llDly);

        llDjs =(LinearLayout)findViewById(R.id.llDjs);
        tvDjsAddress =(TextView) findViewById(R.id.tvDjsAddress);
        tvDjsName =(TextView) findViewById(R.id.tvDjsName);
        tvDjsTel =(TextView) findViewById(R.id.tvDjsTel);
        tvDjsEmail  =(TextView) findViewById(R.id.tvDjsEmail);
        tvDjsPhone=(TextView) findViewById(R.id.tvDjsPhone);

        rlQd  =(RelativeLayout) findViewById(R.id.rlQd);
        tvQdDetails =(TextView) findViewById(R.id.tvQdDetails);
    }

    @Override
    public void initDatas() {
        postStatus=getIntent().getIntExtra("postStatus",0);
        codeid=getIntent().getIntExtra("codeid",0);
        curtime=getIntent().getStringExtra("curtime");
        setTitle("详情");

        params=new RequestParams();
        params.put("reqCode","getRegisterJobDetail");
        params.put("postStatus",postStatus);
        if (postStatus==1||postStatus==4||postStatus==5||postStatus==7) {
            params.put("registerid",codeid);
        }else if (postStatus==2||postStatus==6) {
            params.put("workid",codeid);
        }else if (postStatus==3) {
            params.put("settlementid",codeid);
        }

        if (postStatus == 1) {
            tvAppy.setText("待录用");
            llDly.setVisibility(View.VISIBLE);
        }else if (postStatus==3){
            tvAppy.setText("待结算");
            llDjs.setVisibility(View.VISIBLE);
            tvDStatus.setVisibility(View.GONE);
        }else if (postStatus==5){
            llDly.setVisibility(View.VISIBLE);
            tvAppy.setText("未录用");
            tvDStatus.setText("重新申请");
        }else if (postStatus==4){
            llDjs.setVisibility(View.VISIBLE);
            tvAppy.setText("待评价");
            tvDStatus.setText("评价");
        }else if (postStatus==7){
            llDjs.setVisibility(View.VISIBLE);
            tvAppy.setText("已评价");
            tvDStatus.setVisibility(View.GONE);
        }else if (postStatus==2){
            llDjs.setVisibility(View.VISIBLE);
            tvAppy.setText("待签到");
            rlQd.setVisibility(View.VISIBLE);
            tvDStatus.setText("扫描签到");
        }
        Log.e("param==>",params.toString());
        post("userJob",params,"getRegisterJobDetail");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvDStatus.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }
    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvDStatus:
                if (postStatus==1){
                    params=new RequestParams();
                    params.put("reqCode","deleteRegisterJob");
                    params.put("registerid",codeid);
                    post("userJob",params,"deleteRegisterJob");
                }else if (postStatus==4){
                   /* params=new RequestParams();
                    params.put("reqCode","registerJob");
                    params.put("registerid",codeid);
                    params.put("jobcode",jobByCodeBean.getDefaultAList().get(0).getJobcode());
                    params.put("userid", SharedUtils.getUserId(this));
                    post("userJob",params,"registerJob");*/
                    intent=new Intent(this,JobDetailsActivity.class);
                    intent.putExtra("jobcode",jobByCodeBean.getDefaultAList().get(0).getJobcode());
                    intent.putExtra("registerid",codeid);
                    Jump(intent);
                }else if (postStatus==5){
                    intent=new Intent(this,AssessActivity.class);
                    intent.putExtra("jobcode",jobByCodeBean.getDefaultAList().get(0).getJobcode());
                    intent.putExtra("registerid",codeid);
                    Jump(intent,ADDASSESS);
                }else if(postStatus==2){
                    if (curtime.indexOf(formatter.format(new Date(System.currentTimeMillis())))<0){
                        ToastUtils.show(this,"只能签到当天工作！");
                        return;
                    }
                    intent=new Intent(this,ScanActivity.class);
                    Jump(intent,QCODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==ADDASSESS&&resultCode==ADDASSESS){
            finish();
        }else if (requestCode==QCODE&&resultCode==88){
            String result=data.getStringExtra("result");
            if (!StringUtils.isEmpty(result)&&result.indexOf("&")>-1){
                params = new RequestParams();
                params.put("reqCode", "modifyWorkingDate");
                params.put("curDate", result.substring(result.indexOf("&")+1));
                params.put("jobcode", result.substring(0,result.indexOf("&")));
                params.put("userid", SharedUtils.getUserId(this));
                post("userJob", params, "modifyWorkingDate");
            }else{
                ToastUtils.show(this,"扫描内容不符合签到");
            }
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getRegisterJobDetail")){
            msg.what=1;
        }else if (requestname.equals("deleteRegisterJob")){
            msg.what=2;
        }else if (requestname.equals("registerJob")){
            msg.what=3;
        }else if (requestname.equals("modifyWorkingDate")){
            msg.what=4;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
