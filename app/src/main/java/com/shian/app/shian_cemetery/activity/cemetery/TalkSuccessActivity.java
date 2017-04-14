package com.shian.app.shian_cemetery.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.CemeteryInfoStepsEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.fragment.CemeteryOrderFragment;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.BaseCemeteryInfo;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.CemeteryAgentManInfo;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.CemeteryDeadManInfo;
import com.shian.app.shian_cemetery.order.cemetery.infolayout.CemeteryPreInfo;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.ToastUtils;

public class TalkSuccessActivity extends BaseActivity {

    long beSpeakId = -1;
    long orderId = -1;
    int steps = -1;

    LinearLayout mLLContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_success);

        initView();
        initData();
    }


    private void initView() {
        mLLContent = (LinearLayout) findViewById(R.id.ll_content);
    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
        steps = getIntent().getIntExtra(IntentName.INTENT_CEMETERY_INFO_STEPS, -1);

        initContent();
    }

    /**
     * 初始化content
     */
    private void initContent() {
        BaseCemeteryInfo view = null;
        if (steps == CemeteryInfoStepsEnum.UNFILL.getCode()) {
            view = new CemeteryPreInfo(TalkSuccessActivity.this, orderId, beSpeakId);
            setTitle("购墓信息", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        } else if (steps == CemeteryInfoStepsEnum.FILLPRE.getCode()) {
            view = new CemeteryDeadManInfo(TalkSuccessActivity.this, orderId, beSpeakId);
            setTitle("逝者信息", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        } else if (steps == CemeteryInfoStepsEnum.FILLDEADMAN.getCode()) {
            view = new CemeteryAgentManInfo(TalkSuccessActivity.this, orderId, beSpeakId);
            setTitle("经办人信息", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        } else if (steps == CemeteryInfoStepsEnum.FILLAGENTMAN.getCode()) {

        }
        setInfoCallBack(view);

    }

    private void setInfoCallBack(BaseCemeteryInfo view) {
        if (view != null) {
            view.setCallBack(new BaseCemeteryInfo.CallBack() {
                @Override
                public void next(BaseCemeteryInfo view) {
                    if (view == null) {
                        CemeteryOrderFragment.isRefesh = true;
                        finish();
                    } else {
                        if (CemeteryPreInfo.class.isInstance(view)) {
                            setTitle("购墓信息", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
                        } else if (CemeteryDeadManInfo.class.isInstance(view)) {
                            setTitle("逝者信息", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
                        } else if (CemeteryAgentManInfo.class.isInstance(view)) {
                            setTitle("经办人信息", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
                        }
                        setInfoCallBack(view);
                        addContent(view);
                    }
                }
            });
            addContent(view);
        } else {
            setTitle("数据读取错误", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        }
    }

    /**
     * 添加內容
     *
     * @param view
     */
    private void addContent(BaseCemeteryInfo view) {
        mLLContent.removeAllViews();
        mLLContent.addView(view);
    }
}
