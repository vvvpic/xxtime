package net.xxtime.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.FileUtils;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.longtu.base.view.ScrollGridView;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.adapter.PhotoRAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.CommonBean;
import net.xxtime.listener.DeleteListener;
import net.xxtime.utils.Contact;
import net.xxtime.utils.ImageUtils;
import net.xxtime.utils.OptionsUtils;
import net.xxtime.utils.ParseFilePath;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.DateTimePickDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 完善用户资料
 */
public class BrowseInfoActivity extends BaseActivity implements AdapterView.OnItemClickListener,DeleteListener{

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

    private LinearLayout llSchool;
    private TextView tvEnrollmentyear , tvSchool, tvDegree;
    private EditText etSchoolType, etMajorname;

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

    private String nickname;
    private String name;
    private String gender;
    private String isstudent;
    private String city;
    private String birthday;
    private String height;
    private String weight;
    private String email;
    private String foreignname;
    private String foreignlevel;
    private String self_appraisalids;
    private String avatar;
    private String degreename;
    private String schoolname;
    private String enrollmentyear;
    private String majorname;
    private String departmentname;
    private String otherforeign;

    @Override
    public void initDatas() {
        departmentname=getIntent().getStringExtra("departmentname");
        majorname=getIntent().getStringExtra("majorname");
        enrollmentyear=getIntent().getStringExtra("enrollmentyear");
        schoolname=getIntent().getStringExtra("schoolname");
        degreename=getIntent().getStringExtra("degreename");
        title=getIntent().getStringExtra("title");
        nickname=getIntent().getStringExtra("nickname");
        name=getIntent().getStringExtra("name");
        gender=getIntent().getStringExtra("gender");
        isstudent=getIntent().getStringExtra("isstudent");
        city=getIntent().getStringExtra("city");
        birthday=getIntent().getStringExtra("birthday");
        height=getIntent().getStringExtra("height");
        weight=getIntent().getStringExtra("weight");
        email=getIntent().getStringExtra("email");
        foreignname=getIntent().getStringExtra("foreignname");
        foreignlevel=getIntent().getStringExtra("foreignlevel");
        self_appraisalids=getIntent().getStringExtra("self_appraisalids");
        avatar=getIntent().getStringExtra("avatar");
        listphotos=getIntent().getStringArrayListExtra("photos");
        otherforeign=getIntent().getStringExtra("otherforeign");

        if (!StringUtils.isEmpty(title)){
            setTitle(title);
            if (title.equals("简历预览")){

            }else {
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText("完成");
            }
        }



        if (!StringUtils.isEmpty(nickname)){
            etNiceName.setText(nickname);
        }

        if (!StringUtils.isEmpty(name)){
            etName.setText(name);
        }

        if (!StringUtils.isEmpty(gender)){
            tvSex.setText(gender+"");
        }



        if (!StringUtils.isEmpty(isstudent)){
            tvStudent.setText(isstudent);

            if (isstudent.equals("是")){
                llSchool.setVisibility(View.VISIBLE);
            }else {
                llSchool.setVisibility(View.GONE);
            }
        }

        if (!StringUtils.isEmpty(majorname)){
            etMajorname.setText(majorname);
        }

        if (!StringUtils.isEmpty(departmentname)){
            etSchoolType.setText(departmentname);
        }

        if (!StringUtils.isEmpty(enrollmentyear)){
            tvEnrollmentyear.setText(enrollmentyear);
        }

        if (!StringUtils.isEmpty(schoolname)){
            tvSchool.setText(schoolname);
        }

        if (!StringUtils.isEmpty(degreename)){
            tvDegree.setText(degreename);
        }



        if (!StringUtils.isEmpty(city)){
            tvCity.setText(city);
        }

        if (!StringUtils.isEmpty(birthday)){
            tvBrith.setText(birthday);
        }

        if (!StringUtils.isEmpty(height)){
            etHeight.setText(height);
        }

        if (!StringUtils.isEmpty(email)){
            etEmail.setText(email);
        }

        if (!StringUtils.isEmpty(weight)){
            etWeight.setText(weight);
        }

        if (!StringUtils.isEmpty(foreignname)){
            tvLanguage.setText(foreignname);
        }else {
            if (!StringUtils.isEmpty(otherforeign)){
                tvLanguage.setText(otherforeign);
            }else {
                tvLanguage.setText("");
            }
        }

        if (!StringUtils.isEmpty(foreignlevel)){
            tvLanguageType.setText(foreignlevel);
        }

        if (!StringUtils.isEmpty(self_appraisalids)){
            etBrift.setText(Contact.getLables(self_appraisalids));
        }

        if (!StringUtils.isEmpty(avatar)){
            if (avatar.indexOf("http://")<0) {
                ImageLoader.getInstance().displayImage("file://" + avatar, ivAvatar, OptionsUtils.getSimpleOptions(80));
            }else {
                ImageLoader.getInstance().displayImage( avatar, ivAvatar, OptionsUtils.getSimpleOptions(80));
            }
        }

        if (listphotos!=null) {
            photoRAdapter = new PhotoRAdapter(listphotos, this, this, 1);
            gvPhotos.setAdapter(photoRAdapter);
        }


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void ondel(int position) {

    }
}
