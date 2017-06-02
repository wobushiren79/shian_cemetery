package com.shian.app.shian_cemetery.activity.php;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.SystemTypeEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.phpparams.HpIdeaFeedBackSaveParams;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.ToastUtils;

import okhttp3.Request;


public class IdeaFeedbackActivity extends BaseActivity {
    EditText mEditText;
    TextView mBTSubmit;
    TextView mTVTextNum;


    String[] UserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_feedback);
        UserInfo = getIntent().getStringArrayExtra(IntentName.INTENT_FRAGMENT_USERINFO);
        setTitle("意见反馈", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.edit);
        mBTSubmit = (TextView) findViewById(R.id.bt_submit);
        mTVTextNum = (TextView) findViewById(R.id.tv_textnum);

        mBTSubmit.setOnClickListener(onClickListener);
        mEditText.addTextChangedListener(textWatcher);


        changeNum(0);
    }

    TextWatcher textWatcher = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            changeNum(s.length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void changeNum(int num) {
        mTVTextNum.setText(num + "/" + 100);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTSubmit) {
                submit();
            }
        }
    };


    private void submit() {
        if (mEditText.getText().toString().equals("")) {
            ToastUtils.showShortToast(IdeaFeedbackActivity.this, "还没有填写反馈信息");
            return;
        }
        HpIdeaFeedBackSaveParams params = new HpIdeaFeedBackSaveParams();
        params.setUser(UserInfo[0]);
        params.setTel(UserInfo[1]);
        params.setContent(mEditText.getText().toString());
        params.setUserType(SystemTypeEnum.cemetery.getCode());
        MHttpManagerFactory.getPHPManager().setOpinion(IdeaFeedbackActivity.this, params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.showShortToast(IdeaFeedbackActivity.this, "提交成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(IdeaFeedbackActivity.this, "提交失败");
            }
        }, true);
    }
}
