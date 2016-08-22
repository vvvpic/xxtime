package net.xxtime.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.CommentView;

public class CommentActivity extends BaseActivity {

    private ListView lvComments;
    private View headView;
    private TextView  tvRate ;
    private CommentView cvRate;
    private String buscode;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_comment);
    }

    @Override
    public void initViews() {
        lvComments=(ListView)findViewById(R.id.lvComments);
        headView= LayoutInflater.from(this).inflate(R.layout.omment_header,null);
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
}