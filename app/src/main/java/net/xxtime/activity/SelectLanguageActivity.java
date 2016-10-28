package net.xxtime.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.LanguageAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.ForeignBean;
import net.xxtime.utils.SharedUtils;

import java.lang.reflect.Field;

/**
 * 选择语言
 */
public class SelectLanguageActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvLanguage;

    private Message msg;

    private ForeignBean foreignBean;

    private LanguageAdapter languageAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    foreignBean= JSONObject.parseObject(msg.obj.toString(),ForeignBean.class);
                    if (foreignBean!=null&&foreignBean.getStatus().equals("1")){
                        languageAdapter=new LanguageAdapter(foreignBean.getForeigns(),SelectLanguageActivity.this);
                        lvLanguage.setAdapter(languageAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_select_language);
    }

    @Override
    public void initViews() {
        lvLanguage=(ListView)findViewById(R.id.lvLanguage);
    }

    @Override
    public void initDatas() {
        setTitle("选择外语");
        params=new RequestParams();
        params.put("accessToken", SharedUtils.getToken(this));
        post("foreign!list",params);

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvLanguage.setOnItemClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("foreign!list")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (foreignBean.getForeigns().size()==position){
            setEdit();
            return;
        }
        intent=new Intent();
        intent.putExtra("foreignname",foreignBean.getForeigns().get(position).getName());
        intent.putExtra("foreignid",foreignBean.getForeigns().get(position).getId()+"");
        setResult(SelecctLanguge,intent);
        finish();
    }

    /**
     * 输入提示框
     *
     */
    private void setEdit() {
        LayoutInflater factory = LayoutInflater.from(this);// 提示框
        final View view = factory.inflate(R.layout.update_info, null);// 这里必须是final的
        final EditText editcontent = (EditText) view.findViewById(R.id.info_text); // 获得输入框对象
        new AlertDialog.Builder(this).setTitle("请填写外语").setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (StringUtils.isEmpty(editcontent.getText().toString())) {
                            ToastUtils.show(SelectLanguageActivity.this,"请填写外语");
                            try {
                                Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                                field.setAccessible(true);
                                field.set(dialog, false); // false -
                                // 使之不能关闭(此为机关所在，其它语句相同)
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return;
                        } else {
                            intent=new Intent();
                            intent.putExtra("otherforeign",editcontent.getText().toString());
                            intent.putExtra("foreignid",0+"");
                            setResult(SelecctLanguge,intent);
                            finish();
                        }

                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(dialog, true); // false -
                    // 使之不能关闭(此为机关所在，其它语句相同)
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).show();
    }

}
