package net.xxtime.fragmet;

import android.view.View;
import android.widget.RelativeLayout;

import net.xxtime.R;
import net.xxtime.base.fragment.BaseFragment;

/**
 * Created by 唯图 on 2016/8/18.
 */
public class MsgFragment extends BaseFragment {

    private RelativeLayout  rlFollow ,rlApply ,rlSalary, rlWithdraw, rlSystem;

    @Override
    public void setContentView() {
        layout= R.layout.fgment_msg;
    }

    @Override
    public void initViews() {
        rlFollow =(RelativeLayout)view.findViewById(R.id.rlFollow);
        rlApply  =(RelativeLayout)view.findViewById(R.id.rlApply);
        rlSalary  =(RelativeLayout)view.findViewById(R.id.rlSalary);
        rlWithdraw  =(RelativeLayout)view.findViewById(R.id.rlWithdraw);
        rlSystem =(RelativeLayout)view.findViewById(R.id.rlSystem);
    }

    @Override
    public void initDatas() {
        setTitle("消息列表");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void Receive(String requestname, String response) {

    }
}
