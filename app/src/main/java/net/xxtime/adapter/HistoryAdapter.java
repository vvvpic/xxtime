package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longtu.base.util.ObjectUtils;
import com.longtu.base.util.StringUtils;

import net.xxtime.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 唯图 on 2016/8/19.
 */
public class HistoryAdapter extends BaseAdapter {

    private List<Map<String,Object>> listHistorys;
    private Context context;


    public HistoryAdapter(List<Map<String,Object>> listHistorys, Context context ){
        this.listHistorys =listHistorys;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listHistorys.size()>0?listHistorys.size():1;
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
        History_item his_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.history_item,null);
            his_item=new History_item();
            his_item.tvName=(TextView)convertView.findViewById(R.id.tvName);
            convertView.setTag(his_item);
        }else {
            his_item= (History_item) convertView.getTag();
        }

        if (listHistorys.size()>0) {
            if (!ObjectUtils.isEquals(listHistorys.get(position).get("jobname"), null)) {
                his_item.tvName.setText(listHistorys.get(position).get("jobname").toString());
            }
        }else {
            his_item.tvName.setText("暂无历史搜索");
        }


        return convertView;
    }

    class History_item {
        TextView tvName;
    }
}
