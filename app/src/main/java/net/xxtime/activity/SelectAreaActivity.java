package net.xxtime.activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.longtu.base.util.StringUtils;

import android.R.anim;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import net.xxtime.R;
import net.xxtime.adapter.AreaAdapter;
import net.xxtime.adapter.CityAdapter;
import net.xxtime.adapter.ProvineAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.AreaBean;
import net.xxtime.utils.Contact;

import java.util.List;

/***
 * 
 * @项目名:vvvpic  
 * 
 * @类名:SelectAreaActivity.java  
 * 
 * @创建人:shibaotong 
 *
 * @类描述:选着区域
 * 
 * @date:2016年4月5日
 * 
 * @Version:1.0 
 *
 *****************************************
 */
public class SelectAreaActivity extends BaseActivity implements OnItemClickListener{
    
    private ImageButton ibtn_left, ibtn_right;
    private TextView tv_title;

    private ListView lv_area;
    
    private AreaAdapter areaAdapter;
    private ProvineAdapter provineAdapter;
    private CityAdapter cityAdapter;
    private int propos=0,citypos=0;

    private int type=1;

    private boolean user;

    @Override
    public void ResumeDatas() { 

    }

    @Override
    public void initDatas() { 
        setTitle("选择地区");

        if (Contact.choosecitysBean==null){
            for (int i=0;i<Contact.citysBean.getProvince().size();i++){
                if (Contact.citysBean.getProvince().get(i).getCity()!=null&&Contact.citysBean.getProvince().get(i).getCity().size()>0){
                    Contact.citysBean.getProvince().get(i).nextbool=true;
                    for (int j=0;j<Contact.citysBean.getProvince().get(i).getCity().size();j++){
                        if (Contact.citysBean.getProvince().get(i).getCity().get(j).getArea()!=null&&
                                Contact.citysBean.getProvince().get(i).getCity().get(j).getArea().size()>0) {
                              Contact.citysBean.getProvince().get(i).getCity().get(j).nextbool = true;
                        }
                    }
                }
            }
            Contact.choosecitysBean=Contact.citysBean;
        }

        
        if( Contact.choosecitysBean!=null){
            provineAdapter=new ProvineAdapter( Contact.choosecitysBean.getProvince(), this);
            lv_area.setAdapter(provineAdapter);
        }
    }

    @Override
    public void initViews() {
        lv_area=(ListView)findViewById(R.id.lv_area);
    }

    @Override
    public void setContentView() { 
        setContentView(R.layout.activity_area);
    }

    @Override
    public void setDatas() { 

    }

    @Override
    public void setListener() {
        lv_area.setOnItemClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) { 
        switch (v.getId()) {
        case R.id.ivBack:
            if(type==1){
            finish();
            }else if (type==2){
                provineAdapter=new ProvineAdapter(Contact.choosecitysBean.getProvince(),this);
                lv_area.setAdapter(provineAdapter);
                type=1;
            }else {
                cityAdapter=new CityAdapter(Contact.choosecitysBean.getProvince().get(propos).getCity(),this);
                lv_area.setAdapter(cityAdapter);
                type=2;
            }
            break;

        default:
            break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        if(type==1){
            propos=position;
            cityAdapter=new CityAdapter(Contact.choosecitysBean.getProvince().get(position).getCity(),this);
            lv_area.setAdapter(cityAdapter);
            type=2;
        }else if (type==2){
            citypos=position;
            areaAdapter=new AreaAdapter(Contact.choosecitysBean.getProvince().get(propos).getCity().get(position).getArea(),this);
            lv_area.setAdapter(areaAdapter);
            type=3;
        }else {
            intent=new Intent();
            intent.putExtra("city",Contact.choosecitysBean.getProvince().get(propos).getAddName()+
                    Contact.choosecitysBean.getProvince().get(propos).getCity().get(citypos).getAddName()+
                    Contact.choosecitysBean.getProvince().get(propos).getCity().get(citypos).getArea().get(position).getAddName());
            intent.putExtra("provinecode",Contact.choosecitysBean.getProvince().get(propos).getCode());
            intent.putExtra("citycode",Contact.choosecitysBean.getProvince().get(propos).getCity().get(citypos).getCode());
            intent.putExtra("areacode",Contact.choosecitysBean.getProvince().get(propos).getCity().get(citypos).getArea().get(position).getCode());
            setResult(SELECTAREA,intent);
            finish();
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(type==1){
                finish();
            }else if (type==2){
                provineAdapter=new ProvineAdapter(Contact.choosecitysBean.getProvince(),this);
                lv_area.setAdapter(provineAdapter);
                type=1;
            }else {
                cityAdapter=new CityAdapter(Contact.choosecitysBean.getProvince().get(propos).getCity(),this);
                lv_area.setAdapter(cityAdapter);
                type=2;
            }
        }
        return false;
    }

}
