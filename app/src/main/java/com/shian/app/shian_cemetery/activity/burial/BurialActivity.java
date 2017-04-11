package com.shian.app.shian_cemetery.activity.burial;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.view.dialog.SignDialog;
import com.shian.app.shian_cemetery.view.dataview.burial.TextReadLayout;

public class BurialActivity extends BaseActivity {

    TextReadLayout mTRLocation;
    TextReadLayout mTRUserName;
    TextReadLayout mTRGraveId;
    TextReadLayout mTRBurialCardId;

    ImageView mIVSign;

    Bitmap bitmapName;//签名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burial);
        setTitle("安葬", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        initView();
        initData();
    }


    private void initView() {
        mTRLocation = (TextReadLayout) findViewById(R.id.tr_location);
        mTRUserName = (TextReadLayout) findViewById(R.id.tr_username);
        mTRGraveId = (TextReadLayout) findViewById(R.id.tr_graveid);
        mTRBurialCardId = (TextReadLayout) findViewById(R.id.tr_burialcardid);
        mIVSign = (ImageView) findViewById(R.id.iv_sign);

        mIVSign.setOnClickListener(onClickListener);
    }

    private void initData() {
        mTRLocation.setData("测试");
        mTRUserName.setData("测试");
        mTRGraveId.setData("测试");
        mTRBurialCardId.setData("测试");
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVSign) {
                signName();
            }
        }
    };

    /**
     * 签名
     */
    private void signName() {
        SignDialog dialog = new SignDialog(BurialActivity.this, R.style.CustomDialog);
        dialog.setCallBack(new SignDialog.CallBack() {
            @Override
            public void signComplete(Bitmap bitmapName) {
                mIVSign.setImageBitmap(bitmapName);
                BurialActivity.this.bitmapName = bitmapName;
            }
        });
        dialog.show();
    }
}
