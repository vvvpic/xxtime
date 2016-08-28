package net.xxtime.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.FileUtils;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.xxtime.R;
import net.xxtime.adapter.PhotoRAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.base.activity.XxtimeApplication;
import net.xxtime.bean.CommonBean;
import net.xxtime.utils.ImageUtils;
import net.xxtime.utils.SharedUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class AuthenticationActivity extends BaseActivity {

    private ImageView  ivAddjust, ivAddback, ivAddstudent;
    private EditText etName, etId ,etStudentId;
    private TextView tvStatus;
    private Button btnSubmit;

    private PopupWindow choosePhotoWindow;

    private ImageView ivDelstudent, ivDelback, ivDeljust;

    private String isstudent;

    private RelativeLayout rlStudent;

    private Message msg;
    private CommonBean commonBean;
    private int uploadint=0,up=0;

    private LinearLayout llSutent;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null){
//                        ToastUtils.show(PerfectInfoActivity.this,commonBean.getMsg());
                        if (commonBean.getBflag().equals("1")){
                            if (!StringUtils.isEmpty(addjust)){
                                if (addjust.indexOf("http://")<0){
                                    params=new RequestParams();
                                    params.put("reqCode","uploadImages");
                                    params.put("userid", SharedUtils.getUserId(AuthenticationActivity.this));
                                    params.put("type",4);
                                    File myFile = new File(addjust);
                                    try {
                                        params.put("file1", myFile);
                                    } catch(FileNotFoundException e) {}
                                    uploadint++;
                                    pullpost("studentUser", params,"uploadImages");
                                }
                            }

                            if (!StringUtils.isEmpty(addback)){
                                if (addback.indexOf("http://")<0){
                                    params=new RequestParams();
                                    params.put("reqCode","uploadImages");
                                    params.put("userid", SharedUtils.getUserId(AuthenticationActivity.this));
                                    params.put("type",5);
                                    File myFile = new File(addback);
                                    try {
                                        params.put("file1", myFile);
                                    } catch(FileNotFoundException e) {}
                                    uploadint++;
                                    pullpost("studentUser", params,"uploadImages");
                                }
                            }

                            if (!StringUtils.isEmpty(addstudent)){
                                if (addstudent.indexOf("http://")<0){
                                    params=new RequestParams();
                                    params.put("reqCode","uploadImages");
                                    params.put("userid", SharedUtils.getUserId(AuthenticationActivity.this));
                                    params.put("type",5);
                                    File myFile = new File(addstudent);
                                    try {
                                        params.put("file1", myFile);
                                    } catch(FileNotFoundException e) {}
                                    uploadint++;
                                    pullpost("studentUser", params,"uploadImages");
                                }
                            }

                            if (uploadint==0){
                                disMiss();
                                ToastUtils.show(AuthenticationActivity.this,"修改完善个人信息成功！");
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
                        ToastUtils.show(AuthenticationActivity.this,"修改完善个人信息成功！");
                        finish();
                    }
                    break;

            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_authentication);
    }

    @Override
    public void initViews() {
        ivAddjust =(ImageView)findViewById(R.id.ivAddjust) ;
        ivAddback  =(ImageView)findViewById(R.id.ivAddback) ;
        ivAddstudent =(ImageView)findViewById(R.id.ivAddstudent) ;

        ivDelstudent =(ImageView)findViewById(R.id.ivDelstudent) ;
        ivDelback =(ImageView)findViewById(R.id.ivDelback) ;
        ivDeljust =(ImageView)findViewById(R.id.ivDeljust) ;

        etName  =(EditText) findViewById(R.id.etName) ;
        etId  =(EditText) findViewById(R.id.etId) ;
        etStudentId  =(EditText) findViewById(R.id.etStudentId) ;
        tvStatus  =(TextView) findViewById(R.id.tvStatus) ;
        btnSubmit =(Button) findViewById(R.id.btnSubmit) ;

        ivDelstudent.setVisibility(View.GONE);
        ivDelback.setVisibility(View.GONE);
        ivDeljust.setVisibility(View.GONE);

        rlStudent=(RelativeLayout)findViewById(R.id.rlStudent);
        llSutent=(LinearLayout) findViewById(R.id.llSutent);
    }

    @Override
    public void initDatas() {

      /*  FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        params.width= XxtimeApplication.width/5;
        params.height=params.width;

        ivAddjust.setLayoutParams(params);
        ivAddback.setLayoutParams(params);
        ivAddstudent.setLayoutParams(params);*/

        isstudent=getIntent().getStringExtra("isstudent");

        if (!isstudent.equals("1")){
            llSutent.setVisibility(View.INVISIBLE);
            rlStudent.setVisibility(View.GONE);
        }

        initChooseWindow();

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        ivAddstudent.setOnClickListener(this);
        ivAddjust.setOnClickListener(this);
        ivAddback.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnCam .setOnClickListener(this);
        btnCh.setOnClickListener(this);

        ivDelstudent.setOnClickListener(this);
        ivDelback.setOnClickListener(this);
        ivDeljust.setOnClickListener(this);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    private int addtype=-1;
    private String addstudent="",addback="",addjust="";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivAddstudent:
                addtype=2;
                choosePhotoWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ivAddback:
                addtype=1;
                choosePhotoWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.ivAddjust:
                addtype=0;
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
            case R.id.ivDeljust:
                addjust="";
                ivDeljust.setVisibility(View.GONE);
                ivAddjust.setEnabled(true);
                ivAddjust.setImageResource(R.drawable.btn_add_img_selecter);
                break;
            case R.id.ivDelback:
                addback="";
                ivDelback.setVisibility(View.GONE);
                ivAddback.setEnabled(true);
                ivAddback.setImageResource(R.drawable.btn_add_img_selecter);
                break;
            case R.id.ivDelstudent:
                addstudent="";
                ivDelstudent.setVisibility(View.GONE);
                ivAddstudent.setEnabled(true);
                ivAddstudent.setImageResource(R.drawable.btn_add_img_selecter);
                break;
            case R.id.btnSubmit:

                if (StringUtils.isEmpty(etName.getText().toString())){
                    ToastUtils.show(this,"请输入真实姓名");
                    return;
                }

                if (StringUtils.isEmpty(etId.getText().toString())){
                    ToastUtils.show(this,"请输入身份证号");
                    return;
                }

                if(isstudent.equals("1")){
                    if (StringUtils.isEmpty(etStudentId.getText().toString())){
                        ToastUtils.show(this,"请输入学生证号");
                        return;
                    }

                    if (StringUtils.isEmpty(addstudent)){
                        ToastUtils.show(this,"请选择学生证");
                        return;
                    }
                }

                if (StringUtils.isEmpty(addjust)){
                    ToastUtils.show(this,"请选择身份证正面");
                    return;
                }

                if (StringUtils.isEmpty(addback)){
                    ToastUtils.show(this,"请选择身份证反面");
                    return;
                }

                params=new RequestParams();
                params.put("reqCode","modifyStudentUserInfo");
                params.put("userid", SharedUtils.getUserId(this));
                params.put("name",etName.getText().toString());
                params.put("cardcode",etId.getText().toString());
                params.put("studentcardcode",etStudentId.getText().toString());
                post("studentUser",params,"modifyStudentUserInfo");
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
                    Cursor cursor = getContentResolver().query(uri, null, null, null,null);
                    if (cursor != null && cursor.moveToFirst()) {
                        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                       if (addtype==0){
                           addjust=path;
                           ivDeljust.setVisibility(View.VISIBLE);
                           ivAddjust.setEnabled(false);
                           ImageLoader.getInstance().displayImage("file://"+path,ivAddjust);
                       }else if (addtype==1){
                           addback=path;
                           ivDelback.setVisibility(View.VISIBLE);
                           ivAddback.setEnabled(false);
                           ImageLoader.getInstance().displayImage("file://"+path,ivAddback);
                       }else if (addtype==2){
                           addstudent=path;
                           ivDelstudent.setVisibility(View.VISIBLE);
                           ImageLoader.getInstance().displayImage("file://"+path,ivAddstudent);
                           ivAddstudent.setEnabled(false);
                       }
                    }
            }
        } else if (requestCode==REQUEST_C_IMAGE){
            Log.e("requestCode==>",requestCode+"---");
            if (data == null)
                return;
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            String name = (new DateFormat()).format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
            FileUtils.writeFile(Environment.getExternalStorageDirectory() + "/chakeshe/" + name,bitmap);
            if (addtype==0){
                addjust=Environment.getExternalStorageDirectory() + "/chakeshe/" + name;
                ivDeljust.setVisibility(View.VISIBLE);
               ivAddjust.setImageBitmap(bitmap);
                ivAddjust.setEnabled(false);
            }else if (addtype==1){
                addback=Environment.getExternalStorageDirectory() + "/chakeshe/" + name;
                ivDelback.setVisibility(View.VISIBLE);
                ivAddback.setImageBitmap(bitmap);
                ivAddback.setEnabled(false);
            }else if (addtype==2){
                addstudent=Environment.getExternalStorageDirectory() + "/chakeshe/" + name;
                ivDelstudent.setVisibility(View.VISIBLE);
                ivAddstudent.setImageBitmap(bitmap);
                ivAddstudent.setEnabled(false);
            }
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("modifyStudentUserInfo")){
            msg.what=1;
        }else if (requestname.equals("uploadImages")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

}
