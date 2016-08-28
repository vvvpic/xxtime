package net.xxtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.longtu.base.util.StringUtils;

import net.xxtime.R;
import net.xxtime.bean.TradeListBean;

import java.util.List;

/**
 * Created by 唯图 on 2016/8/28.
 */
public class TradeAdapter extends BaseAdapter {

    private List<TradeListBean.DefaultAListBean> listBeens;
    private Context context;

    public TradeAdapter(List<TradeListBean.DefaultAListBean> listBeens,Context context){
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
        Trade_item trade_item;
        if (convertView==null){
            trade_item=new Trade_item();
            convertView= LayoutInflater.from(context).inflate(R.layout.trade_item,null);
            trade_item.tvAmounts=(TextView)convertView.findViewById(R.id.tvAmounts);
            trade_item.tvTime=(TextView)convertView.findViewById(R.id.tvTime);
            trade_item.tvTradename=(TextView)convertView.findViewById(R.id.tvTradename);
            trade_item.tvTradestate=(TextView)convertView.findViewById(R.id.tvTradestate);
            convertView.setTag(trade_item);
        }else {
            trade_item= (Trade_item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getTradetime())){
            trade_item.tvTime.setText(listBeens.get(position).getTradetime());
        }

        if (!StringUtils.isEmpty(listBeens.get(position).getTradename())){
            trade_item.tvTradename.setText(listBeens.get(position).getTradename());
        }

        trade_item.tvAmounts.setText("金额:"+listBeens.get(position).getAmounts());

        if (listBeens.get(position).getTradestate()==0){
            trade_item.tvTradestate.setText("状态:未完成");
        }else if (listBeens.get(position).getTradestate()==0){
            trade_item.tvTradestate.setText("状态:交易完成");
        }else {
            trade_item.tvTradestate.setText("状态:交易失败");
        }

        return convertView;
    }

    class Trade_item{
        TextView  tvTime, tvAmounts, tvTradename, tvTradestate;
    }
}
