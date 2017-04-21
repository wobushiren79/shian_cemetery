package com.shian.app.shian_cemetery.activity.cemetery;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.PackageHealthStats;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.CemeteryPreInfo;
import com.shian.app.shian_cemetery.staticdata.IntentName;

public class InfoDetailsActivity extends BaseActivity {

    Button mBTDetailes;
    CemeteryPreInfo cemeteryInfoView;
    RelativeLayout mRLContent;
    long orderId;
    long beSpeakId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        setTitle("订单详情", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initData();
        initView();

    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
    }

    private void initView() {
        mBTDetailes = (Button) findViewById(R.id.bt_detailes);
        mRLContent = (RelativeLayout) findViewById(R.id.rl_content);

        cemeteryInfoView = new CemeteryPreInfo(InfoDetailsActivity.this, orderId, beSpeakId);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView mask = new TextView(InfoDetailsActivity.this);
        mask.setLayoutParams(layoutParams);
        cemeteryInfoView.setLayoutParams(layoutParams);
        mRLContent.addView(cemeteryInfoView);
        mRLContent.addView(mask);
        mBTDetailes.setOnClickListener(onClickListener);

        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cemeteryInfoView.setShowMode();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTDetailes) {
                Intent intent = new Intent(InfoDetailsActivity.this, MoreInfoDetailsActivity.class);
                intent.putExtra(IntentName.INTENT_BESPEAKID, beSpeakId);
                intent.putExtra(IntentName.INTENT_ORDERID, orderId);
                startActivity(intent);
            }
        }
    };


}
