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
import net.xxtime.bean.MsgStatusBean;
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
    private MsgStatusBean msgStatusBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    stuMsgNumDivideBean= JSONObject.parseObject(msg.obj.toString(),StuMsgNumDivideBean.class);
                    if (stuMsgNumDivideBean!=null&&stuMsgNumDivideBean.getBflag().equals("1")){

                    }
                    break;
                case 2:
                    msgStatusBean=JSONObject.parseObject(msg.obj.toString(),MsgStatusBean.class);
                    if (msgStatusBean!=null&&msgStatusBean.getBflag().equals("1")){
                        setMsg();
                    }
                    break;
            }
        }
    };

    private void setMsg(){

        if (msgStatusBean.getDefaultAList()!=null&&msgStatusBean.getDefaultAList().size()>0){
            for (int i=0;i<msgStatusBean.getDefaultAList().size();i++){
               switch (msgStatusBean.getDefaultAList().get(i).getMsgtype()){
                   case 1:

                       if (msgStatusBean.getDefaultAList().get(i).getUnread()==1){
                           ivMsgFollow.setVisibility(View.VISIBLE);
                       }else {
                           ivMsgFollow.setVisibility(View.GONE);
                       }

                       break;
                   case 2:
                       if (msgStatusBean.getDefaultAList().get(i).getUnread()==1){
                           ivMsgApply.setVisibility(View.VISIBLE);
                       }else {
                           ivMsgApply.setVisibility(View.GONE);
                       }
                       break;
                   case 3:

                       if (msgStatusBean.getDefaultAList().get(i).getUnread()==1){
                           ivMsgSalary.setVisibility(View.VISIBLE);
                       }else {
                           ivMsgSalary.setVisibility(View.GONE);
                       }

                       break;
                   case 4:
                       if (msgStatusBean.getDefaultAList().get(i).getUnread()==1){
                           ivMsgWithdraw.setVisibility(View.VISIBLE);
                       }else {
                           ivMsgWithdraw.setVisibility(View.GONE);
                       }

                       break;
                   case 5:
                       if (msgStatusBean.getDefaultAList().get(i).getUnread()==1){
                           ivMsgSystem.setVisibility(View.VISIBLE);
                       }else {
                           ivMsgSystem.setVisibility(View.GONE);
                       }
                       break;

               }
            }
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
        params.put("reqCode","getAllMsgStatus");
        params.put("userid", SharedUtils.getUserId(getActivity()));
        pullpost("studentUser",params,"getAllMsgStatus");
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
                    ivMsgFollow.setVisibility(View.GONE);
                homeActivity.Jump(MsgFollowActivity.class);
                break;
            case R.id.rlSystem:
                    ivMsgSystem.setVisibility(View.GONE);

                homeActivity.Jump(MsgSystemActivity.class);
                break;
            case R.id.rlWithdraw:
                    ivMsgWithdraw.setVisibility(View.GONE);
                homeActivity.Jump(MsgWithdrawActivity.class);
                break;
            case R.id.rlApply:
                    ivMsgApply.setVisibility(View.GONE);
                homeActivity.Jump(MsgApplyActivity.class);
                break;
            case R.id.rlSalary:
                    ivMsgSalary.setVisibility(View.GONE);
                 /*   params=new RequestParams();
                    params.put("reqCode","setMsgRead");
                    params.put("userid", SharedUtils.getUserId(getActivity()));
                    params.put("msgtype",3);
                    params.put("isread", 1);
                    pullpost("studentUser",params,"setMsgRead");*/
                homeActivity.Jump(MsgSalaryActivity.class);
                break;
        }
    }

    @Override
    public void Receive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getStuMsgNumDivideByType")){
            msg.what=1;
        }else if (requestname.equals("getAllMsgStatus")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
