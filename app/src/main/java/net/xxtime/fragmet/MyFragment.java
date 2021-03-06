package net.xxtime.fragmet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.activity.AuthenticationActivity;
import net.xxtime.activity.MyFollowActivity;
import net.xxtime.activity.MymoneyActivity;
import net.xxtime.activity.PerfectInfoActivity;
import net.xxtime.activity.PersoninfoActivity;
import net.xxtime.activity.SettingActivity;
import net.xxtime.activity.ShareActivity;
import net.xxtime.activity.WalksActivity;
import net.xxtime.base.fragment.BaseFragment;
import net.xxtime.bean.CheckStudentBean;
import net.xxtime.bean.StudentUserInfoBean;
import net.xxtime.bean.UserViewBean;
import net.xxtime.utils.Contact;
import net.xxtime.utils.OptionsUtils;
import net.xxtime.utils.SharedUtils;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class MyFragment extends BaseFragment {

    private TextView tvMyMsg ,tvMyMon, tvRz, tvDz , tvBs, tvShare, tvFollow, tvSetting;

    private ImageView  ivMsg, ivSet ;
    private TextView tvWork;
    private Message msg;

    private ImageView ivAvatar;
    private TextView tvName;

    private Dialog personaldialog;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Contact.userViewBean= JSONObject.parseObject(msg.obj.toString(),UserViewBean.class);
                    if ( Contact.userViewBean!=null&& Contact.userViewBean.getStatus().equals("1")){
                        setIserInfo();
                    }
                    break;
                case 2:
                    Contact.checkStudentBean=JSONObject.parseObject(msg.obj.toString(), CheckStudentBean.class);
                    break;
            }
        }
    };

    private void setIserInfo(){
        if (!StringUtils.isEmpty( Contact.userViewBean.getUser().getNickname())){
            tvName.setText(Contact.userViewBean.getUser().getNickname());
        }else if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getRealname())){
            tvName.setText(Contact.userViewBean.getUser().getRealname());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getPhoto())){
            ImageLoader.getInstance().displayImage(Contact.userViewBean.getUser().getPhoto(),ivAvatar, OptionsUtils.getSimpleOptions(100));
        }
    }

    @Override
    public void setContentView() {
        layout= R.layout.fgment_my;
    }

    @Override
    public void initViews() {
        tvMyMsg=(TextView)view.findViewById(R.id.tvMyMsg);
        tvMyMon=(TextView)view.findViewById(R.id.tvMyMon);
        tvRz=(TextView)view.findViewById(R.id.tvRz);
        tvDz=(TextView)view.findViewById(R.id.tvDz);
        tvBs=(TextView)view.findViewById(R.id.tvBs);
        tvShare=(TextView)view.findViewById(R.id.tvShare);
        tvFollow=(TextView)view.findViewById(R.id.tvFollow);
        tvSetting=(TextView)view.findViewById(R.id.tvSetting);
        ivAvatar =(ImageView) view.findViewById(R.id.ivAvatar);
        tvName=(TextView)view.findViewById(R.id.tvName);
        ivMsg =(ImageView) view.findViewById(R.id.ivMsg);
        ivSet =(ImageView) view.findViewById(R.id.ivSet);
        tvWork =(TextView) view.findViewById(R.id.tvWork);
    }

    @Override
    public void initDatas() {
        initPerson();
        params=new RequestParams();
        params.put("accessToken",SharedUtils.getToken(getActivity()));
        params.put("id",SharedUtils.getUserId(getActivity()));
        post("user!view",params);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvBs.setOnClickListener(this);
        tvDz.setOnClickListener(this);
        tvFollow.setOnClickListener(this);
        tvMyMon.setOnClickListener(this);
        tvMyMsg.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvRz.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        ivMsg.setOnClickListener(this);
        ivSet.setOnClickListener(this);
        tvWork.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

        params=new RequestParams();
        params.put("reqCode","checkStudentUserInfo");
        params.put("userid", SharedUtils.getUserId(getActivity()));
        Log.e("param==>",params.toString());
        pullpost("studentUser",params,"checkStudentUserInfo");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRz://认证
                if (Contact.checkStudentBean!=null) {
                    if (Contact.checkStudentBean.isSuccess()) {
                        intent=new Intent(getActivity(),AuthenticationActivity.class);
                      /*  if (studentUserInfoBean!=null) {
                            intent.putExtra("isstudent",studentUserInfoBean.getDefaultAList().get(0).getIsstudent());
                            intent.putExtra("cardobverse",studentUserInfoBean.getDefaultAList().get(0).getCardobverse());
                            intent.putExtra("cardreverse",studentUserInfoBean.getDefaultAList().get(0).getCardreverse());
                            intent.putExtra("studentcard",studentUserInfoBean.getDefaultAList().get(0).getStudentcard());
                            intent.putExtra("certification",studentUserInfoBean.getDefaultAList().get(0).getCertification());
                            intent.putExtra("name",studentUserInfoBean.getDefaultAList().get(0).getName());
                            intent.putExtra("cardcode",studentUserInfoBean.getDefaultAList().get(0).getCardcode());
                            intent.putExtra("studentcardcode",studentUserInfoBean.getDefaultAList().get(0).getStudentcardcode());
                        }*/
                        homeActivity.Jump(intent);
                    } else {
                        tvDialogContent.setText("您的信息资料还没完善，不能进行实名认证,请先去完善个人信息后再来查看！");
                        personaldialog.show();
                    }
                }
                break;
            case R.id.tvBs://保送
                if (Contact.checkStudentBean!=null) {
                    if (Contact.checkStudentBean.isSuccess()) {
                        intent=new Intent(getActivity(),WalksActivity.class);
                        homeActivity.Jump(intent);
                    } else {
                        tvDialogContent.setText("您的信息资料还没完善，不能申请保送,请先去完善个人信息后再来查看！");
                        personaldialog.show();
                    }
                }
                break;
            case R.id.tvDz://定制

                break;
            case R.id.tvMyMsg://我的信息

                homeActivity.Jump(PersoninfoActivity.class);
               /* if (Contact.checkStudentBean!=null) {
                    if (Contact.checkStudentBean.isSuccess()) {
                        homeActivity.Jump(PersoninfoActivity.class);
                    } else {
                        tvDialogContent.setText("您的信息资料还没完善，不能查看个人信息,请先去完善个人信息后再来查看！");
                        personaldialog.show();
                    }
                }*/
                break;
            case R.id.tvMyMon://我的钱包
                intent=new Intent(getActivity(),MymoneyActivity.class);
                homeActivity.Jump(intent);
              /*  if (Contact.checkStudentBean!=null) {
                    if (Contact.checkStudentBean.isSuccess()) {
                        intent=new Intent(getActivity(),MymoneyActivity.class);
                        if (Contact.userViewBean!=null){
                            intent.putExtra("balance",studentUserInfoBean.getDefaultAList().get(0).getBalance());
                            intent.putExtra("earnestmoney",studentUserInfoBean.getDefaultAList().get(0).getEarnestmoney());
                        }
                        homeActivity.Jump(intent);
                    } else {
                        tvDialogContent.setText("您的信息资料还没完善，不能进入我的钱包,请先去完善个人信息后再来查看！");
                        personaldialog.show();
                    }
                }*/
                break;
            case R.id.tvShare://分享
                homeActivity.Jump(ShareActivity.class);
                break;
            case R.id.tvFollow://关注
                        intent=new Intent(getActivity(),MyFollowActivity.class);
                        homeActivity.Jump(intent);
                break;
            case R.id.ivSet://设置
                homeActivity.Jump(SettingActivity.class);
                break;
            case R.id.btnCancel:
                personaldialog.dismiss();
                break;
            case R.id.btnOk:
                homeActivity.Jump(PerfectInfoActivity.class);
                personaldialog.dismiss();
                break;
        }
    }

    @Override
    public void Receive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("user!view")){
            msg.what=1;
        }else if (requestname.equals("checkStudentUserInfo")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    private Button  btnOk, btnCancel;
    private TextView tvDialogContent;
    private void initPerson() {
        personaldialog = new Dialog(getActivity(), R.style.loadingDialog);
        LinearLayout layout = new LinearLayout(getActivity());

        layout.setBackgroundColor(getActivity().getResources().getColor(
                R.color.transparent));
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.personal_dialog, null);
        layout.addView(view);
        personaldialog.setContentView(layout);
        personaldialog.setCanceledOnTouchOutside(false);
        personaldialog.setCancelable(false);
        btnOk =(Button)view.findViewById(R.id.btnOk);
        btnCancel=(Button)view.findViewById(R.id.btnCancel);
        tvDialogContent=(TextView)view.findViewById(R.id.tvDialogContent);
    }
}
