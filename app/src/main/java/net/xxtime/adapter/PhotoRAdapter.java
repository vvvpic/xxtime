package net.xxtime.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.base.activity.XxtimeApplication;
import net.xxtime.utils.ImageLoader;

import java.util.List;

/**
 * Created by 设计院 on 2016/6/12.
 */
public class PhotoRAdapter extends BaseAdapter {

    private List<String> listphotos;
    private Context context;
    private ImageLoader imageLoader;

    public PhotoRAdapter(List<String> listphotos, Context context){
        this.listphotos=listphotos;
        this.context=context;
        imageLoader = ImageLoader.getInstance(3 , ImageLoader.Type.LIFO);
    }

    @Override
    public int getCount() {
        return listphotos.size()<6?listphotos.size()+1:listphotos.size();
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
        Photo_item photo_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.photo_r_item,null);
            photo_item=new Photo_item();
            photo_item.ivPhoto=(ImageView)convertView.findViewById(R.id.ivPhoto);
            photo_item.ivDel=(ImageView)convertView.findViewById(R.id.ivDel);
            convertView.setTag(photo_item);
        }else {
            photo_item= (Photo_item) convertView.getTag();
        }
        Log.e("position==>",position+"");
        ViewGroup.LayoutParams params=photo_item.ivPhoto.getLayoutParams();
        params.width= XxtimeApplication.width/3-XxtimeApplication.width/20;
        params.height=params.width;
        photo_item.ivPhoto.setVisibility(View.VISIBLE);
        photo_item.ivPhoto.setBackgroundResource(R.drawable.btn_add_img_selecter);

       if (listphotos.size()>position){
           photo_item.ivDel.setVisibility(View.VISIBLE);
            if (!StringUtils.isEmpty(listphotos.get(position))){
                if (listphotos.get(position).indexOf("http://")>-1||listphotos.get(position).indexOf("https://")>-1){
                    com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listphotos.get(position),photo_item.ivPhoto);
                }else {
                    imageLoader.loadImage(listphotos.get(position), photo_item.ivPhoto);
                }
            }
        }else {
           photo_item.ivDel.setVisibility(View.GONE);
       }


        return convertView;
    }

    class Photo_item{
        ImageView ivPhoto;
        ImageView ivDel;
    }
}
