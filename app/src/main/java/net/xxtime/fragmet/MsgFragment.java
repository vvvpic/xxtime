package net.xxtime.fragmet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.activity.MsgApplyActivity;
import net.xxtime.activity.MsgFollowActivity;
import net.xxtime.activity.MsgSalaryActivity;
import net.xxtime.activity.MsgSystemActivity;
import net.xxtime.activity.MsgWithdrawActivity;
import net.xxtime.base.fragment.BaseFragment;
import net.xxtime.bean.StuMsgNumDivideBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.SharedUtils;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class MsgFragment extends BaseFragment {

    private RelativeLayout  rlFollow ,rlApply ,rlSalary, rlWithdraw, rlSystem;
    private ImageView ivMsgSystem, ivMsgWithdraw, ivMsgSalary, ivMsgFollow, ivMsgApply;

    private Message msg;
    private StuMsgNumDivideBean stuMsgNumDivideBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    stuMsgNumDivideBean= JSONObject.parseObject(msg.obj.toString(),StuMsgNumDivideBean.class);
                    if (stuMsgNumDivideBean!=null&&stuMsgNumDivideBean.getBflag().equals("1")){
                        setMsg();
                    }
                    break;
            }
        }
    };

    private void setMsg(){
        if (stuMsgNumDivideBean.getDefaultAList().get(0).getGzmsgNum()==0){
            ivMsgFollow.setVisibility(View.VISIBLE);
        }else {
            ivMsgFollow.setVisibility(View.GONE);
        }

        if (stuMsgNumDivideBean.getDefaultAList().get(0).getSalarymsgNum()==0){
            ivMsgSalary.setVisibility(View.VISIBLE);
        }else {
            ivMsgSalary.setVisibility(View.GONE);
        }

        if (stuMsgNumDivideBean.getDefaultAList().get(0).getSystemmsgNum()==0){
            ivMsgSystem.setVisibility(View.VISIBLE);
        }else {
            ivMsgSystem.setVisibility(View.GONE);
        }

        if (stuMsgNumDivideBean.getDefaultAList().get(0).getTxmsgNum()==0){
            ivMsgWithdraw.setVisibility(View.VISIBLE);
        }else {
            ivMsgWithdraw.setVisibility(View.GONE);
        }

        if (stuMsgNumDivideBean.getDefaultAList().get(0).getSqmsgNum()==0){
            ivMsgApply.setVisibility(View.VISIBLE);
        }else {
            ivMsgApply.setVisibility(View.GONE);
        }
    }

    @Override
    public void setContentView() {
        layout= R.layout.fgment_msg;
    }

    @Override
    public void initViews() {
        rlFollow =(RelativeLayout)view.findViewById(R.id.rlFollow);
        rlApply  =(RelativeLayout)view.findViewById(R.id.rlApply);
        rlSalary  =(RelativeLayout)view.findViewById(R.id.rlSalary);
        rlWithdraw  =(RelativeLayout)view.findViewById(R.id.rlWithdraw);
        rlSystem =(RelativeLayout)view.findViewById(R.id.rlSystem);
        ivMsgSystem  =(ImageView) view.findViewById(R.id.ivMsgSystem);
        ivMsgWithdraw  =(ImageView)view.findViewById(R.id.ivMsgWithdraw);
        ivMsgSalary  =(ImageView)view.findViewById(R.id.ivMsgSalary);
        ivMsgFollow  =(ImageView)view.findViewById(R.id.ivMsgFollow);
        ivMsgApply =(ImageView)view.findViewById(R.id.ivMsgApply);
    }

/*    *//***
     * 本地保存
     *//*
    public static void setMsgSystem(Context context, int System){
        sharedPreferences=context.getSharedPreferences(Contact.USERINFO,  Context.MODE_APPEND);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("System",System);
        editor.commit();
    }

    public static int get*/

    @Override
    public void initDatas() {
        setTitle("消息列表");
        Refresh();

    }

    public void Refresh(){
        params=new RequestParams();
        params.put("reqCode","getStuMsgNumDivideByType");
        params.put("userid", SharedUtils.getUserId(getActivity()));
        pullpost("studentUser",params,"getStuMsgNumDivideByType");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        rlFollow.setOnClickListener(this);
        rlApply .setOnClickListener(this);
        rlSalary .setOnClickListener(this);
        rlWithdraw .setOnClickListener(this);
        rlSystem .setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlFollow:
                if (stuMsgNumDivideBean.getDefaultAList().get(0).getGzmsgNum()==0){
                    ivMsgFollow.setVisibility(View.GONE);
                    params=new RequestParams();
                    params.put("reqCode","setMsgRead");
                    params.put("userid", SharedUtils.getUserId(getActivity()));
                    params.put("msgtype",1);
                    params.put("isread", 1);
                    pullpost("studentUser",params,"setMsgRead");
                }
                homeActivity.Jump(MsgFollowActivity.class);
                break;
            case R.id.rlSystem:
                if (stuMsgNumDivideBean.getDefaultAList().get(0).getSystemmsgNum()==0){
                    ivMsgSystem.setVisibility(View.GONE);
                    params=new RequestParams();
                    params.put("reqCode","setMsgRead");
                    params.put("userid", SharedUtils.getUserId(getActivity()));
                    params.put("msgtype",5);
                    params.put("isread", 1);
                    pullpost("studentUser",params,"setMsgRead");
                }
                homeActivity.Jump(MsgSystemActivity.class);
                break;
            case R.id.rlWithdraw:
                if (stuMsgNumDivideBean.getDefaultAList().get(0).getTxmsgNum()==0){
                    ivMsgWithdraw.setVisibility(View.GONE);
                    params=new RequestParams();
                    params.put("reqCode","setMsgRead");
                    params.put("userid", SharedUtils.getUserId(getActivity()));
                    params.put("msgtype",4);
                    params.put("isread", 1);
                    pullpost("studentUser",params,"setMsgRead");
                }
                homeActivity.Jump(MsgWithdrawActivity.class);
                break;
            case R.id.rlApply:
                if (stuMsgNumDivideBean.getDefaultAList().get(0).getSqmsgNum()==0){
                    ivMsgApply.setVisibility(View.GONE);
                    params=new RequestParams();
                    params.put("reqCode","setMsgRead");
                    params.put("userid", SharedUtils.getUserId(getActivity()));
                    params.put("msgtype",2);
                    params.put("isread", 1);
                    pullpost("studentUser",params,"setMsgRead");
                }
                homeActivity.Jump(MsgApplyActivity.class);
                break;
            case R.id.rlSalary:
                if (stuMsgNumDivideBean.getDefaultAList().get(0).getSalarymsgNum()==0){
                    ivMsgSalary.setVisibility(View.GONE);
                    params=new RequestParams();
                    params.put("reqCode","setMsgRead");
                    params.put("userid", SharedUtils.getUserId(getActivity()));
                    params.put("msgtype",3);
                    params.put("isread", 1);
                    pullpost("studentUser",params,"setMsgRead");
                }
                homeActivity.Jump(MsgSalaryActivity.class);
                break;
        }
    }

    @Override
    public void Receive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getStuMsgNumDivideByType")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
