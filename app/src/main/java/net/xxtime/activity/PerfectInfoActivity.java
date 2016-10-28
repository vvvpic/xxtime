package net.xxtime.activity;

import android.app.Activity;
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
import net.xxtime.bean.StudentUserInfoBean;
import net.xxtime.bean.StudentUserPictureBean;
import net.xxtime.bean.StudentViewByUserIdBean;
import net.xxtime.bean.UserPictureBean;
import net.xxtime.bean.XxtimeBean;
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
public class PerfectInfoActivity extends BaseActivity implements AdapterView.OnItemClickListener,DeleteListener {

    private PhotoRAdapter photoRAdapter;
    private List<String> listphotos;
    private ScrollGridView gvPhotos;

    private PopupWindow choosePhotoWindow;

    private StudentViewByUserIdBean studentschool;
    private UserPictureBean userPictureBean;

    private RelativeLayout  rlAvatar;
    private ImageView ivAvatar;
    private EditText etNiceName, etName,etHeight, etWeight, etEmail;
    private TextView tvBrith ;
    private RadioButton rbtn_male, rbtn_female;
    private DateTimePickDialog dateTimePickDialog;

    private RadioButton  rbtnStudentYes, rbtnStudentNo;
    private TextView tvCity , tvLanguage;
    private RadioButton rbtnLanC, rbtnLanWork, rbtnLanS;
    private EditText etBrift;

    private int choosephoto=0;//0头像 1照片
    private String avatar="";

    private TextView tvRight;
    private Button btnBrowse;

    private Message msg;
    private CommonBean commonBean;
    private int uploadint=0,up=0;

    private LinearLayout llSchool;
    private TextView tvEnrollmentyear , tvSchool, tvDegree;
    private EditText etSchoolType, etMajorname;

    private XxtimeBean xxtimeBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    xxtimeBean= JSONObject.parseObject(msg.obj.toString(),XxtimeBean.class);
                    if (xxtimeBean!=null){
//                        ToastUtils.show(PerfectInfoActivity.this,commonBean.getMsg());
                        if (xxtimeBean.getStatus().equals("1")){
                            if (!StringUtils.isEmpty(avatar)){
                                if (avatar.indexOf("http://")<0){
                                    params=new RequestParams();
                                    params.put("accessToken",SharedUtils.getToken(PerfectInfoActivity.this));
                                    File myFile = new File(avatar);
                                    try {
                                        params.put("file", myFile);
                                    } catch(FileNotFoundException e) {}
                                    uploadint++;
                                    pullpost("user!photo", params);
                                }
                            }

                            if (listphotos.size()>0){
                                for (int i=0;i<listphotos.size();i++){
                                    if (listphotos.get(i).indexOf("http://")<0){
                                        params=new RequestParams();
                                        params.put("accessToken",SharedUtils.getToken(PerfectInfoActivity.this));
                                        File myFile = new File(listphotos.get(i));
                                        try {
                                            params.put("files", myFile);
                                        } catch(FileNotFoundException e) {}
                                        uploadint++;
                                        pullpost("user-picture!save", params);
                                    }
                                }
                            }

                            if (uploadint==0){
                                disMiss();
                                ToastUtils.show(PerfectInfoActivity.this,"修改完善个人信息成功！");
                                finish();
                            }
//                            ToastUtils.show(PerfectInfoActivity.this,"正在保存图片");
                        }
                    }
                    break;
                case 2:
                    up++;
                    if (up==uploadint){
                        disMiss();
                        ToastUtils.show(PerfectInfoActivity.this,"修改完善个人信息成功！");
                        finish();
                    }
                    break;
                case 3:
                    xxtimeBean=JSONObject.parseObject(msg.obj.toString(),XxtimeBean.class);
                    if (xxtimeBean!=null){
                        ToastUtils.show(PerfectInfoActivity.this,xxtimeBean.getMsg());
                        if (xxtimeBean.getStatus().equals("1")){
                            listphotos.remove(delpos);
                            photoRAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_perfect_info);
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
        rbtn_male =(RadioButton) findViewById(R.id.rbtn_male);
        rbtn_female=(RadioButton) findViewById(R.id.rbtn_female);

        rbtnStudentYes=(RadioButton) findViewById(R.id.rbtnStudentYes);
        rbtnStudentNo =(RadioButton) findViewById(R.id.rbtnStudentNo);
        tvCity  =(TextView) findViewById(R.id.tvCity);
        tvLanguage  =(TextView) findViewById(R.id.tvLanguage);
        rbtnLanC =(RadioButton) findViewById(R.id.rbtnLanC);
        rbtnLanWork =(RadioButton) findViewById(R.id.rbtnLanWork);
        rbtnLanS =(RadioButton) findViewById(R.id.rbtnLanS);
        etBrift=(EditText) findViewById(R.id.etBrift);

        llSchool =(LinearLayout) findViewById(R.id.llSchool);
        tvEnrollmentyear  =(TextView) findViewById(R.id.tvEnrollmentyear);
        tvSchool =(TextView) findViewById(R.id.tvSchool);
        tvDegree =(TextView) findViewById(R.id.tvDegree);
        etSchoolType =(EditText) findViewById(R.id.etSchoolType);
        etMajorname=(EditText) findViewById(R.id.etMajorname);

        tvRight=(TextView)findViewById(R.id.tvRight);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        btnBrowse=(Button) findViewById(R.id.btnBrowse);

    }

