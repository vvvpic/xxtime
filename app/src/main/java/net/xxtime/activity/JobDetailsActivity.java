package net.xxtime.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
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

/**
 * 工作详情
 */
public class JobDetailsActivity extends BaseActivity {

    private String html="若企业提出任何收费要求请一律拒绝，并向我们举报。举报电话：<font size=\"3\" color=\"#17B2EB\">021-80376968</font>";

    private TextView tvTel;
    private String jobcode;

    private Message msg;
    private JobByCodeBean jobByCodeBean;

    private ImageView ivBuslogo;
    private TextView tvAppy;
    private RatingBar rbAssess;
    private TextView tvJobname, tvPrice, tvUint, tvTime ,tvLucency,  tvAdress, tvStatus;

    private TextView tvNumber, tvWay, tvDect, tvApplyTime;
    private TextView tvComMane;

    private TextView tvWorkTime, tvWorkAddress, tvWorkContact;

    private TextView tvEducation, tvLanguage, tvAsk;

    private TextView tvWage, tvWorkIntro, tvAccount;

    private RelativeLayout rlCollect;
    private ImageView ivCollect;
    private TextView tvCollect, tvApply;
    private CommonBean commonBean;

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
                        ivCollect.setImageResource(R.mipmap.ico_collect_p);
                        tvCollect.setText("已关注");
                        tvCollect.setTextColor(getResources().getColor(R.color.blue));
                        tvCollect.setEnabled(false);
                    }
                    ToastUtils.show(JobDetailsActivity.this,commonBean.getMsg());
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
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getAreaname())){
            tvAdress.append(jobByCodeBean.getDefaultAList().get(0).getAreaname());
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
            tvApplyTime.setText(jobByCodeBean.getDefaultAList().get(0).getApplyenddate());
            int status= Contact.getStatus(jobByCodeBean.getDefaultAList().get(0).getApplystartdate(),Contact.CurTime,jobByCodeBean.getDefaultAList().get(0).getApplyenddate());
            if (status==1){
                tvAppy.setText("报名中");
            }else if (status==-1){
                tvAppy.setText("已结束");
            }
        }else {
            tvTime.setText("报名日期不限");
            tvApplyTime.setText("不限");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getLabelnames())){
            tvLucency.setText(jobByCodeBean.getDefaultAList().get(0).getLabelnames().replace(","," "));
        }

        rbAssess.setRating(jobByCodeBean.getDefaultAList().get(0).getStarNum());

        if (jobByCodeBean.getDefaultAList().get(0).getRecruitall()>0){
            tvNumber.setText(jobByCodeBean.getDefaultAList().get(0).getRecruitall()+"人，男女不限");
        }else  if (jobByCodeBean.getDefaultAList().get(0).getRecruitboy()>0){
            tvNumber.setText(jobByCodeBean.getDefaultAList().get(0).getRecruitboy()+"人，仅限男士");
        }else  if (jobByCodeBean.getDefaultAList().get(0).getRecruitgirl()>0){
            tvNumber.setText(jobByCodeBean.getDefaultAList().get(0).getRecruitgirl()+"人，仅限女士");
        }else {
            tvNumber.setText("人数不限，男女不限");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getBusfullname())){
            tvComMane.setText(jobByCodeBean.getDefaultAList().get(0).getBusfullname());
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getCommission())){
            tvDect.setText(jobByCodeBean.getDefaultAList().get(0).getCommission());
        }else {
            tvDect.setText("无");
        }

        if (jobByCodeBean.getDefaultAList().get(0).getSettlementtype()==1){
            tvWay.setText("线上结算");
        }else {
            tvWay.setText("线下结算");
        }

        tvWorkTime.setText("");
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobstartdate())&&
                !StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobenddate())){
            tvWorkTime.setText(jobByCodeBean.getDefaultAList().get(0).getJobstartdate().substring(5,10)+"~"+
                    jobByCodeBean.getDefaultAList().get(0).getJobenddate().substring(5,10)+" ");
        }else {
            tvWorkTime.setText("日期不限 ");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobstarttime())){
            tvWorkTime.append(jobByCodeBean.getDefaultAList().get(0).getJobstarttime().substring(0,5));
            if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobendtime())){
                tvWorkTime.append("~"+jobByCodeBean.getDefaultAList().get(0).getJobendtime().substring(0,5));
            }
        }else {
            tvWorkTime.append("时间不限 ");
        }


        tvWorkAddress.setText("");
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getProvicename())){
            tvWorkAddress.append(jobByCodeBean.getDefaultAList().get(0).getProvicename());
        }
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getCityname())){
            tvWorkAddress.append(jobByCodeBean.getDefaultAList().get(0).getCityname());
        }
        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getAreaname())){
            tvWorkAddress.append(jobByCodeBean.getDefaultAList().get(0).getAreaname());
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getDegreename())){
            tvEducation.setText(jobByCodeBean.getDefaultAList().get(0).getDegreename());
        }else {
            tvEducation.setText("不限");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getOtherforeign())){
            tvLanguage.setText(jobByCodeBean.getDefaultAList().get(0).getOtherforeign());
        }else {
            tvLanguage.setText("不限");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobdemands())){
            tvAsk.setText(jobByCodeBean.getDefaultAList().get(0).getJobdemands());
        }else {
            tvAsk.setText("无");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getBasicsalary())){
            tvWage.setText(jobByCodeBean.getDefaultAList().get(0).getBasicsalary());
        }else {
            tvWage.setText("无");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getJobdescription())){
            tvWorkIntro.setText(jobByCodeBean.getDefaultAList().get(0).getJobdescription());
        }else {
            tvWorkIntro.setText("无");
        }

        if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getOther())){
            tvAccount.setText(jobByCodeBean.getDefaultAList().get(0).getOther());
        }else {
            tvAccount.setText("无");
        }

        if (jobByCodeBean.getDefaultAList().get(0).getIsFocus()==1){
            ivCollect.setImageResource(R.mipmap.ico_collect_p);
            tvCollect.setText("已关注");
            tvCollect.setTextColor(getResources().getColor(R.color.blue));
            tvCollect.setEnabled(false);
        }else {
            ivCollect.setImageResource(R.mipmap.ico_collect_n);
            tvCollect.setText("关注");
            tvCollect.setTextColor(getResources().getColor(R.color.txt_666));
        }

        if (jobByCodeBean.getDefaultAList().get(0).getIsEmploy()==0){
            if (!StringUtils.isEmpty(jobByCodeBean.getDefaultAList().get(0).getContactmphone())) {
                tvWorkContact.setText(jobByCodeBean.getDefaultAList().get(0).getContactmphone());
            }
        }else {
            tvWorkContact.setText("需企业录用后才会显示");
        }

        if (jobByCodeBean.getDefaultAList().get(0).isApply==1){
            tvApply.setText("已申请");
            tvApply.setEnabled(false);
        }else {

        }

        if (jobByCodeBean.getDefaultAList().get(0).getJobcontinuous()==1){
            tvWorkTime.append("\n必须持续工作");
        }else {
            tvWorkTime.append("\n可自由选择工作日期");
        }



    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_job_details);
    }

    @Override
    public void initViews() {
        tvTel=(TextView)findViewById(R.id.tvTel);
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

        tvNumber =(TextView) findViewById(R.id.tvNumber);
        tvWay =(TextView) findViewById(R.id.tvWay);
        tvDect =(TextView) findViewById(R.id.tvDect);
        tvApplyTime=(TextView) findViewById(R.id.tvApplyTime);
        tvComMane=(TextView) findViewById(R.id.tvComMane);

        tvWorkTime =(TextView) findViewById(R.id.tvWorkTime);
        tvWorkAddress =(TextView) findViewById(R.id.tvWorkAddress);
        tvWorkContact=(TextView) findViewById(R.id.tvWorkContact);

        tvEducation =(TextView) findViewById(R.id.tvEducation);
        tvLanguage =(TextView) findViewById(R.id.tvLanguage);
        tvAsk=(TextView) findViewById(R.id.tvAsk);

        tvWage  =(TextView) findViewById(R.id.tvWage);
        tvWorkIntro  =(TextView) findViewById(R.id.tvWorkIntro);
        tvAccount =(TextView) findViewById(R.id.tvAccount);

        rlCollect  =(RelativeLayout) findViewById(R.id.rlCollect);
        ivCollect  =(ImageView) findViewById(R.id.ivCollect);
        tvCollect  =(TextView) findViewById(R.id.tvCollect);
        tvApply =(TextView) findViewById(R.id.tvApply);

    }

    @Override
    public void initDatas() {
        setTitle("岗位详情");
        setRightVisibility(View.VISIBLE);
        setRightResource(R.mipmap.ico_share);
        tvTel.setText(Html.fromHtml(html));
        jobcode=getIntent().getStringExtra("jobcode");
        params=new RequestParams();
        params.put("reqCode","getJobByCode");
        params.put("jobcode",jobcode);
        params.put("userid", SharedUtils.getUserId(this));
        post("job",params,"getJobByCode");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        rlCollect.setOnClickListener(this);
        tvTel.setOnClickListener(this);
        tvComMane.setOnClickListener(this);
        tvApply.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlCollect:
                params=new RequestParams();
                params.put("reqCode","focusPosition");
                params.put("userid",SharedUtils.getUserId(this));
                params.put("type",0);
                params.put("code",jobcode);
                post("userJob",params,"focusPosition");
                break;
            case R.id.tvTel:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:021-80376968"));
                startActivity(intent);
                break;
            case R.id.tvComMane:
                intent=new Intent(this,FirmHomeActivity.class);
                if (jobByCodeBean!=null) {
                    intent.putExtra("buscode", jobByCodeBean.getDefaultAList().get(0).getBuscode());
                    intent.putExtra("busname", jobByCodeBean.getDefaultAList().get(0).getBusfullname());

                }
                Jump(intent);
                break;
            case R.id.tvApply:
                intent=new Intent(this,ApplyActivity.class);
                if (jobByCodeBean!=null) {
                    intent.putExtra("jobcode", jobByCodeBean.getDefaultAList().get(0).getJobcode());
                    intent.putExtra("jobname", jobByCodeBean.getDefaultAList().get(0).getJobname());
                    intent.putExtra("price",tvPrice.getText().toString()+tvUint.getText().toString());
                    intent.putExtra("address",tvWorkAddress.getText().toString());
                    intent.putExtra("jobstartdate",jobByCodeBean.getDefaultAList().get(0).getJobstartdate());
                    intent.putExtra("jobenddate",jobByCodeBean.getDefaultAList().get(0).getJobenddate());
                    intent.putExtra("jobstarttime",jobByCodeBean.getDefaultAList().get(0).getJobstarttime());
                    intent.putExtra("jobendtime",jobByCodeBean.getDefaultAList().get(0).getJobendtime());
                    intent.putExtra("continuous",jobByCodeBean.getDefaultAList().get(0).getJobcontinuous());
                }
                Jump(intent);
                break;
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getJobByCode")){
            msg.what=1;
        }else if (requestname.equals("focusPosition")){
            msg.what=2;
        }

        msg.obj=response;
        handler.sendMessage(msg);
    }
}
