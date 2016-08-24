package net.xxtime.adapter;

import android.content.Context;
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
import net.xxtime.bean.BusCommentBean;
import net.xxtime.utils.OptionsUtils;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class CommentAdapter extends BaseAdapter {

    private List<BusCommentBean.DefaultAListBean> listBeens;
    private Context context;

    public CommentAdapter(List<BusCommentBean.DefaultAListBean> listBeens,Context context){
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
        Comment_Item comment_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.comment_item,null);
            comment_item=new Comment_Item();
            comment_item.ivAvatar=(ImageView)convertView.findViewById(R.id.ivAvatar);
            comment_item.tvContent=(TextView)convertView.findViewById(R.id.tvContent);
            comment_item.tvName=(TextView)convertView.findViewById(R.id.tvName);
            comment_item.tvTime=(TextView)convertView.findViewById(R.id.tvTime);
            comment_item.rbAssess=(RatingBar) convertView.findViewById(R.id.rbAssess);
            convertView.setTag(comment_item);
        }else {
            comment_item= (Comment_Item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getName())){
            comment_item.tvName.setText(listBeens.get(position).getName());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getCommenttime())){
            comment_item.tvTime.setText(listBeens.get(position).getCommenttime());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getContent())){
            comment_item.tvContent.setText(listBeens.get(position).getContent());
        }

        comment_item.ivAvatar.setImageResource(R.mipmap.ic_user_default);
        if (!StringUtils.isEmpty(listBeens.get(position).getLogo())){
            ImageLoader.getInstance().displayImage(listBeens.get(position).getLogo(),comment_item.ivAvatar, OptionsUtils.getSimpleOptions(80));
        }

        comment_item.rbAssess.setRating(listBeens.get(position).getLevel());

        return convertView;
    }

    class Comment_Item{
        ImageView ivAvatar;
        TextView tvTime, tvName, tvContent;
        RatingBar rbAssess;
    }
}
