package net.xxtime.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.AreaBean;
import net.xxtime.bean.CitysBean;

import java.util.List;

public class AreaAdapter extends BaseAdapter {

    private Context context;
    private List<AreaBean> listAreas;
    
    public AreaAdapter(List<AreaBean> listAreas,Context context) {
        this.listAreas=listAreas;
        this.context=context;
    }

    @Override
    public int getCount() { 
        return listAreas.size();
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

        if (listAreas.get(position).nextbool){
            area_item.iv_a.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isEmpty(listAreas.get(position).getAddName())){
            area_item.tv_name.setText(listAreas.get(position).getAddName());
        }

        return convertView;
    }
    
    class Area_item{
        TextView tv_name ;
        ImageView iv_a;
    }

}
