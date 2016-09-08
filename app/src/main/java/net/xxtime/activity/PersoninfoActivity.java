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
import net.xxtime.listener.DeleteListener;
import net.xxtime.utils.Contact;
import net.xxtime.utils.OptionsUtils;
import net.xxtime.utils.SharedUtils;
import net.xxtime.view.DateTimePickDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 完善用户资料
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
    private StudentUserInfoBean studentUserInfoBean;
    private Message msg;

    private LinearLayout llSchool;
    private TextView tvEnrollmentyear , tvSchool, tvDegree;
    private EditText etSchoolType, etMajorname;


    private StudentUserPictureBean studentUserPictureBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    studentUserInfoBean= JSONObject.parseObject(msg.obj.toString(),StudentUserInfoBean.class);
                    if (studentUserInfoBean!=null&&studentUserInfoBean.getBflag().equals("1")){
                        setIserInfo();
                    }
                    break;
                case 2:
                    studentUserPictureBean=JSONObject.parseObject(msg.obj.toString(),StudentUserPictureBean.class);
                    if (studentUserPictureBean!=null&&studentUserPictureBean.getBflag().equals("1")){
                        setPhotoRAdapter();
                    }
                    break;
            }
        }
    };

    private void setPhotoRAdapter(){
        listphotos=new ArrayList<>();
        if (studentUserPictureBean.getDefaultAList()!=null){
            for (int i=0;i<studentUserPictureBean.getDefaultAList().size();i++){
                listphotos.add(studentUserPictureBean.getDefaultAList().get(i).getPicture());
            }
        }
        photoRAdapter=new PhotoRAdapter(listphotos,this,this,1);
        gvPhotos.setAdapter(photoRAdapter);
    }

    private void setIserInfo(){
        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getNickname())){
            etNiceName.setText(studentUserInfoBean.getDefaultAList().get(0).getNickname());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getName())){
            etName.setText(studentUserInfoBean.getDefaultAList().get(0).getName());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getName())){
            etName.setText(studentUserInfoBean.getDefaultAList().get(0).getName());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getGender())){
            if (studentUserInfoBean.getDefaultAList().get(0).getGender().equals("1")) {
                tvSex.setText("男");
            }else {
                tvSex.setText("女");
            }
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getBirthday())){
            tvBrith.setText(studentUserInfoBean.getDefaultAList().get(0).getBirthday());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getHeight())){
            etHeight.setText(studentUserInfoBean.getDefaultAList().get(0).getHeight());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getWeight())){
            etWeight.setText(studentUserInfoBean.getDefaultAList().get(0).getWeight());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getEmail())){
            etEmail.setText(studentUserInfoBean.getDefaultAList().get(0).getEmail());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getIsstudent())){
            if (studentUserInfoBean.getDefaultAList().get(0).getIsstudent().equals("1")) {
                tvStudent.setText("是");
                llSchool.setVisibility(View.VISIBLE);
            }else {
                tvStudent.setText("否");
                llSchool.setVisibility(View.GONE);
            }
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getEmail())){
            etEmail.setText(studentUserInfoBean.getDefaultAList().get(0).getEmail());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getSchoolname())){
            tvSchool.setText(studentUserInfoBean.getDefaultAList().get(0).getSchoolname());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getDegreename())){
            tvDegree.setText(studentUserInfoBean.getDefaultAList().get(0).getDegreename());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getEnrollmentyear())){
            tvEnrollmentyear.setText(studentUserInfoBean.getDefaultAList().get(0).getEnrollmentyear());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getDepartmentname())){
            etSchoolType.setText(studentUserInfoBean.getDefaultAList().get(0).getDepartmentname());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getMajorname())){
            etMajorname.setText(studentUserInfoBean.getDefaultAList().get(0).getMajorname());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getProvincename())
                ||!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getCityname())||
                !StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getAreaname())){
            tvCity.setText(studentUserInfoBean.getDefaultAList().get(0).getProvincename()+studentUserInfoBean.getDefaultAList().get(0).getCityname()
            +studentUserInfoBean.getDefaultAList().get(0).getAreaname());
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getForeignname())){
            tvLanguage.setText(studentUserInfoBean.getDefaultAList().get(0).getForeignname());
        }else {
            tvLanguage.setText("其他");
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getForeignlevel())){
            if (studentUserInfoBean.getDefaultAList().get(0).getForeignlevel().equals("1")) {
                tvLanguageType.setText("日常问候");
            }else if (studentUserInfoBean.getDefaultAList().get(0).getForeignlevel().equals("2")) {
                tvLanguageType.setText("工作交流");
            }else if (studentUserInfoBean.getDefaultAList().get(0).getForeignlevel().equals("3")) {
                tvLanguageType.setText("学术交流");
            }
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getSelf_appraisalids())){
            etBrift.setText(Contact.getLables(studentUserInfoBean.getDefaultAList().get(0).getSelf_appraisalids()));
        }

        if (!StringUtils.isEmpty(studentUserInfoBean.getDefaultAList().get(0).getLogo())){
           ImageLoader.getInstance().displayImage(studentUserInfoBean.getDefaultAList().get(0).getLogo(),ivAvatar,OptionsUtils.getSimpleOptions(80));
        }
    }


    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("getStudentUserInfo")){
            msg.what=1;
        }else if (requestname.equals("getStudentUserPicture")){
            msg.what=2;
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

    @Override
    public void initDatas() {
       /* title=getIntent().getStringExtra("title");
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
        }

        if (!StringUtils.isEmpty(foreignlevel)){
            tvLanguageType.setText(foreignlevel);
        }

        if (!StringUtils.isEmpty(self_appraisalids)){
            etBrift.setText(self_appraisalids);
        }

        if (!StringUtils.isEmpty(avatar)){
            ImageLoader.getInstance().displayImage("file://"+avatar,ivAvatar, OptionsUtils.getSimpleOptions(80));
        }

        if (listphotos!=null) {
            photoRAdapter = new PhotoRAdapter(listphotos, this, this, 1);
            gvPhotos.setAdapter(photoRAdapter);
        }*/

        params=new RequestParams();
        params.put("reqCode","getStudentUserInfo");
        params.put("userid", SharedUtils.getUserId(this));
        Log.e("param==>",params.toString());
        post("studentUser",params,"getStudentUserInfo");


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
        if (start) {
            params = new RequestParams();
            params.put("reqCode", "getStudentUserInfo");
            params.put("userid", SharedUtils.getUserId(this));
            Log.e("param==>", params.toString());
            pullpost("studentUser", params, "getStudentUserInfo");
        }

        params=new RequestParams();
        params.put("reqCode","getStudentUserPicture");
        params.put("userid", SharedUtils.getUserId(this));
        Log.e("param==>",params.toString());
        pullpost("studentUser",params,"getStudentUserPicture");

        start=true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvRight:
                intent=new Intent(this,PerfectInfoActivity.class);
                intent.putExtra("studentUser",studentUserInfoBean);
                intent.putExtra("photos", studentUserPictureBean);
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
