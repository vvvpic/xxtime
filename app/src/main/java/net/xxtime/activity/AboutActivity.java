package net.xxtime.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.xxtime.R;
import net.xxtime.base.activity.BaseActivity;

/**
 * g关于我们
 */
public class AboutActivity extends BaseActivity {

    private TextView tvTel;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void initViews() {
        tvTel=(TextView)findViewById(R.id.tvTel);
    }

    @Override
    public void initDatas() {
        setTitle("关于我们");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvTel.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTel:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:021-80376968"));
                startActivity(intent);
                break;
        }
    }
}