    @Override
    public void initDatas() {
        studentschool= (StudentViewByUserIdBean) getIntent().getSerializableExtra("studentschool");
        userPictureBean= (UserPictureBean) getIntent().getSerializableExtra("photos");
        initChooseWindow();
        listphotos=new ArrayList<>();

        setTitle("完善个人信息");
        if (Contact.userViewBean!=null) {
            setIserInfo();
        }

        if (studentschool!=null){
            setStudentschool();
        }

        if (userPictureBean!=null&&userPictureBean.getUserPictures()!=null){
            setPhotoRAdapter();
        }

        photoRAdapter=new PhotoRAdapter(listphotos,this,this,0);
        gvPhotos.setAdapter(photoRAdapter);

    }

    private void setStudentschool(){
        if (!StringUtils.isEmpty(studentschool.getStudent().getCollege().getName())){
            tvSchool.setText(studentschool.getStudent().getCollege().getName());
            schoolname=studentschool.getStudent().getCollege().getName();
            schoolCode=studentschool.getStudent().getCollege().getId();
        }

        if (!StringUtils.isEmpty(studentschool.getStudent().getFaculty())){
            etSchoolType.setText(studentschool.getStudent().getFaculty());
        }

        if (!StringUtils.isEmpty(studentschool.getStudent().getYear())){
            tvEnrollmentyear.setText(studentschool.getStudent().getYear());
        }

        if (!StringUtils.isEmpty(studentschool.getStudent().getDegree().getName())){
            tvDegree.setText(studentschool.getStudent().getDegree().getName());
            degreename=studentschool.getStudent().getDegree().getName();
        }

        if (!StringUtils.isEmpty(studentschool.getStudent().getDiscipline())){
            etMajorname.setText(studentschool.getStudent().getDiscipline());
        }
    }

    private void setPhotoRAdapter(){
        for (int i=0;i<userPictureBean.getUserPictures().size();i++){
            listphotos.add(userPictureBean.getUserPictures().get(i).getUrl());
        }

    }

    private void setIserInfo(){
        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getNickname())){
            etNiceName.setText(Contact.userViewBean.getUser().getNickname());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getUsername())){
            etName.setText(Contact.userViewBean.getUser().getUsername());
        }

        if (Contact.userViewBean.getUser().getMale()==1){
               rbtn_male.setChecked(true);
               llSchool.setVisibility(View.VISIBLE);
            }else {
                rbtn_female.setChecked(false);
                llSchool.setVisibility(View.GONE);
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

        if (Contact.userViewBean.getUser().getIsStudent()==1){
                rbtnStudentYes.setChecked(true);
                llSchool.setVisibility(View.VISIBLE);
            }else {
                rbtnStudentNo.setChecked(true);
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
             foreignname=Contact.userViewBean.getUser().getForeign().getName();
        }else {
            tvLanguage.setText("");
        }


        if (Contact.userViewBean.getUser().getForeignLevel()>0){
            if (Contact.userViewBean.getUser().getForeignLevel()==1) {
                rbtnLanC.setChecked(true);
            }else if (Contact.userViewBean.getUser().getForeignLevel()==2) {
                rbtnLanWork.setChecked(true);
            }else if (Contact.userViewBean.getUser().getForeignLevel()==3) {
                rbtnLanS.setChecked(true);
            }
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getIntro())){
            etBrift.setText(Contact.userViewBean.getUser().getIntro());
        }

        if (!StringUtils.isEmpty(Contact.userViewBean.getUser().getPhoto())){
            avatar=Contact.userViewBean.getUser().getPhoto();
            ImageLoader.getInstance().displayImage(Contact.userViewBean.getUser().getPhoto(),ivAvatar,OptionsUtils.getSimpleOptions(80));
        }

        provinecode=Contact.userViewBean.getUser().getProvince().getId();
        citycode=Contact.userViewBean.getUser().getCity().getId();
        areacode=Contact.userViewBean.getUser().getDistrict().getId();
        foreignid=Contact.userViewBean.getUser().getForeign().getId();
    }


    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        rlAvatar.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnCam .setOnClickListener(this);
        btnCh.setOnClickListener(this);
        gvPhotos.setOnItemClickListener(this);
        tvRight.setOnClickListener(this);
        tvBrith.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        tvLanguage.setOnClickListener(this);
        btnBrowse.setOnClickListener(this);
