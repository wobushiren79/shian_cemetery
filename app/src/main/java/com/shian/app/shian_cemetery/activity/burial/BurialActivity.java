package com.shian.app.shian_cemetery.activity.burial;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.appenum.BaseTitleEnum;
import com.shian.app.shian_cemetery.appenum.BurialOrderStateEnum;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.fragment.OrderFragment;
import com.shian.app.shian_cemetery.http.MHttpManagerFactory;
import com.shian.app.shian_cemetery.http.base.FileHttpResponseHandler;
import com.shian.app.shian_cemetery.http.base.HttpResponseHandler;
import com.shian.app.shian_cemetery.http.model.BurialDeadInfoModel;
import com.shian.app.shian_cemetery.http.model.BurialInfoModel;
import com.shian.app.shian_cemetery.http.model.BurialLocationModel;
import com.shian.app.shian_cemetery.http.model.BurialRecord;
import com.shian.app.shian_cemetery.http.params.HpBurialIdParams;
import com.shian.app.shian_cemetery.http.params.HpSaveBurialDataParams;
import com.shian.app.shian_cemetery.http.result.HrGetBurialDetails;
import com.shian.app.shian_cemetery.http.result.HrUploadFile;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.staticdata.SelectData;
import com.shian.app.shian_cemetery.tools.TimeUtils;
import com.shian.app.shian_cemetery.tools.ToastUtils;
import com.shian.app.shian_cemetery.tools.Utils;
import com.shian.app.shian_cemetery.view.dataview.burial.EditLayout;
import com.shian.app.shian_cemetery.view.dataview.burial.SpinnerLayout;
import com.shian.app.shian_cemetery.view.dialog.CustomDialog;
import com.shian.app.shian_cemetery.view.dialog.SignDialog;
import com.shian.app.shian_cemetery.view.dataview.burial.TextReadLayout;

import okhttp3.Request;

public class BurialActivity extends BaseActivity {

    TextReadLayout mTRLocation;
    TextReadLayout mTRUserName;
    TextReadLayout mTRGraveId;
    TextReadLayout mTRBurialCardId;
    TextReadLayout mTRBurialTime;
    SpinnerLayout mSPBurialOdds;
    SpinnerLayout mSPBurialState;
    EditLayout mETRemark;
    LinearLayout mLLBurialodds;

