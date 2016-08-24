package net.xxtime.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.longtu.base.util.FileUtils;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.longtu.base.view.ScrollGridView;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.PhotoRAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.utils.ImageUtils;
import net.xxtime.utils.ParseFilePath;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.DateTimePickDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * 完善用户资料
 */
public class PerfectInfoActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private PhotoRAdapter photoRAdapter;
    private List<String> listphotos;
    private ScrollGridView gvPhotos;

    private PopupWindow choosePhotoWindow;

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
    private String avatar;

    private TextView tvRight;

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

        tvRight=(TextView)findViewById(R.id.tvRight);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");

    }

    @Override
    public void initDatas() {
        initChooseWindow();
        listphotos=new ArrayList<>();
        photoRAdapter=new PhotoRAdapter(listphotos,this);
        gvPhotos.setAdapter(photoRAdapter);
        setTitle("完善个人信息");

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
    }

    @Override
    public void ResumeDatas() {

    }

    private int gender=1;
    private int isstudent=-1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                if (choosePhotoWindow!=null)
                    choosePhotoWindow.dismiss();
                break;
            case R.id.tvRight:

                if (!StringUtils.isEmpty(etNiceName.getText().toString())){
                    ToastUtils.show(this,"请输入用户名");
                    return;
                }

                if (!StringUtils.isEmpty(etName.getText().toString())){
                    ToastUtils.show(this,"请输入姓名");
                    return;
                }

                if (rbtn_female.isChecked()){
                    gender=0;
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
                    ToastUtils.show(this,"请选择所在学校");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","modifyStudentUserInfo");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("nickname",etNiceName.getText().toString());
                params.put("name",etName.getText().toString());
                params.put("gender",gender);
                params.put("isstudent",isstudent);
                params.put("province",provinecode);
                params.put("city",citycode);
                params.put("area",areacode);
                if (!StringUtils.isEmpty(tvBrith.getText().toString())){
                    params.put("birthday",tvBrith.getText().toString());
                }

                if (!StringUtils.isEmpty(etHeight.getText().toString())){
                    params.put("height",etHeight.getText().toString());
                }

                if (!StringUtils.isEmpty(etWeight.getText().toString())){
                    params.put("weight",etWeight.getText().toString());
                }

                if (!StringUtils.isEmpty(etEmail.getText().toString())){
                    params.put("email",etEmail.getText().toString());
                }

                if (!StringUtils.isEmpty(tvLanguage.getText().toString())){
                    params.put("foreignid",foreignid);
                }

                if (rbtnLanC.isChecked()){
                    params.put("foreignlevel",1);
                }else if (rbtnLanWork.isChecked()){
                    params.put("foreignlevel",2);
                }else if (rbtnLanS.isChecked()){
                    params.put("foreignlevel",3);
                }

                if (!StringUtils.isEmpty(etBrift.getText().toString())){
                    params.put("self_appraisalids",etBrift.getText().toString());
                }

                post("studentUser",params,"modifyStudentUserInfo");

                break;
            case R.id.tvBrith:
                dateTimePickDialog = new DateTimePickDialog(this, "1990-1-1");
                dateTimePickDialog.setTimePicker(View.GONE);
                dateTimePickDialog.dateTimePicKDialog(tvBrith);
                break;
            case R.id.tvCity:
                intent=new Intent(this,SelectAreaActivity.class);
                Jump(intent,AREA);
                break;
            case R.id.tvLanguage:
                intent=new Intent(this,SelectLanguageActivity.class);
                Jump(intent,Languge);
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
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if (data == null)
                return;

            if (data != null) {
                Uri uri = data.getData();
                if (choosephoto==0) {
                    startPhotoZoom(uri);
                }else {
//                    Log.e("imgPath==>",uri.getPath());
                    listphotos.add(uri.getPath());
                    photoRAdapter=new PhotoRAdapter(listphotos,this);
                    gvPhotos.setAdapter(photoRAdapter);
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
                photoRAdapter=new PhotoRAdapter(listphotos,this);
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
            tvLanguage.setText(foreignname);
        }
    }

    private String city;
    private String provinecode;
    private String citycode;
    private String areacode;
    private String foreignname;
    private String foreignid;

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
}
