package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.bean.FocusBusBean;
import net.xxtime.utils.OptionsUtils;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/26.
 */
public class FocusBusAdapter extends BaseAdapter {
    private List<FocusBusBean.DefaultAListBean> listfocus;
    private Context context;

    public FocusBusAdapter(List<FocusBusBean.DefaultAListBean> listfocus,Context context){
        this.listfocus=listfocus;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listfocus.size();
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
        Focus_item focus_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.focus_item,null);
            focus_item=new Focus_item();
            focus_item.ivAvatar=(ImageView)convertView.findViewById(R.id. ivAvatar);
            focus_item.tvName=(TextView)convertView.findViewById(R.id.tvName);
            convertView.setTag(focus_item);
        }else {
            focus_item= (Focus_item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listfocus.get(position).getBuslogo())){
            ImageLoader.getInstance().displayImage(listfocus.get(position).getBuslogo(),focus_item.ivAvatar, OptionsUtils.getSimpleOptions(20));
        }

        if (!StringUtils.isEmpty(listfocus.get(position).getBusfullname())){
            focus_item.tvName.setText(listfocus.get(position).getBusfullname());
        }

        return convertView;
    }

    class Focus_item{
        ImageView  ivAvatar;
        TextView tvName;
    }
}
