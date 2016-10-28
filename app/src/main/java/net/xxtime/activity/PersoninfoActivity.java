package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.longtu.base.view.ScrollGridView;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.adapter.PhotoRAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CheckStudentBean;
import net.xxtime.bean.StudentUserInfoBean;
import net.xxtime.bean.StudentUserPictureBean;
import net.xxtime.bean.StudentViewByUserIdBean;
import net.xxtime.bean.UserPictureBean;
import net.xxtime.bean.UserViewBean;
import net.xxtime.listener.DeleteListener;
import net.xxtime.utils.Contact;
import net.xxtime.utils.OptionsUtils;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.DateTimePickDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户资料信息
 */
public class PersoninfoActivity extends BaseActivity implements AdapterView.OnItemClickListener,DeleteListener{

    private PhotoRAdapter photoRAdapter;
    private List<String> listphotos;
    private ScrollGridView gvPhotos;

    private PopupWindow choosePhotoWindow;

    private RelativeLayout  rlAvatar;
    private ImageView ivAvatar;
    private EditText etNiceName, etName,etHeight, etWeight, etEmail;
    private TextView tvBrith ;
    private DateTimePickDialog dateTimePickDialog;
    private TextView tvCity , tvLanguage;
    private EditText etBrift;
    private int choosephoto=0;//0头像 1照片
    private TextView tvRight;
    private TextView tvSex, tvStudent, tvLanguageType;
    private String title;
    private Message msg;

    private LinearLayout llSchool;
    private TextView tvEnrollmentyear , tvSchool, tvDegree;
    private EditText etSchoolType, etMajorname;

