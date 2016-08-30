package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.InviteRecordBean;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/29.
 */
public class InviteAdapter extends BaseAdapter {

    private List<InviteRecordBean.DefaultAListBean> listBeens;
    private Context context;

    public InviteAdapter(List<InviteRecordBean.DefaultAListBean> listBeens, Context context){
        this.listBeens=listBeens;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listBeens.size();
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
        Invite_item invite_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.invite_item,null);
            invite_item=new Invite_item();
            invite_item.tvCreatetime=(TextView)convertView.findViewById(R.id. tvCreatetime);
            invite_item.tvTelephone=(TextView)convertView.findViewById(R.id. tvTelephone);
            convertView.setTag(invite_item);
        }else {
            invite_item= (Invite_item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getTelephone())){
            invite_item.tvTelephone.setText(listBeens.get(position).getTelephone());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getCreatetime())){
            invite_item.tvCreatetime.setText(listBeens.get(position).getCreatetime());
        }

        return convertView;
    }

    class Invite_item{
        TextView tvTelephone, tvCreatetime;
    }
}
