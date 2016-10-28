package net.xxtime.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.longtu.base.util.StringUtils;
import com.loopj.android.http.RequestParams;

import net.xxtime.R;
import net.xxtime.adapter.SchoolAdapter;
import net.xxtime.base.activity.BaseActivity;
import net.xxtime.bean.AllSchoolsBean;
import net.xxtime.utils.SharedUtils;

public class SelectSchoolActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private EditText et_search;
    private ListView lvSchools;

    private Message msg;

    private AllSchoolsBean allSchoolsBean;

    private SchoolAdapter schoolAdapter;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    allSchoolsBean= JSONObject.parseObject(msg.obj.toString(),AllSchoolsBean.class);
                    if (allSchoolsBean!=null&&allSchoolsBean.getStatus().equals("1")){
                        schoolAdapter=new SchoolAdapter(allSchoolsBean.getColleges(),SelectSchoolActivity.this);
                        lvSchools.setAdapter(schoolAdapter);
                    }
                    break;
            }
        }
    };


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_selet_school);
    }

    @Override
    public void initViews() {
        et_search=(EditText)findViewById(R.id.et_search);
        lvSchools=(ListView)findViewById(R.id.lvSchools);
    }

    @Override
    public void initDatas() {
        setTitle("选择学校");
    }

    private void getSearch(String search){
        params=new RequestParams();
        params.put("accessToken", SharedUtils.getToken(this));
        if (!StringUtils.isEmpty(search)){
            params.put("query.name",search);
        }
        Log.e("param==>",params.toString());
        pullpost("college!list",params);
    }

    @Override
    public void setDatas() {
        et_search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        et_search.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    getSearch(et_search.getText().toString());
                    hideSoftInput(et_search);
                    return false;
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent();
        intent.putExtra("schoolname",allSchoolsBean.getColleges().get(position).getName());
        intent.putExtra("schoolCode",allSchoolsBean.getColleges().get(position).getId());
        setResult(SCHOOL,intent);
        finish();
    }

    public class EditTextWatcher implements TextWatcher {
        int code;

        public EditTextWatcher(int i) {
            code = i;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            if (!StringUtils.isEmpty(et_search.getText().toString())) {
                getSearch(et_search.getText().toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO 自动生成的方法存根

        }

    }


    @Override
    public void setListener() {
        et_search.addTextChangedListener(new EditTextWatcher(R.id.et_search));
        lvSchools.setOnItemClickListener(this);
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
        if (requestname.equals("college!list")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
