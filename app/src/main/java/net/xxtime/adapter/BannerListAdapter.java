package net.xxtime.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.activity.PhotosFullActivity;
import net.xxtime.base.activity.XxtimeApplication;
import net.xxtime.bean.HomeLbtDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 唯图 on 2016/8/22.
 */
public class BannerListAdapter extends BaseAdapter {
    private List<HomeLbtDetailBean.DefaultAListBean> listBeens;
    private Context context;
    private List<String > listimages;

    public BannerListAdapter(List<HomeLbtDetailBean.DefaultAListBean> listBeens,Context context){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Banner_item banner_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.banner_list_item,null);
            banner_item=new Banner_item();
            banner_item.tvDetails=(TextView)convertView.findViewById(R.id.tvDetails);
            banner_item.ivImg=(ImageView) convertView.findViewById(R.id.ivImg);
            convertView.setTag(banner_item);
        }else {
            banner_item= (Banner_item) convertView.getTag();
        }

        ViewGroup.LayoutParams params=banner_item.ivImg.getLayoutParams();
        params.width= XxtimeApplication.width-XxtimeApplication.width/13;
        params.height=params.width/2;

        if (!StringUtils.isEmpty(listBeens.get(position).getDetailimg())){
            banner_item.ivImg.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(listBeens.get(position).getDetailimg(),banner_item.ivImg);
        }else {
            banner_item.ivImg.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getDetails())){
            banner_item.tvDetails.setText(listBeens.get(position).getDetails());
        }

        banner_item.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosFullActivity.class);
                listimages=new ArrayList<String>();
                listimages.add(listBeens.get(position).getDetailimg());
                intent.putStringArrayListExtra("urls", (ArrayList<String>) listimages);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class Banner_item{
        ImageView ivImg;
        TextView tvDetails;
    }
}
