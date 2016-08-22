package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.AccountBean;
import net.xxtime.bean.SortBean;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/19.
 */
public class SortAdapter extends BaseAdapter {

    private List<SortBean> listSorts;
    private Context context;
    private int cur;


    public void setCur(int cur){
        this.cur=cur;
        notifyDataSetChanged();
    }

    public SortAdapter(List<SortBean> listSorts, Context context, int cur){
        this.listSorts =listSorts;
        this.context=context;
        this.cur=cur;
    }

    @Override
    public int getCount() {
        return listSorts.size();
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
        Account_item account_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.account_item,null);
            account_item=new Account_item();
            account_item.tvAccount=(TextView)convertView.findViewById(R.id.tvAccount);
            convertView.setTag(account_item);
        }else {
            account_item= (Account_item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listSorts.get(position).name)){
            account_item.tvAccount.setText(listSorts.get(position).name);
        }

        if (cur==position){
            account_item.tvAccount.setTextColor(context.getResources().getColor(R.color.white));
            account_item.tvAccount.setBackgroundColor(context.getResources().getColor(R.color.blue));
        }else {
            account_item.tvAccount.setTextColor(context.getResources().getColor(R.color.txt_666));
            account_item.tvAccount.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        return convertView;
    }

    class Account_item{
        TextView tvAccount;
    }
}
