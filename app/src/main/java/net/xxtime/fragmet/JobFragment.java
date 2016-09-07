package net.xxtime.fragmet;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.activity.AssessedActivity;
import net.xxtime.activity.NoemployActivity;
import net.xxtime.activity.StoodActivity;
import net.xxtime.activity.ToSignActivity;
import net.xxtime.activity.ToaccountdActivity;
import net.xxtime.activity.ToassessActivity;
import net.xxtime.activity.ToemployedActivity;
import net.xxtime.base.fragment.BaseFragment;
import net.xxtime.bean.RegisterJobNumBean;
import net.xxtime.utils.SharedUtils;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class JobFragment extends BaseFragment {

    private RelativeLayout rlDly ,rlDqd ,rlDjs, rlDpj, rlWly, rlSy ,rlYpj;
    private TextView tvYpj, tvSy ,tvWpj ,tvDpj, tvDjs ,tvDqd ,tvDly;

    private RegisterJobNumBean jobNumBean;

    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    jobNumBean= JSONObject.parseObject(msg.obj.toString(),RegisterJobNumBean.class);
                    if (jobNumBean!=null&&jobNumBean.getBflag().equals("1")){
                        setNum();
                    }
                    break;
            }
        }
    };

    private void setNum(){
        if (jobNumBean.getDefaultAList().get(0).getDjsNum()==0){
            tvDjs.setVisibility(View.GONE);
        }else {
            tvDjs.setVisibility(View.VISIBLE);
            tvDjs.setText(jobNumBean.getDefaultAList().get(0).getDjsNum()+"");
        }

        if (jobNumBean.getDefaultAList().get(0).getDqdNum()==0){
            tvDqd.setVisibility(View.GONE);
        }else {
            tvDqd.setVisibility(View.VISIBLE);
            tvDqd.setText(jobNumBean.getDefaultAList().get(0).getDqdNum()+"");
        }

        if (jobNumBean.getDefaultAList().get(0).getSyNum()==0){
            tvSy.setVisibility(View.GONE);
        }else {
            tvSy.setVisibility(View.VISIBLE);
            tvSy.setText(jobNumBean.getDefaultAList().get(0).getSyNum()+"");
        }

        if (jobNumBean.getDefaultAList().get(0).getWlyNum()==0){
            tvWpj.setVisibility(View.GONE);
        }else {
            tvWpj.setVisibility(View.VISIBLE);
            tvWpj.setText(jobNumBean.getDefaultAList().get(0).getWlyNum()+"");
        }

        if (jobNumBean.getDefaultAList().get(0).getYpjNum()==0){
            tvYpj.setVisibility(View.GONE);
        }else {
            tvYpj.setVisibility(View.VISIBLE);
            tvYpj.setText(jobNumBean.getDefaultAList().get(0).getYpjNum()+"");
        }

        if (jobNumBean.getDefaultAList().get(0).getDlyNum()==0){
            tvDly.setVisibility(View.GONE);
        }else {
            tvDly.setVisibility(View.VISIBLE);
            tvDly.setText(jobNumBean.getDefaultAList().get(0).getDlyNum()+"");
        }

        if (jobNumBean.getDefaultAList().get(0).getDpjNum()==0){
            tvDpj.setVisibility(View.GONE);
        }else {
            tvDpj.setVisibility(View.VISIBLE);
            tvDpj.setText(jobNumBean.getDefaultAList().get(0).getDpjNum()+"");
        }
    }

    @Override
    public void setContentView() {
        layout= R.layout.fgment_job;
    }

    @Override
    public void initViews() {
        rlDly =(RelativeLayout)view.findViewById(R.id.rlDly);
        rlDqd =(RelativeLayout)view.findViewById(R.id.rlDqd);
        rlDjs =(RelativeLayout)view.findViewById(R.id.rlDjs);
        rlDpj =(RelativeLayout)view.findViewById(R.id.rlDpj);
        rlWly =(RelativeLayout)view.findViewById(R.id.rlWly);
        rlSy =(RelativeLayout)view.findViewById(R.id.rlSy);
        rlYpj=(RelativeLayout)view.findViewById(R.id.rlYpj);

        tvYpj =(TextView) view.findViewById(R.id.tvYpj);
        tvSy =(TextView) view.findViewById(R.id.tvSy);
        tvWpj =(TextView) view.findViewById(R.id.tvWpj);
        tvDpj =(TextView) view.findViewById(R.id.tvDpj);
        tvDjs=(TextView) view.findViewById(R.id.tvDjs);
        tvDqd =(TextView) view.findViewById(R.id.tvDqd);
        tvDly=(TextView) view.findViewById(R.id.tvDly);
    }

    @Override
    public void initDatas() {
        Refresh();
    }

    public void Refresh(){

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        rlDjs.setOnClickListener(this);
        rlDly.setOnClickListener(this);
        rlDpj.setOnClickListener(this);
        rlDqd.setOnClickListener(this);
        rlSy.setOnClickListener(this);
        rlWly.setOnClickListener(this);
        rlYpj.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        params=new RequestParams();
        params.put("reqCode","getRegisterJobNum");
        params.put("userid", SharedUtils.getUserId(getActivity()));
        pullpost("userJob",params,"getRegisterJobNum");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlDly:
                homeActivity.Jump(ToemployedActivity.class);
                break;
            case R.id.rlDjs:
                homeActivity.Jump(ToaccountdActivity.class);
                break;
            case R.id.rlWly:
                homeActivity.Jump(NoemployActivity.class);
                break;
            case R.id.rlYpj:
                homeActivity.Jump(AssessedActivity.class);
                break;
            case R.id.rlDqd:
                homeActivity.Jump(ToSignActivity.class);
                break;
            case R.id.rlDpj:
                homeActivity.Jump(ToassessActivity.class);
                break;
            case R.id.rlSy:
                homeActivity.Jump(StoodActivity.class);
                break;
        }
    }

    @Override
    public void Receive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getRegisterJobNum")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
