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
import net.xxtime.bean.GetHomeLbtBean;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class BannerAdapter extends BaseAdapter {

    private Context context;
    private List<GetHomeLbtBean.DefaultAListBean> listBeen;

    public BannerAdapter(List<GetHomeLbtBean.DefaultAListBean> listBeen,Context context){
        this.listBeen=listBeen;
        this.context=context;
    }
    @Override
    public int getCount() {
        return listBeen.size();
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
        Banner_item banner_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            banner_item=new Banner_item();
            banner_item.ivBanner=(ImageView)convertView.findViewById(R.id.ivBanner);
            convertView.setTag(banner_item);
        }else {
            banner_item= (Banner_item) convertView.getTag();
        }

        ViewGroup.LayoutParams params=banner_item.ivBanner.getLayoutParams();
        params.width= XxtimeApplication.width;
        params.height=params.width*3/7;

        if (!StringUtils.isEmpty(listBeen.get(position).getImage())){
            ImageLoader.getInstance().displayImage(listBeen.get(position).getImage(),banner_item.ivBanner);
        }
        return convertView;
    }

    class Banner_item{
        ImageView ivBanner;
    }
}
