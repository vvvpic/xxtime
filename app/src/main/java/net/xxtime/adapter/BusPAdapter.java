package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.longtu.base.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.base.activity.XxtimeApplication;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/21.
 */
public class BusPAdapter extends BaseAdapter {

    private List<String> listbusps;
    private Context context;

    public BusPAdapter(List<String> listbusps,Context context){
        this.listbusps=listbusps;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listbusps.size();
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
        Bus_item bus_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.bus_item,null);
            bus_item=new Bus_item();
            bus_item.ivBus=(ImageView)convertView.findViewById(R.id.ivBus);
            convertView.setTag(bus_item);
        }else {
            bus_item= (Bus_item) convertView.getTag();
        }

        ViewGroup.LayoutParams params=bus_item.ivBus.getLayoutParams();
        params.width= XxtimeApplication.width/2- XxtimeApplication.width/24;
        params.height=params.width;

        if (!StringUtils.isEmpty(listbusps.get(position))){
            ImageLoader.getInstance().displayImage(listbusps.get(position),bus_item.ivBus);
        }
        return convertView;
    }

    class Bus_item{
        ImageView ivBus;
    }
}
