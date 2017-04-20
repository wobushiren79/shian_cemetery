package com.shian.app.shian_cemetery.activity.php;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.HelpEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.common.view.ScrollGridView;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;
import com.shian.app.shian_cemetery.view.customlayout.helpitem.HelpHotIssue;
import com.shian.app.shian_cemetery.view.customlayout.helpitem.HelpItemsLayout;
import com.shian.app.shian_cemetery.view.customlayout.maindynamic.MainDynamic;


public class CustomerHelpActivity extends BaseActivity {

    LinearLayout mLLLineHelp;
    LinearLayout mLLPhoneHelp;

    ScrollGridView mGridView;
    HelpHotIssue mHelpHotIssue;

    HelpEnum[] helpEna = {
            HelpEnum.MONEY,
            HelpEnum.SYSTEMHELP,
            HelpEnum.PROJECTDETAILS,
            HelpEnum.TALK,
            HelpEnum.PLATFORM,
            HelpEnum.OTHER
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_help);

        setTitle("客服帮助",BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
    }

    private void initView() {
        mLLLineHelp = (LinearLayout) findViewById(R.id.ll_linehelp);
        mLLPhoneHelp = (LinearLayout) findViewById(R.id.ll_phonehelp);
        mGridView = (ScrollGridView) findViewById(R.id.gridview);
        mHelpHotIssue = (HelpHotIssue) findViewById(R.id.help_hotissue);
        mHelpHotIssue.setVisibility(View.GONE);


        mLLPhoneHelp.setOnClickListener(onClickListener);
        mLLLineHelp.setOnClickListener(onClickListener);

        mGridView.setAdapter(helpAdapter);


        mHelpHotIssue.setCallBack(new MainDynamic.CallBack() {
            @Override
            public void loadingComplete() {
                mHelpHotIssue.setVisibility(View.VISIBLE);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLLPhoneHelp) {
                Utils.call(v, "4009679678");
            } else if (v == mLLLineHelp) {
//                KF5SDKConfig.INSTANCE.startKF5ChatActivity(CustomerHelpActivity.this);
                ToastUtils.showShortToast(CustomerHelpActivity.this,"暂时不提供此项服务");
            }
        }
    };

    BaseAdapter helpAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return helpEna.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HelpItemsLayout helpItemsLayout = new HelpItemsLayout(CustomerHelpActivity.this);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_132dp));
            helpItemsLayout.setLayoutParams(layoutParams);
            helpItemsLayout.setData(helpEna[position].getName(), helpEna[position].getPicId(), helpEna[position].getUrl());
            return helpItemsLayout;
        }
    };
}
