package com.shian.app.shian_cemetery.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.OrderUserEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.tools.SharePerfrenceUtils;
import com.shian.app.shian_cemetery.view.toolsview.radiogroupview.RadioButtonBean;
import com.shian.app.shian_cemetery.view.toolsview.radiogroupview.RadioGroupView;

import java.util.ArrayList;
import java.util.List;

public class ChangeRoleActivity extends BaseActivity {
    LinearLayout mLLContent;
    TextView mTVSubmit;
    RadioGroupView radioGroupView;

    List<RadioButtonBean> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_role);
        setTitle("更改角色", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
        initData();
    }

    public void initView() {
        mLLContent = (LinearLayout) findViewById(R.id.ll_content);
        mTVSubmit = (TextView) findViewById(R.id.tv_submit);

        mTVSubmit.setOnClickListener(onClickListener);
    }

    private void initData() {
        RadioButtonBean burialBean = new RadioButtonBean();
        RadioButtonBean cemeteryBean = new RadioButtonBean();

        burialBean.setNum(0);
        burialBean.setName(OrderUserEnum.Burial.getName());
        burialBean.setCode(OrderUserEnum.Burial.getCode());

        cemeteryBean.setNum(1);
        cemeteryBean.setName(OrderUserEnum.Cemetery.getName());
        cemeteryBean.setCode(OrderUserEnum.Cemetery.getCode());

        listData.add(burialBean);
        listData.add(cemeteryBean);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        radioGroupView = new RadioGroupView(this, listData);
        radioGroupView.setLayoutParams(layoutParams);
        mLLContent.addView(radioGroupView, 0);

        //初始化数据
        int orderUser = SharePerfrenceUtils.getOrderUser(this);
        radioGroupView.setData(orderUser);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVSubmit) {
                submit();
            }
        }
    };

    /**
     * 确认切换
     */
    private void submit() {
        int orderUser = radioGroupView.getData().getCode();
        SharePerfrenceUtils.setOrderUser(ChangeRoleActivity.this, orderUser);
        Intent mIntent = new Intent();
        setResult(1001, mIntent);
        finish();
    }
}
