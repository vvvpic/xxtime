package net.xxtime.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


import net.xxtime.R;
import net.xxtime.adapter.PhotoAdapter;
import net.xxtime.listener.OnClickFinishListener;
import net.xxtime.view.SilderViewPager;

import java.util.List;

/***
 * @项目名:Mps
 * @类名:PhotosFullActivity.java
 * @创建人:shibaotong
 * @类描述:图片预览
 * @date:2015年12月4日
 * @Version:1.0 ****************************************
 */

public class PhotosFullActivity extends Activity implements OnPageChangeListener,View.OnClickListener,OnClickFinishListener {
    private SilderViewPager vp_photo;
    private Button btn_cancel, btn_del;
    
    private PhotoAdapter photoAdapter;
    private List<String> listurls;
    private int position;
    
    private boolean del=false;
    private String type;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  1:
                    listurls.remove(position);
                    if (listurls.size()<=position&&position>0){
                        position--;
                    }
                    if(listurls!=null) {
                        photoAdapter = new PhotoAdapter(listurls, PhotosFullActivity.this, PhotosFullActivity.this);
                        vp_photo.setAdapter(photoAdapter);

                        if (position != -1) {
                            vp_photo.setCurrentItem(position);
                        }
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initViews();
        initDatas();
        setDatas();
        setListener();
    }

    public void initDatas() {
        listurls=getIntent().getStringArrayListExtra("urls");
        position=getIntent().getIntExtra("position", -1);
        del=getIntent().getBooleanExtra("del", false);
        type=getIntent().getStringExtra("type");
        
        if(del){
            btn_del.setVisibility(View.VISIBLE);
        }else{
            btn_del.setVisibility(View.GONE);
        }
        
        if(listurls!=null){
        photoAdapter=new PhotoAdapter(listurls, this,this);
        vp_photo.setAdapter(photoAdapter);
        
        if(position!=-1){
            vp_photo.setCurrentItem(position);
        }
        }
        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try{
            super.onTouchEvent(event);
        } catch(IllegalArgumentException ex) {
        }
        return false;
    }

    public void initViews() {
        vp_photo=(SilderViewPager)findViewById(R.id.vp_photo);
        btn_cancel=(Button)findViewById(R.id.btn_cancel);
        btn_del=(Button)findViewById(R.id.btn_del);
    }

    public void setContentView() {
        setContentView(R.layout.activity_photo_browe);

    }

    public void setDatas() { 

    }

    public void setListener() { 
        btn_cancel.setOnClickListener(this);
        vp_photo.setOnPageChangeListener(this);
        btn_del.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_cancel:
            finish();
            break;
        case R.id.btn_del:
          /*  intent=new Intent();
            intent.putExtra("position", position);
            setResult(EDITPHOTO, intent);
            finish();*/
//            Constants.listpositions.add(position);

            if (listurls.size()>1){
            handler.sendEmptyMessage(1);
            }else {
                finish();
            }
            break;
        default:
            break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {   
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {  
        position=arg0;
    }

    @Override
    public void onPageSelected(int arg0) {  
    }

    @Override
    public void onFinish() {
        finish();
        overridePendingTransition(R.anim.zoom_out,R.anim.zoom_finish_in);
    }
}
