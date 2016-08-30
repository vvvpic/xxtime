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
import net.xxtime.bean.StudentUserMsgBean;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/29.
 */
public class MsgFollowAdapter extends BaseAdapter {

    private List<StudentUserMsgBean.DefaultAListBean> listBeens;
    private Context context;

    private int del=1;

    public void setDel(int del){
        this.del=del;
        notifyDataSetChanged();
    }

    public MsgFollowAdapter(List<StudentUserMsgBean.DefaultAListBean> listBeens,Context context){
        this.context=context;
        this.listBeens=listBeens;
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
        Follow_item follow_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.msgfollow_item,null);
            follow_item=new Follow_item();
            follow_item.ivChoose=(ImageView)convertView.findViewById(R.id.ivChoose);
            follow_item.tvName=(TextView) convertView.findViewById(R.id.tvName);
            follow_item.tvTime=(TextView) convertView.findViewById(R.id.tvTime);
            convertView.setTag(follow_item);
        }else {
            follow_item= (Follow_item) convertView.getTag();
        }

        follow_item.ivChoose.setVisibility(View.GONE);
        if (del==2){
            follow_item.ivChoose.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getMsgcontent())){
            follow_item.tvName.setText(listBeens.get(position).getMsgcontent());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getMsgtime())){
            follow_item.tvTime.setText(listBeens.get(position).getMsgtime());
        }

        follow_item.ivChoose.setImageResource(R.mipmap.agree_n);
        if (listBeens.get(position).del){
            follow_item.ivChoose.setImageResource(R.mipmap.agree_p);
        }

        return convertView;
    }

    class Follow_item{
        ImageView  ivChoose;
        TextView tvName, tvTime;
    }
}
