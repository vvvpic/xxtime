package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.TradeAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.TradeListBean;
import net.xxtime.utils.SharedUtils;

public class TradeListActivity extends BaseActivity {

    private ListView lvTrades;
    private TradeAdapter tradeAdapter;

    private TradeListBean tradeListBean;
    private Message msg;

    private RelativeLayout rlEmpty;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    tradeListBean= JSONObject.parseObject(msg.obj.toString(),TradeListBean.class);
                    if (tradeListBean!=null&&tradeListBean.getBflag().equals("1")){
                        if (tradeListBean.getDefaultAList()!=null&&tradeListBean.getDefaultAList().size()>0) {
                            tradeAdapter = new TradeAdapter(tradeListBean.getDefaultAList(), TradeListActivity.this);
                            lvTrades.setAdapter(tradeAdapter);
                        }else {
                            lvTrades.setVisibility(View.GONE);
                            rlEmpty.setVisibility(View.VISIBLE);
                        }
                    }else {
                        lvTrades.setVisibility(View.GONE);
                        rlEmpty.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_trade_list);
    }

    @Override
    public void initViews() {
        lvTrades=(ListView)findViewById(R.id.lvTrades);
        rlEmpty=(RelativeLayout)findViewById(R.id.rlEmpty);
    }

    @Override
    public void initDatas() {
        setTitle("交易明细");
        params=new RequestParams();
        params.put("reqCode","getTradeDetails");
        params.put("userid", SharedUtils.getUserId(this));
        post("studentUser",params,"getTradeDetails");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getTradeDetails")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
