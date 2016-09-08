package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.DegreeBean;

import java.util.List;

public class DegreeAdapter extends BaseAdapter {

    private Context context;
    private List<DegreeBean.DefaultAListBean> listDefaults;

    public DegreeAdapter( List<DegreeBean.DefaultAListBean>  listDefaults, Context context) {
        this.listDefaults=listDefaults;
        this.context=context;
    }

    @Override
    public int getCount() { 
        return listDefaults.size();
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
        Area_item area_item;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.area_item, null);
            area_item=new Area_item();
            area_item.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            area_item.iv_a=(ImageView)convertView.findViewById(R.id.iv_a);
            convertView.setTag(area_item);
        }else{
            area_item=(Area_item) convertView.getTag();
        }
        
        area_item.iv_a.setVisibility(View.GONE);


        if (!StringUtils.isEmpty(listDefaults.get(position).getDegreename())){
            area_item.tv_name.setText(listDefaults.get(position).getDegreename());
        }

        return convertView;
    }
    
    class Area_item{
        TextView tv_name ;
        ImageView iv_a;
    }

}
