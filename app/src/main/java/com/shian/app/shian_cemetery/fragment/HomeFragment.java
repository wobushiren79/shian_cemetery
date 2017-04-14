package com.shian.app.shian_cemetery.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.app.shian_cemetery.R;
import com.shian.app.shian_cemetery.base.BaseFragment;
import com.shian.app.shian_cemetery.view.customlayout.burialinfo.BurialInfoLayout;

public class HomeFragment extends BaseFragment {
    View view;
    BurialInfoLayout mBurialInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null, false);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {
        mBurialInfo = (BurialInfoLayout) view.findViewById(R.id.burialinfo);
    }
}
