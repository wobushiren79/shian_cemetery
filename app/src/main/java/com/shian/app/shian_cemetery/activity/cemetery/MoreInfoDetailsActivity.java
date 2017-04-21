package com.shian.app.shian_cemetery.activity.cemetery;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.CemeteryAgentManInfo;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.CemeteryDeadManInfo;
import com.shian.app.shian_cemetery.staticdata.IntentName;


public class MoreInfoDetailsActivity extends BaseActivity {
    CemeteryDeadManInfo deadManInfoView;
    CemeteryAgentManInfo agentManInfoView;

    RelativeLayout mRLDeadMan;
    RelativeLayout mRLAgentMan;
    long beSpeakId;
    long orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_details);

        setTitle("客户详情", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initData();
        initView();

    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
    }

    private void initView() {

        mRLDeadMan = (RelativeLayout) findViewById(R.id.rl_deadman);
        mRLAgentMan = (RelativeLayout) findViewById(R.id.rl_agentman);


        deadManInfoView = new CemeteryDeadManInfo(MoreInfoDetailsActivity.this, orderId, beSpeakId);
        agentManInfoView = new CemeteryAgentManInfo(MoreInfoDetailsActivity.this, orderId, beSpeakId);


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView mask1 = new TextView(MoreInfoDetailsActivity.this);
        TextView mask2 = new TextView(MoreInfoDetailsActivity.this);

        deadManInfoView.setLayoutParams(layoutParams);
        agentManInfoView.setLayoutParams(layoutParams);

        mask1.setLayoutParams(layoutParams);
        mask2.setLayoutParams(layoutParams);

        mRLAgentMan.addView(agentManInfoView);
        mRLDeadMan.addView(deadManInfoView);

        mRLAgentMan.addView(mask1);
        mRLDeadMan.addView(mask2);

        mask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deadManInfoView.setShowMode();
        agentManInfoView.setShowMode();
    }


}
