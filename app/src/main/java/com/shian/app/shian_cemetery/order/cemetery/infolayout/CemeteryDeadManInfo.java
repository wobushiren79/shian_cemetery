package com.shian.app.shian_cemetery.order.cemetery.infolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.cemetery.TalkSuccessActivity;
import com.shian.app.shian_cemetery.staticdata.SelectDictCode;
import com.shian.app.shian_cemetery.view.dataview.cemetery.EditTextViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.SpinnerViewNormal;
import com.shian.app.shian_cemetery.view.dataview.cemetery.TimeSelectViewNormal;

/**
 * Created by Administrator on 2017/4/12.
 */

public class CemeteryDeadManInfo extends BaseCemeteryInfo {

    EditTextViewNormal mUserName1;
    EditTextViewNormal mUserName2;
    EditTextViewNormal mUserAge1;
    EditTextViewNormal mUserAge2;
    SpinnerViewNormal mUserSex1;
    SpinnerViewNormal mUserSex2;
    SpinnerViewNormal mUserState1;
    SpinnerViewNormal mUserState2;
    EditTextViewNormal mUserCardId1;
    EditTextViewNormal mUserCardId2;
    TimeSelectViewNormal mUserBirth1;
    TimeSelectViewNormal mUserBirth2;

    TextView mTVNext;
    TextView mTVBack;


    public CemeteryDeadManInfo(Context context, long orderId, long bespeakId) {
        super(context, orderId, bespeakId, R.layout.layout_cemetery_info_deadman);
    }

    @Override
    public void getData() {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void initView() {
        mUserName1 = (EditTextViewNormal) view.findViewById(R.id.write_username_1);
        mUserName2 = (EditTextViewNormal) view.findViewById(R.id.write_username_2);
        mUserAge1 = (EditTextViewNormal) view.findViewById(R.id.write_userage_1);
        mUserAge2 = (EditTextViewNormal) view.findViewById(R.id.write_userage_2);
        mUserSex1 = (SpinnerViewNormal) view.findViewById(R.id.write_usersex_1);
        mUserSex2 = (SpinnerViewNormal) view.findViewById(R.id.write_usersex_2);
        mUserState1 = (SpinnerViewNormal) view.findViewById(R.id.write_userstate_1);
        mUserState2 = (SpinnerViewNormal) view.findViewById(R.id.write_userstate_2);
        mUserCardId1 = (EditTextViewNormal) view.findViewById(R.id.write_usercardid_1);
        mUserCardId2 = (EditTextViewNormal) view.findViewById(R.id.write_usercardid_2);
        mUserBirth1 = (TimeSelectViewNormal) view.findViewById(R.id.write_userbirth_1);
        mUserBirth2 = (TimeSelectViewNormal) view.findViewById(R.id.write_userbirth_2);

        mTVNext = (TextView) view.findViewById(R.id.tv_submit);
        mTVBack = (TextView) view.findViewById(R.id.tv_back);

        mTVNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTVBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.next(new CemeteryPreInfo(getContext(), orderId, beSpeakId));
            }
        });
    }

    @Override
    public void initData() {
        mUserSex1.initSpinner(SelectDictCode.PERSONNEL_SEX);
        mUserSex2.initSpinner(SelectDictCode.PERSONNEL_SEX);
        mUserState1.initSpinner(SelectDictCode.DEAD_INFO_STATE);
        mUserState2.initSpinner(SelectDictCode.DEAD_INFO_STATE);
    }
}
