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
import net.xxtime.bean.EmployedStudentBean;
import net.xxtime.utils.OptionsUtils;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/21.
 */
public class RecruitAdapter extends BaseAdapter {

    private List<EmployedStudentBean.DefaultAListBean> listBeens;
    private Context context;

    public RecruitAdapter(List<EmployedStudentBean.DefaultAListBean> listBeens,Context context){
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
        Recruit_item recruit_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.cruit_item,null);
            recruit_item=new Recruit_item();
            recruit_item.tvName=(TextView)convertView.findViewById(R.id.tvName);
            recruit_item.tvTime=(TextView)convertView.findViewById(R.id.tvTime);
            recruit_item.tvSchool=(TextView)convertView.findViewById(R.id.tvSchool);
            recruit_item.ivAvatar=(ImageView) convertView.findViewById(R.id.ivAvatar);
            convertView.setTag(recruit_item);
        }else {
            recruit_item= (Recruit_item) convertView.getTag();
        }

        recruit_item.ivAvatar.setImageResource(R.mipmap.ic_user_default);

        if (!StringUtils.isEmpty(listBeens.get(position).getName())){
            recruit_item.tvName.setText(listBeens.get(position).getName());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getRegistertime())){
            recruit_item.tvTime.setText(listBeens.get(position).getRegistertime());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getSchoolname())){
            recruit_item.tvSchool.setText(listBeens.get(position).getSchoolname());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getLogo())){
            ImageLoader.getInstance().displayImage(listBeens.get(position).getLogo(),recruit_item.ivAvatar, OptionsUtils.getSimpleOptions(80));
        }

        return convertView;
    }

    class Recruit_item{
        ImageView  ivAvatar;
        TextView tvTime, tvName, tvSchool;
    }
}
