package com.shian.app.shian_cemetery.mvp.login.presenter.impl;


import com.shian.app.shian_cemetery.mvp.base.OnGetDataListener;
import com.shian.app.shian_cemetery.mvp.login.model.ISubSystemLoginModel;
import com.shian.app.shian_cemetery.mvp.login.model.impl.SubSystemLoginModelImpl;
import com.shian.app.shian_cemetery.mvp.login.presenter.ISubSystemLoginPresenter;
import com.shian.app.shian_cemetery.mvp.login.view.ISubSystemLoginView;
import com.shian.app.shian_cemetery.staticdata.AppData;

/**
 * Created by zm.
 */

public class SubSystemLoginPresenterImpl implements ISubSystemLoginPresenter {

    ISubSystemLoginView subSystemLoginView;
    ISubSystemLoginModel subSystemLoginModel;

    public SubSystemLoginPresenterImpl(ISubSystemLoginView subSystemLoginView) {
        this.subSystemLoginView = subSystemLoginView;
        subSystemLoginModel = new SubSystemLoginModelImpl();
    }

    @Override
    public void loginStoreSystem() {
        subSystemLoginModel.subSystemStoreLogin(subSystemLoginView.getContext(), AppData.System_Ki4so_Client_Ec, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                subSystemLoginView.loginGoodsSuccess();
            }

            @Override
            public void getDataFail(String msg) {
                subSystemLoginView.loginGoodsFail();
            }
        });
    }

    @Override
    public void loginOrderCenterSystem() {
        subSystemLoginModel.subSystemOrderCenterLogin(subSystemLoginView.getContext(), AppData.System_Ki4so_Client_Ec, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                subSystemLoginView.loginOrderCenterSuccess();
            }

            @Override
            public void getDataFail(String msg) {
                subSystemLoginView.loginOrderCenterFail();
            }
        });
    }

    @Override
    public void loginCemeterySystem() {
        subSystemLoginModel.subSystemCemeteryLogin(subSystemLoginView.getContext(), AppData.System_Ki4so_Client_Ec, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                subSystemLoginView.loginCemeterySuccess();
            }

            @Override
            public void getDataFail(String msg) {
                subSystemLoginView.loginCemeteryFail();
            }
        });
    }
}
