package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.xxtime.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/19.
 */
public class DateAdapter extends BaseAdapter {

    private List<Long> listdates;
    private Context context;
    private int cur=0;
    private int curday;

    private int all=1;
    SimpleDateFormat formatter = new SimpleDateFormat ("dd");

    public DateAdapter(Context context,List<Long> listdates,int cur){
        this.listdates=listdates;
        this.context=context;
        this.cur=cur;
        curday=cur;
    }

    public void setAll(int all){
        this.all=all;
    }

    public void setCur(int cur){
        this.cur=cur;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (all==1) {
            return listdates.size() + 1;
        }else {
            return listdates.size();
        }
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
        Date_item date_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.date_item,null);
            date_item=new Date_item();
            date_item.tvDate=(TextView)convertView.findViewById(R.id.tvDate);
            convertView.setTag(date_item);
        }else {
            date_item= (Date_item) convertView.getTag();
        }
        if (position==listdates.size()){
            date_item.tvDate.setText("全部");
        }else {
            if (listdates.get(position) == 0) {
                date_item.tvDate.setText("");
            } else {
                date_item.tvDate.setText(formatter.format(new Date(listdates.get(position))));
            }
        }

        if (cur==position){
            date_item.tvDate.setTextColor(context.getResources().getColor(R.color.white));
            date_item.tvDate.setBackgroundResource(R.drawable.circle_blue);
        }else {
            date_item.tvDate.setTextColor(context.getResources().getColor(R.color.txt_666));
            date_item.tvDate.setBackgroundResource(R.mipmap.bg);
        }

        if (curday==position){
            date_item.tvDate.setText("今天");
        }

        return convertView;
    }

    class Date_item{
        TextView tvDate;
    }
}