    private StudentViewByUserIdBean studentViewByUserIdBean;
    private UserPictureBean userPictureBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    studentViewByUserIdBean=JSONObject.parseObject(msg.obj.toString(),StudentViewByUserIdBean.class);
                    if (studentViewByUserIdBean!=null&&studentViewByUserIdBean.getStatus().equals("1")){
                        setSchool();
                    }
                    break;
                case 2:
                    userPictureBean=JSONObject.parseObject(msg.obj.toString(),UserPictureBean.class);
                    if (userPictureBean!=null&&userPictureBean.getStatus().equals("1")){
                        setPhotoRAdapter();
                    }
                    break;
                case 3:
                    Contact.userViewBean= JSONObject.parseObject(msg.obj.toString(),UserViewBean.class);
                    if ( Contact.userViewBean!=null&& Contact.userViewBean.getStatus().equals("1")){
                        setIserInfo();
                    }
                    break;
            }
        }
    };

    private void setSchool(){
    if (!StringUtils.isEmpty(studentViewByUserIdBean.getStudent().getCollege().getName())){
            tvSchool.setText(studentViewByUserIdBean.getStudent().getCollege().getName());
        }

        if (!StringUtils.isEmpty(studentViewByUserIdBean.getStudent().getFaculty())){
            etSchoolType.setText(studentViewByUserIdBean.getStudent().getFaculty());
        }

        if (!StringUtils.isEmpty(studentViewByUserIdBean.getStudent().getYear())){
            tvEnrollmentyear.setText(studentViewByUserIdBean.getStudent().getYear());
        }

        if (!StringUtils.isEmpty(studentViewByUserIdBean.getStudent().getDegree().getName())){
            tvDegree .setText(studentViewByUserIdBean.getStudent().getDegree().getName());
        }

        if (!StringUtils.isEmpty(studentViewByUserIdBean.getStudent().getDiscipline())){
            etMajorname.setText(studentViewByUserIdBean.getStudent().getDiscipline());
        }
    }

    private void setPhotoRAdapter(){
        listphotos=new ArrayList<>();
        if (userPictureBean.getUserPictures()!=null){
            for (int i=0;i<userPictureBean.getUserPictures().size();i++){
                listphotos.add(userPictureBean.getUserPictures().get(i).getUrl());
            }
        }
        photoRAdapter=new PhotoRAdapter(listphotos,this,this,1);
        gvPhotos.setAdapter(photoRAdapter);
    }

    private void setIserInfo(){
        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getNickname())){
            etNiceName.setText(Contact.userViewBean.getUser().getNickname());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getUsername())){
            etName.setText(Contact.userViewBean.getUser().getUsername());
        }

        if (Contact.userViewBean.getUser().getMale()==1) {
            tvSex.setText("男");
        }else {
            tvSex.setText("女");
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getBirthday())){
            tvBrith.setText(Contact.userViewBean.getUser().getBirthday());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getHight())){
            etHeight.setText(Contact.userViewBean.getUser().getHight());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getWeight())){
            etWeight.setText(Contact.userViewBean.getUser().getWeight());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getEmail())){
            etEmail.setText(Contact.userViewBean.getUser().getEmail());
        }

        if (Contact.userViewBean.getUser().getIsStudent()==1) {
            tvStudent.setText("是");
            llSchool.setVisibility(View.VISIBLE);
        }else {
            tvStudent.setText("否");
            llSchool.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getProvince().getName())
                ||!StringUtils.isEmpty(Contact.userViewBean.getUser().getDistrict().getName())||
                !StringUtils.isEmpty(Contact.userViewBean.getUser().getCity().getName())){
            tvCity.setText(Contact.userViewBean.getUser().getProvince().getName()+Contact.userViewBean.getUser().getCity().getName()
                    +Contact.userViewBean.getUser().getDistrict().getName());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getForeign().getName())){
            tvLanguage.setText(Contact.userViewBean.getUser().getForeign().getName());
        }else {
            tvLanguage.setText("");
        }

        if (Contact.userViewBean.getUser().getForeignLevel()>0){
            if (Contact.userViewBean.getUser().getForeignLevel()==1) {
                tvLanguageType.setText("日常问候");
            }else if (Contact.userViewBean.getUser().getForeignLevel()==2) {
                tvLanguageType.setText("工作交流");
            }else if (Contact.userViewBean.getUser().getForeignLevel()==3) {
                tvLanguageType.setText("学术交流");
            }
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getIntro())){
            etBrift.setText(Contact.userViewBean.getUser().getIntro());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getPhoto())){
            ImageLoader.getInstance().displayImage(Contact.userViewBean.getUser().getPhoto(),ivAvatar,OptionsUtils.getSimpleOptions(80));
        }
    }


    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("student!viewByUserId")){
            msg.what=1;
        }else if (requestname.equals("user-picture!list")){
            msg.what=2;
        }else  if (requestname.equals("user!view")){
            msg.what=3;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_browse_info);
    }

    @Override
    public void initViews() {
        gvPhotos=(ScrollGridView)findViewById(R.id.gvPhotos);
        rlAvatar=(RelativeLayout) findViewById(R.id.rlAvatar);
        ivAvatar  =(ImageView)findViewById(R.id.ivAvatar);
        etNiceName =(EditText) findViewById(R.id.etNiceName);
        etName   =(EditText)findViewById(R.id.etName);
        etHeight =(EditText)findViewById(R.id.etHeight);
        etWeight =(EditText)findViewById(R.id.etWeight);
        etEmail =(EditText)findViewById(R.id.etEmail);
        tvBrith  =(TextView) findViewById(R.id.tvBrith);
        tvCity  =(TextView) findViewById(R.id.tvCity);
        tvLanguage  =(TextView) findViewById(R.id.tvLanguage);
        etBrift=(EditText) findViewById(R.id.etBrift);

        tvRight=(TextView)findViewById(R.id.tvRight);

        tvSex =(TextView)findViewById(R.id.tvSex);
        tvStudent =(TextView)findViewById(R.id.tvStudent);
        tvLanguageType=(TextView)findViewById(R.id.tvLanguageType);

        llSchool =(LinearLayout) findViewById(R.id.llSchool);
        tvEnrollmentyear  =(TextView) findViewById(R.id.tvEnrollmentyear);
        tvSchool =(TextView) findViewById(R.id.tvSchool);
        tvDegree =(TextView) findViewById(R.id.tvDegree);
        etSchoolType =(EditText) findViewById(R.id.etSchoolType);
        etMajorname=(EditText) findViewById(R.id.etMajorname);

    }

    @Override
    public void initDatas() {

        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("编辑");

    }

    private boolean start=false;

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvRight.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        params=new RequestParams();
        params.put("accessToken",SharedUtils.getToken(this));
        pullpost("student!viewByUserId",params);
        params.put("query.userId",SharedUtils.getUserId(this));
        pullpost("user-picture!list",params);
        if (Contact.userViewBean!=null) {
            setIserInfo();
        }
        params=new RequestParams();
        params.put("accessToken",SharedUtils.getToken(this));
        params.put("id",SharedUtils.getUserId(this));
        pullpost("user!view",params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRight:
                intent=new Intent(this,PerfectInfoActivity.class);
                intent.putExtra("studentschool",studentViewByUserIdBean);
                intent.putExtra("photos",userPictureBean );
                Jump(intent);

                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void ondel(int position) {

    }
}
