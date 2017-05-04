package com.shian.app.shian_cemetery.view.dataview.cemetery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;


import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.activity.map.MapLocation;
import com.shian.app.shian_cemetery.base.BaseActivity;
import com.shian.app.shian_cemetery.staticdata.IntentName;
import com.shian.app.shian_cemetery.tools.CheckUtils;

import java.util.List;
import java.util.jar.Manifest;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


/**
 * Created by Administrator on 2017/3/17.
 */

public class MapSelectViewNormal extends BaseWriteView {
    View view;
    TextView mTVIsImportant;
    TextView mTVTitleName;
    AutoCompleteTextView mAutoTextView;
    ImageView mIVMapCheck;

    private ArrayAdapter<String> arrayAdapter;
    private int numView = 0;
    public static String THE_ACTION = "MapLocationData";

    public MapSelectViewNormal(Context context) {
        this(context, null);
    }

    public MapSelectViewNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.zhy_write_mapselect_normal, this);
        initView();
        initData();
    }

    private void initView() {
        mTVIsImportant = (TextView) view.findViewById(R.id.tv_important);
        mTVTitleName = (TextView) view.findViewById(R.id.tv_titlename);
        mAutoTextView = (AutoCompleteTextView) view.findViewById(R.id.auto_textview);
        mIVMapCheck = (ImageView) view.findViewById(R.id.iv_mapcheck);

        mIVMapCheck.setOnClickListener(onClickListener);
        mAutoTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutoTextView.showDropDown();
            }
        });
    }

    private void initData() {
        mTVTitleName.setText(titleName);
//        mETInput.setInputType(inputType);
        if (isImportant) {
            mTVIsImportant.setVisibility(VISIBLE);
        } else {
            mTVIsImportant.setVisibility(INVISIBLE);
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction(THE_ACTION);
        filter.setPriority(Integer.MAX_VALUE);
        getContext().registerReceiver(locationDataReceiver, filter);
    }

    public void initAutoTextView(String[] arr) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr);
        mAutoTextView.setAdapter(arrayAdapter);
    }

    public void initAutoTextView(List<String> arr) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arr);
        mAutoTextView.setAdapter(arrayAdapter);
    }

    public String getData() {
        return mAutoTextView.getText().toString();
    }

    public void setData(String location) {
        mAutoTextView.setText(location);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVMapCheck) {
                mapCheck();
            }
        }
    };

    /**
     * 通过地图得到地址
     */
    private void mapCheck() {
        boolean isPermission = CheckUtils.getPermissionToReadUserContacts
                (getContext(),
                        new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION,WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},
                        "获取地址需要权限",
                        BaseActivity.READ_LOCATION);
        if (isPermission) {
            Intent intent = new Intent(getContext(), MapLocation.class);
            intent.putExtra(IntentName.INTENT_LOCATION_NUMVIEW, numView);
            getContext().startActivity(intent);
        }
    }

    private BroadcastReceiver locationDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int num = intent.getIntExtra(IntentName.INTENT_LOCATION_NUMVIEW, 0);
            if (num == numView) {
                String location = intent.getStringExtra("location");
                mAutoTextView.setText(location);
            }
        }
    };


    public void setNumView(int numView) {
        this.numView = numView;
    }
}

