package net.xxtime.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.ApplyTimeBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.DataUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class ApplyTimeAdapter extends BaseAdapter {

    private List<ApplyTimeBean> listapplys;
    private Context context;
    SimpleDateFormat formatter = new SimpleDateFormat ("MM-dd");
    SimpleDateFormat dformatter = new SimpleDateFormat ("yyyy-MM-dd");

    public ApplyTimeAdapter(List<ApplyTimeBean> listapplys,Context context){
        this.listapplys=listapplys;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listapplys.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Apply_item apply_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.choose_time_item,null);
            apply_item=new Apply_item();
            apply_item.tvDate=(TextView)convertView.findViewById(R.id.tvDate);
            apply_item.tvTime=(TextView)convertView.findViewById(R.id.tvTime);
            apply_item.rlDate=(RelativeLayout) convertView.findViewById(R.id.rlDate);
            convertView.setTag(apply_item);
        }else {
            apply_item= (Apply_item) convertView.getTag();
        }

        apply_item.tvDate.setText("");
        if (listapplys.get(position).applydate>0){
            apply_item.tvDate.setText(formatter.format(new Date(listapplys.get(position).applydate))+"  "
                    + DataUtils.getWeek(dformatter.format(new Date(listapplys.get(position).applydate))));
        }

        if (!StringUtils.isEmpty(listapplys.get(position).applytime)){
            apply_item.tvTime.setText(listapplys.get(position).applytime);
        }

        if (listapplys.get(position).choosebool){
            apply_item.rlDate.setBackgroundColor(context.getResources().getColor(R.color.blue));
            apply_item.tvTime.setTextColor(context.getResources().getColor(R.color.white));
            apply_item.tvDate.setTextColor(context.getResources().getColor(R.color.white));
        }else {
            apply_item.rlDate.setBackgroundColor(context.getResources().getColor(R.color.white));
            apply_item.tvTime.setTextColor(context.getResources().getColor(R.color.txt_666));
            apply_item.tvDate.setTextColor(context.getResources().getColor(R.color.txt_666));
        }

        return convertView;
    }

   class Apply_item{
        RelativeLayout rlDate;
        TextView tvDate, tvTime;
   }
}