//        etBrift.setOnClickListener(this);
        rbtnStudentNo.setOnClickListener(this);
        rbtnStudentYes.setOnClickListener(this);

        tvSchool.setOnClickListener(this);
        tvDegree.setOnClickListener(this);
        tvEnrollmentyear.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    private int gender=1;
    private int isstudent=-1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvEnrollmentyear:
                dateTimePickDialog = new DateTimePickDialog(this, "2016-9-1");
                dateTimePickDialog.setTimePicker(View.GONE);
                dateTimePickDialog.dateTimePicKDialog(tvEnrollmentyear,1);
                break;
            case R.id.tvDegree:
                intent=new Intent(this,SelectDegreeActivity.class);
                Jump(intent,DEGREE);
                break;
            case R.id.tvSchool:
                intent=new Intent(this,SelectSchoolActivity.class);
                Jump(intent,SCHOOL);
                break;
            case R.id.rlAvatar:
                choosephoto=0;
                choosePhotoWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.btnCancel:
                if (choosePhotoWindow!=null)
                    choosePhotoWindow.dismiss();
                break;
            case R.id.btnCam:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_C_IMAGE);
                if (choosePhotoWindow!=null)
                    choosePhotoWindow.dismiss();

                break;
            case R.id.btnCh:
                intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             /*   intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image*//*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);*/
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                if (choosePhotoWindow!=null)
                    choosePhotoWindow.dismiss();
                break;
            case R.id.tvRight:

                if (StringUtils.isEmpty(etNiceName.getText().toString())){
                    ToastUtils.show(this,"请输入用户名");
                    return;
                }

                if (StringUtils.isEmpty(etName.getText().toString())){
                    ToastUtils.show(this,"请输入姓名");
                    return;
                }

                if (rbtn_female.isChecked()){
                    gender=2;
                }else if(rbtn_male.isChecked()){
                    gender=1;
                }

                if (rbtnStudentYes.isChecked()){
                    isstudent=1;
                }else if (rbtnStudentNo.isChecked()){
                    isstudent=0;
                }

                if (isstudent==-1){
                    ToastUtils.show(this,"请选择是否是学生");
                    return;
                }

                if (StringUtils.isEmpty(tvCity.getText().toString())){
                    ToastUtils.show(this,"请选择所在城市");
                    return;
                }

                if (isstudent==1){
                    if (StringUtils.isEmpty(tvSchool.getText().toString())){
                        ToastUtils.show(this,"请选择所在学校");
                        return;
                    }
                }

                params=new RequestParams();
                params.put("accessToken",SharedUtils.getToken(this));
                params.put("id",SharedUtils.getUserId(this));
                params.put("user.nickname",etNiceName.getText().toString());
                params.put("user.realname",etName.getText().toString());
                params.put("user.male",gender);
                params.put("user.isStudent",isstudent);
                params.put("user.province.id",provinecode);
                params.put("user.city.id",citycode);
                params.put("user.district.id",areacode);
                if (!StringUtils.isEmpty(degreeid)){
                    params.put("student.degree.id",degreeid);
                }
                if (!StringUtils.isEmpty(tvSchool.getText().toString())){
                    params.put("student.college.id",schoolCode);
                }
                if (!StringUtils.isEmpty(etMajorname.getText().toString())){
                    params.put("student.discipline",etMajorname.getText().toString());
                }

                if (!StringUtils.isEmpty(etSchoolType.getText().toString())){
                    params.put("student.faculty",etSchoolType.getText().toString());
                }

                if (!StringUtils.isEmpty(tvEnrollmentyear.getText().toString())){
                    params.put("student.year",tvEnrollmentyear.getText().toString().substring(0,4));
                }

                if (!StringUtils.isEmpty(tvBrith.getText().toString())){
                    params.put("user.birthday",tvBrith.getText().toString());
                }

                if (!StringUtils.isEmpty(etHeight.getText().toString())){
                    params.put("user.hight",etHeight.getText().toString());
                }

                if (!StringUtils.isEmpty(etWeight.getText().toString())){
                    params.put("user.weight",etWeight.getText().toString());
                }

                if (!StringUtils.isEmpty(etEmail.getText().toString())){
                    params.put("user.email",etEmail.getText().toString());
                }

                if (!StringUtils.isEmpty(tvLanguage.getText().toString())){
                    params.put("user.foreign.id",foreignid);
                }

                if (rbtnLanC.isChecked()){
                    params.put("user.foreignLevel",1);
                }else if (rbtnLanWork.isChecked()){
                    params.put("user.foreignLevel",2);
                }else if (rbtnLanS.isChecked()){
                    params.put("user.foreignLevel",3);
                }

                if (!StringUtils.isEmpty(etBrift.getText().toString())){
                    params.put("user.intro",etBrift.getText().toString());
                }

                Log.e("param==>",params.toString());

                post("user!update",params);

                break;
            case R.id.tvBrith:
                dateTimePickDialog = new DateTimePickDialog(this, "1990-1-1");
                dateTimePickDialog.setTimePicker(View.GONE);
                dateTimePickDialog.dateTimePicKDialog(tvBrith,1);
                break;
            case R.id.tvCity:
                intent=new Intent(this,SelectAreaActivity.class);
                Jump(intent,AREA);
                break;
            case R.id.tvLanguage:
                intent=new Intent(this,SelectLanguageActivity.class);
                Jump(intent,Languge);
                break;
            case R.id.btnBrowse:

                intent=new Intent(this,BrowseInfoActivity.class);
                intent.putExtra("nickname",etNiceName.getText().toString());
                intent.putExtra("name",etName.getText().toString());
                if (rbtn_female.isChecked()){
                    intent.putExtra("gender","女");
                }else{
                    intent.putExtra("gender","男");
                }

                if (rbtnStudentYes.isChecked()){
                    intent.putExtra("isstudent","是");
                }else if (rbtnStudentNo.isChecked()){
                    intent.putExtra("isstudent","否");
                }

                intent.putExtra("city",tvCity.getText().toString());
                intent.putExtra("birthday",tvBrith.getText().toString());
                intent.putExtra("height",etHeight.getText().toString());

                intent.putExtra("weight",etWeight.getText().toString());

                intent.putExtra("email",etEmail.getText().toString());

                intent.putExtra("foreignname",foreignname);
                intent.putExtra("otherforeign",otherforeign);

                intent.putExtra("degreename",degreename);

                intent.putExtra("schoolname",schoolname);

                intent.putExtra("enrollmentyear",tvEnrollmentyear.getText().toString());

                intent.putExtra("majorname",etMajorname.getText().toString());

                intent.putExtra("departmentname",etSchoolType.getText().toString());

                if (rbtnLanC.isChecked()){
                    intent.putExtra("foreignlevel","日常交流");
                }else if (rbtnLanWork.isChecked()){
                    intent.putExtra("foreignlevel","工作交流");
                }else if (rbtnLanS.isChecked()){
                    intent.putExtra("foreignlevel","学术交流");
                }

                intent.putExtra("self_appraisalids",etBrift.getText().toString());

                intent.putStringArrayListExtra("photos", (ArrayList<String>) listphotos);

                intent.putExtra("title","简历预览");

                intent.putExtra("avatar",avatar);

                Jump(intent);

                break;
           /* case R.id.etBrift:
                intent=new Intent(this,LablesActivity.class);
                Jump(intent,LABLE);
                break;*/
            case R.id.rbtnStudentNo:
                if (rbtnStudentNo.isChecked()){
                    llSchool.setVisibility(View.GONE);
                }
                break;
            case R.id.rbtnStudentYes:
                if (rbtnStudentYes.isChecked()){
                    llSchool.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private Button btnCam, btnCh, btnCancel;
    private void initChooseWindow() {
        View moreView = getLayoutInflater().inflate(R.layout.choose_photo, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        choosePhotoWindow = new PopupWindow(moreView);
        choosePhotoWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        choosePhotoWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        choosePhotoWindow.setFocusable(true);

        moreView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                choosePhotoWindow.dismiss();
                return false;
            }
        });
        btnCam = (Button) moreView.findViewById(R.id.btnCam);
        btnCh = (Button) moreView.findViewById(R.id.btnCh);
        btnCancel = (Button) moreView.findViewById(R.id.btnCancel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE&& resultCode == Activity.RESULT_OK) {
            if (data == null)
                return;

            if (data != null) {
                Uri uri = data.getData();
                if (choosephoto==0) {
                    startPhotoZoom(uri);
                }else {
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, proj, null, null,null);
                    if (cursor != null && cursor.moveToFirst()) {
                        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                        listphotos.add(path);
                        photoRAdapter=new PhotoRAdapter(listphotos,this,this,0);
                        gvPhotos.setAdapter(photoRAdapter);
                    }
                }
            }
        } else if (requestCode == SET_PHOTO) {
            if (data != null) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap cameraBitmap = extras.getParcelable("data");
                    if (cameraBitmap != null) {
                        ivAvatar.setImageBitmap(ImageUtils.toRoundCorner(cameraBitmap, 100));
                        try {
                            String name = (new DateFormat()).format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
                            ImageUtils.saveFile(cameraBitmap, name, this);
                            avatar = Environment.getExternalStorageDirectory() + "/chakeshe/" + name;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }else if (requestCode==REQUEST_C_IMAGE){
            Log.e("requestCode==>",requestCode+"---");
            if (data == null)
                return;
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            String name = (new DateFormat()).format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
            FileUtils.writeFile(Environment.getExternalStorageDirectory() + "/chakeshe/" + name,bitmap);
            if (choosephoto==0) {
                if (data != null) {
                    File picture = new File(Environment.getExternalStorageDirectory() + "/chakeshe/" + name);
                    startPhotoZoom(Uri.fromFile(picture));
                }
            }else {
                listphotos.add(Environment.getExternalStorageDirectory() + "/chakeshe/" + name);
                photoRAdapter=new PhotoRAdapter(listphotos,this,this,0);
                gvPhotos.setAdapter(photoRAdapter);
            }
        }else if (requestCode==AREA&&resultCode==SELECTAREA){
            city=data.getStringExtra("city");
            provinecode=data.getStringExtra("provinecode");
            citycode=data.getStringExtra("citycode");
            areacode=data.getStringExtra("areacode");
            tvCity.setText(city);
        }else if (requestCode==Languge&&resultCode==SelecctLanguge){
            foreignname=data.getStringExtra("foreignname");
            foreignid=data.getStringExtra("foreignid");
            otherforeign=data.getStringExtra("otherforeign");
            if (!StringUtils.isEmpty(foreignname)) {
                tvLanguage.setText(foreignname);
            }else if (!StringUtils.isEmpty(otherforeign)){
                tvLanguage.setText(otherforeign);
            }
        } else if (requestCode==SCHOOL&&resultCode==SCHOOL){
            schoolname=data.getStringExtra("schoolname");
            schoolCode=data.getStringExtra("schoolCode");
            tvSchool.setText(schoolname);
        }else if (requestCode==DEGREE&&resultCode==DEGREE){
            degreename=data.getStringExtra("degreename");
            degreeid=data.getStringExtra("degreeid");
            tvDegree.setText(degreename);
        }
    }

    private String degreeid="";
    private String degreename;
    private String schoolname;
    private String city;
    private String provinecode;
    private String citycode;
    private String areacode;
    private String foreignname;
    private String foreignid;
    private String otherforeign;
    private String schoolCode;

    /*
	 * 裁剪头像
	 */
    public void startPhotoZoom(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");// 裁剪的action
        uri = ParseFilePath.getUri(this, uri);
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, SET_PHOTO);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listphotos.size()<6){
            if (listphotos.size()==position){
                choosephoto=1;
                choosePhotoWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            }
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("user!update")){
            msg.what=1;
        }else if (requestname.equals("user-picture!save")){
            msg.what=2;
        }else if (requestname.equals("user!photo")){
            msg.what=2;
        }else if (requestname.equals("user-picture!delete")){
            msg.what=3;
        }

        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void ondel(int position) {
        delpos=position;
        if (listphotos.get(position).indexOf("http://")>-1){
            String userpictureids=getImageid(listphotos.get(position));
            if (!StringUtils.isEmpty(userpictureids)){
                params=new RequestParams();
                params.put("accessToken",SharedUtils.getToken(this));
                params.put("id", userpictureids);
                post("user-picture!delete",params);
            }
        }else {
            listphotos.remove(position);
            photoRAdapter.notifyDataSetChanged();
        }
    }

    private int delpos=-1;
    private String getImageid(String url){

        if (userPictureBean!=null){
            for (int i=0;i<userPictureBean.getUserPictures().size();i++){
                if (userPictureBean.getUserPictures().get(i).getUrl().equals(url)){
                    return userPictureBean.getUserPictures().get(i).getId();
                }
            }
        }
        return "";
    }
}
