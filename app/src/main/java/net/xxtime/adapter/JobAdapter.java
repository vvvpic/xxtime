package net.xxtime.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.activity.AssessActivity;
import net.xxtime.bean.GetHomeLbtBean;
import net.xxtime.bean.JobByConditionBean;
import net.xxtime.listener.SignListener;
import net.xxtime.utils.Contact;
import net.xxtime.utils.OptionsUtils;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/19.
 */
public class JobAdapter extends BaseAdapter {

    private List<JobByConditionBean.DefaultAListBean> listsdefaultAListBeens;
    private Context context;
    private String status="";

    private SignListener listener;

    public void setSignListener( SignListener listener){
        this.listener=listener;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public JobAdapter(List<JobByConditionBean.DefaultAListBean> listsdefaultAListBeens,Context context){
        this.listsdefaultAListBeens=listsdefaultAListBeens;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listsdefaultAListBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Job_item job_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.job_item,null);
            job_item=new Job_item();
            job_item.ivBuslogo=(ImageView)convertView.findViewById(R.id.ivBuslogo);
            job_item.tvAppy=(TextView) convertView.findViewById(R.id.tvAppy);
            job_item.rbAssess=(RatingBar) convertView.findViewById(R.id.rbAssess);
            job_item.tvJobname=(TextView) convertView.findViewById(R.id.tvJobname);
            job_item.tvPrice=(TextView) convertView.findViewById(R.id.tvPrice);
            job_item.tvUint=(TextView) convertView.findViewById(R.id.tvUint);
            job_item.tvTime=(TextView) convertView.findViewById(R.id.tvTime);
            job_item.tvLucency=(TextView) convertView.findViewById(R.id.tvLucency);
            job_item.tvAdress=(TextView) convertView.findViewById(R.id.tvAdress);
            job_item.tvStatus=(TextView) convertView.findViewById(R.id.tvStatus);
            job_item.tvOperate=(TextView) convertView.findViewById(R.id.tvOperate);
            convertView.setTag(job_item);
        }else {
            job_item= (Job_item) convertView.getTag();
        }

        job_item.tvOperate.setVisibility(View.GONE);

        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getBuslogo())){
            ImageLoader.getInstance().displayImage(listsdefaultAListBeens.get(position).getBuslogo(),job_item.ivBuslogo, OptionsUtils.getSimpleOptions(10));
        }

        Drawable drawable = context.getResources().getDrawable(R.mipmap.ico_cheng);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        if (listsdefaultAListBeens.get(position).isCheng>0){
            job_item.tvJobname.setCompoundDrawables(null, null, drawable, null); //分别对应 左上右下
        }else {
            job_item.tvJobname.setCompoundDrawables(null, null, null, null); //分别对应 左上右下
        }

        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getJobname())){
            job_item.tvJobname.setText(listsdefaultAListBeens.get(position).getJobname());
        }

        job_item.tvPrice.setText(listsdefaultAListBeens.get(position).getSalary()+"");

        if (listsdefaultAListBeens.get(position).getSalarytype()==1){
            job_item.tvUint.setText("元/时");
        }else if (listsdefaultAListBeens.get(position).getSalarytype()==2){
            job_item.tvUint.setText("元/天");
        }else if (listsdefaultAListBeens.get(position).getSalarytype()==3){
            job_item.tvUint.setText("元/单");
        }

        job_item.tvAdress.setText("");
        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getAreaname())){
            job_item.tvAdress.append(listsdefaultAListBeens.get(position).getAreaname());
        }
        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getAddress())){
            job_item.tvAdress.append(listsdefaultAListBeens.get(position).getAddress());
        }

        if (!StringUtils.isEmpty(job_item.tvAdress.getText().toString())){
            Contact.get(job_item.tvAdress.getText().toString(),context,job_item.tvAdress);
        }

        if (StringUtils.isEmpty(job_item.tvAdress.getText().toString())){
            job_item.tvAdress.setText("地址不限");
        }

        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getAreaname())){
            job_item.tvAdress.setText(listsdefaultAListBeens.get(position).getAreaname());
        }
        if (listsdefaultAListBeens.get(position).getSettlementtime()==1){
            job_item.tvStatus.setText("日结");
        }else if (listsdefaultAListBeens.get(position).getSettlementtime()==2){
            job_item.tvStatus.setText("周结");
        }else if (listsdefaultAListBeens.get(position).getSettlementtime()==3){
            job_item.tvStatus.setText("月结");
        }else if (listsdefaultAListBeens.get(position).getSettlementtime()==4){
            job_item.tvStatus.setText("完工结算");
        }

        job_item.tvTime.setText("");
        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).jobstartdate)&&!StringUtils.isEmpty(listsdefaultAListBeens.get(position).jobenddate)){
            job_item.tvTime.append(listsdefaultAListBeens.get(position).jobstartdate+"~"+listsdefaultAListBeens.get(position).jobenddate);
        }else {
            job_item.tvTime.setText("工作日期不限");
        }

       if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getApplystartdate())&&!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getApplyenddate())){
           int status=Contact.getStatus(listsdefaultAListBeens.get(position).getApplystartdate(),Contact.CurTime,listsdefaultAListBeens.get(position).getApplyenddate());

           if (status==1){
               job_item.tvAppy.setText("报名中");
           }else if (status==-1){
               job_item.tvAppy.setText("已结束");
           }
       }

        if (!StringUtils.isEmpty(status)){
            job_item.tvAppy.setText(status);
        }

        if (!StringUtils.isEmpty(listsdefaultAListBeens.get(position).getLabelnames())){
            job_item.tvLucency.setText(listsdefaultAListBeens.get(position).getLabelnames().replace(","," "));
        }else {
            job_item.tvLucency.setText("无附加说明");
        }

        job_item.rbAssess.setRating(listsdefaultAListBeens.get(position).getStarNum());


        if (status.equals("待评价")){
            job_item.tvOperate.setVisibility(View.VISIBLE);
            job_item.tvOperate.setText("评价");
        }else if (status.equals("待签到")){
            job_item.tvOperate.setVisibility(View.VISIBLE);
            job_item.tvOperate.setText("扫描签到");
        }

        job_item.tvOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("待评价")){
                    Intent intent=new Intent(context,AssessActivity.class);
                    intent.putExtra("jobcode",listsdefaultAListBeens.get(position).getJobcode());
                    intent.putExtra("registerid",listsdefaultAListBeens.get(position).registerid);
                    context.startActivity(intent);
                }else if (status.equals("待签到")){
                    listener.sign(position);
                }
            }
        });

       // job_item.rbAssess.setEnabled(false);

        return convertView;
    }

    class Job_item{
        ImageView ivBuslogo;
        TextView tvAppy;
        RatingBar rbAssess;
        TextView tvJobname, tvPrice, tvUint, tvTime ,tvLucency,  tvAdress, tvStatus;
        TextView tvOperate;
    }
}