    ImageView mIVSign;
    TextView mTVSubmit;
    Bitmap bitmapName;//签名
    private BurialRecord burialRecord;//安葬信息
    private BurialDeadInfoModel deadInfo;//死者信息
    private BurialLocationModel tombPosition;//安葬墓位
    private String fileName = "signFile";
    long buryRecordId = -1;
    long orderId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burial);
        setTitle("安葬", BaseTitleEnum.BACKNORMALTITLE.getTitleType());
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
        buryRecordId = getIntent().getLongExtra(IntentName.INTENT_BURYRECORDID, -1);
        initView();
        getData();

    }


    private void initView() {
        mTRLocation = (TextReadLayout) findViewById(R.id.tr_location);
        mTRUserName = (TextReadLayout) findViewById(R.id.tr_username);
        mTRGraveId = (TextReadLayout) findViewById(R.id.tr_graveid);
        mTRBurialCardId = (TextReadLayout) findViewById(R.id.tr_burialcardid);
        mIVSign = (ImageView) findViewById(R.id.iv_sign);
        mTRBurialTime = (TextReadLayout) findViewById(R.id.tr_time);
        mSPBurialOdds = (SpinnerLayout) findViewById(R.id.spinner_burialodds);
        mSPBurialState = (SpinnerLayout) findViewById(R.id.spinner_burialstate);
        mETRemark = (EditLayout) findViewById(R.id.et_remark);
        mTVSubmit = (TextView) findViewById(R.id.tv_submit);
        mLLBurialodds = (LinearLayout) findViewById(R.id.ll_burialodds);

        mIVSign.setOnClickListener(onClickListener);
        mTVSubmit.setOnClickListener(onClickListener);
        mSPBurialOdds.initSpinner(SelectData.BURIAL_ODDS);
        mSPBurialState.initSpinner(SelectData.BURIAL_STATE);
    }

    private void initData() {
        if (tombPosition != null) {
            StringBuilder location = new StringBuilder();
            String cemeteryName = tombPosition.getCemeteryName() + "\n";
            String tombName = tombPosition.getTombName() + "";
            String parkName = tombPosition.getParkName() + "";
            String rowName = tombPosition.getRow() + "排";
            String numName = tombPosition.getNum() + "号";
            location.append(cemeteryName);
            location.append(tombName);
            location.append(parkName);
            location.append(rowName);
            location.append(numName);
            mTRLocation.setData(location.toString());
        }
        if (burialRecord != null) {

            if (burialRecord.getBuryType() == BurialOrderStateEnum.singleburial.getCode()) {
                mLLBurialodds.setVisibility(View.VISIBLE);
            } else {
                mLLBurialodds.setVisibility(View.GONE);
            }

            if (burialRecord.getBuryDatePre() != 0)
                mTRBurialTime.setData(TimeUtils.formatTime(burialRecord.getBuryDatePre()));

            if (burialRecord.getBuryStatus() == 1) {
                ToastUtils.showLongToast(BurialActivity.this, "此订单已被操作");
                finish();
                return;
            }

            if (burialRecord.getBuryCardNo() != null) {
                mTRBurialCardId.setData(burialRecord.getBuryCardNo());
            }

            if (burialRecord.getBuryInfo().getTombCertificateNo() != null)
                mTRGraveId.setData(burialRecord.getBuryInfo().getTombCertificateNo() + "");

            String name = new String();
            if (burialRecord.getBuryInfo().getIsMultiBurial() == 0) {
                if (burialRecord.getBuryName() != null)
                    name = burialRecord.getBuryName();
            } else {
                if (burialRecord.getBuryName() != null && burialRecord.getBuryName() != null)
                    name = burialRecord.getBuryName();
            }
            mTRUserName.setData(name);
        }
        if (deadInfo != null) {
//            StringBuilder name = new StringBuilder();
//            if (deadInfo.getDeadmanOneName() != null && !deadInfo.getDeadmanOneName().isEmpty()) {
//                name.append(deadInfo.getDeadmanOneName());
//            }
//            if (deadInfo.getDeadmanTwoName() != null && !deadInfo.getDeadmanTwoName().isEmpty()) {
//                name.append(" | " + deadInfo.getDeadmanTwoName());
//            }
//            mTRUserName.setData(name.toString());
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVSign) {
                signName();
            } else if (v == mTVSubmit) {
                saveData();
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

    /**
     * 获取数据
     */
    private void getData() {
        HpBurialIdParams params = new HpBurialIdParams();
        params.setContent(buryRecordId);
        MHttpManagerFactory.getAccountManager().getBurialDetails(this, params, new HttpResponseHandler<HrGetBurialDetails>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetBurialDetails result) {
                burialRecord = result.getBuryRecord();
                deadInfo = result.getDeadInfo();
                tombPosition = result.getTombPosition();
                initData();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * 保存数据
     */
    private void saveData() {
        if (mTRBurialCardId.getData() == null || mTRBurialCardId.getData().equals("")) {
            ToastUtils.showShortToast(BurialActivity.this, "没有安葬卡编号，请联系财务");
            return;
        }
        if (bitmapName == null) {
            ToastUtils.showShortToast(BurialActivity.this, "还没有签名");
            return;
        }
        String path = Utils.savePic(bitmapName);
        uploadFile(mIVSign, fileName, path);
    }


    private void uploadFile(final ImageView ib, final String fileName, String path) {
        final CustomDialog dialog = new CustomDialog(BurialActivity.this);
        MHttpManagerFactory.getFileManager().upLoadFile(BurialActivity.this, fileName, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        String fileUrl = (String) t.getNameMap().get(fileName);
                        dialog.cancel();
                        saveBurialData(fileUrl);
                    }

                    @Override
                    public void onStart() {
                        dialog.show();
                    }


                    @Override
                    public void onError(String message) {
                        ToastUtils.showShortToast(BurialActivity.this, "上传签名图片失败");
                        dialog.cancel();
                    }

                    @Override
                    public void onProgress(long total, float progress) {

                    }

                });
    }

    private void saveBurialData(final String fileUrl) {
        HpSaveBurialDataParams params = new HpSaveBurialDataParams();
        params.setBuryRecordId(buryRecordId);
        params.setRemark(mETRemark.getData());
        params.setOrderId(orderId);
        params.setSignFileIds(fileUrl);

        params.setDetail(mSPBurialState.getData());

        if (burialRecord.getBuryType() == BurialOrderStateEnum.singleburial.getCode()) {
            params.setBuryRate(mSPBurialOdds.getData());
        } else {

        }
//        if (mTRBurialCardId.getData().equals(""))
//            params.setBuryCardNo(mTRBurialCardId.getData());
        MHttpManagerFactory.getAccountManager().saveBurialData(BurialActivity.this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.showShortToast(BurialActivity.this, "提交数据成功");
                OrderFragment.isRefesh = true;
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.showShortToast(BurialActivity.this, "提交数据失败");
            }
        });
    }
}
