package net.xxtime.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;

import net.xxtime.R;
import net.xxtime.adapter.LableAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.utils.Contact;

import java.io.Serializable;

public class LablesActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvLables;

    private LableAdapter lableAdapter;

    private Button btnOk;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_lables);
    }

    @Override
    public void initViews() {
        lvLables=(ListView)findViewById(R.id.lvLables);
        btnOk=(Button)findViewById(R.id.btnOk);
    }

    @Override
    public void initDatas() {
        setTitle("自我评价");
        if (Contact.appraisalLabelsBean!=null) {
            lableAdapter=new LableAdapter(Contact.appraisalLabelsBean.getDefaultAList(),this);
            lvLables.setAdapter(lableAdapter);
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvLables.setOnItemClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOk:
                String lables=getLable();

                if (StringUtils.isEmpty(lables)){
                    ToastUtils.show(this,"请选择自我评价");
                    return;
                }

                intent=new Intent();
                intent.putExtra("lables",lables);
                setResult(LABLE,intent);
                finish();

                break;
        }
    }

    private String getLable(){
        String lables="";
        if (Contact.appraisalLabelsBean!=null) {
            for (int i = 0; i <Contact.appraisalLabelsBean.getDefaultAList().size();i++){
                if (Contact.appraisalLabelsBean.getDefaultAList().get(i).choosebool){
                    lables=lables+Contact.appraisalLabelsBean.getDefaultAList().get(i).getLabelid()+",";
                }
            }
        }
        if (!StringUtils.isEmpty(lables)){
            lables=lables.substring(0,lables.length()-1);
        }

        return lables;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact.appraisalLabelsBean.getDefaultAList().get(position).choosebool=!Contact.appraisalLabelsBean.getDefaultAList().get(position).choosebool;
        lableAdapter.notifyDataSetChanged();
    }
}
