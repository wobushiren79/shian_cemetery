package com.shian.app.shian_cemetery.mvp.base;

import android.content.Context;

/**
 * Created by zm.
 */

public interface BaseMVPView {
    Context getContext();

    void showToast(String msg);
}
