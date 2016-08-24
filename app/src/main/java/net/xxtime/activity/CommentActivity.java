package net.xxtime.activity;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.CommentAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.BusCommentBean;
import net.xxtime.bean.BusCommentLevelBean;
import net.xxtime.view.CommentView;

public class CommentActivity extends BaseActivity {

    private ListView lvComments;
    private View headView;
    private TextView  tvRate ;
    private CommentView cvRate;
    private String buscode;
    private BusCommentBean commentBean;
    private BusCommentLevelBean busCommentLevelBean;

    private CommentAdapter commentAdapter;

    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commentBean= JSONObject.parseObject(msg.obj.toString(),BusCommentBean.class);
                    if (commentBean!=null&&commentBean.getBflag().equals("1")){
                        commentAdapter=new CommentAdapter(commentBean.getDefaultAList(),CommentActivity.this);
                        lvComments.setAdapter(commentAdapter);
                    }
                    break;
                case 2:
                    busCommentLevelBean=JSONObject.parseObject(msg.obj.toString(),BusCommentLevelBean.class);
                    if (busCommentLevelBean!=null&&busCommentLevelBean.getBflag().equals("1")){
                        setLevel();
                    }
                    break;
            }
        }
    };

    private void setLevel(){
        tvRate.setText(((int)busCommentLevelBean.getDefaultAList().get(0).getGoodpercent()*100)+"%");
        cvRate.setProgess((int)busCommentLevelBean.getDefaultAList().get(0).getGoodpercent()*100,
                (int)busCommentLevelBean.getDefaultAList().get(0).getInpercent()*100,
                (int)busCommentLevelBean.getDefaultAList().get(0).getBadpercent()*100
                );
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_comment);
    }

    @Override
    public void initViews() {
        lvComments=(ListView)findViewById(R.id.lvComments);
        headView= LayoutInflater.from(this).inflate(R.layout.comment_header,null);
        tvRate =(TextView)headView.findViewById(R.id.tvRate) ;
        cvRate=(CommentView) headView.findViewById(R.id.cvRate) ;
        lvComments.addHeaderView(headView);
    }

    @Override
    public void initDatas() {
        setTitle("累计获取评价");
        buscode=getIntent().getStringExtra("buscode");

        params=new RequestParams();
        params.put("reqCode","getBusCommentByBuscode");
        params.put("buscode", buscode);
        post("userJob",params,"getBusCommentByBuscode");

        params=new RequestParams();
        params.put("reqCode","getBusCommentLevelByBuscode");
        params.put("buscode", buscode);
        post("userJob",params,"getBusCommentLevelByBuscode");
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
        if (requestname.equals("getBusCommentByBuscode")){
            msg.what=1;
        }else if (requestname.equals("getBusCommentLevelByBuscode")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
