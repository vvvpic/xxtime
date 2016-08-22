package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.AreaBean;

import java.util.List;

/**
 * Created by 唯图 on 2016/7/29.
 */
public class CitysAdapter extends BaseAdapter {
    private List<AreaBean> listcitys;
    private Context context;

    public CitysAdapter(List<AreaBean> listcitys, Context context){
        this.context=context;
        this.listcitys=listcitys;
    }

    @Override
    public int getCount() {
        return listcitys.size();
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
        City_item city_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.city_item,null);
            city_item=new City_item();
            city_item.tvHead=(TextView)convertView.findViewById(R.id.tvHead);
            city_item.tvCity=(TextView)convertView.findViewById(R.id.tvCity);
            city_item.viewHead=(View) convertView.findViewById(R.id.viewHead);
            convertView.setTag(city_item);
        }else {
            city_item= (City_item) convertView.getTag();
        }
        city_item.tvHead.setVisibility(View.GONE);
        city_item.viewHead.setVisibility(View.GONE);
        if (!StringUtils.isEmpty(listcitys.get(position).getAddName())){

            city_item.tvCity.setText(listcitys.get(position).getAddName());
        }

        if (position==0){
            city_item.tvHead.setVisibility(View.VISIBLE);
            city_item.tvHead.setText(listcitys.get(position).Pinyin.toUpperCase().substring(0,1).toUpperCase());
        }else {
            if (listcitys.get(position).Pinyin.toUpperCase().charAt(0)!=listcitys.get(position-1).Pinyin.toUpperCase().charAt(0)){
                city_item.tvHead.setVisibility(View.VISIBLE);
//                city_item.viewHead.setVisibility(View.VISIBLE);
                city_item.tvHead.setText(listcitys.get(position).Pinyin.toUpperCase().substring(0,1));
            }
        }

        return convertView;
    }

    class City_item{
        TextView tvHead,tvCity;
        View viewHead;
    }
}
