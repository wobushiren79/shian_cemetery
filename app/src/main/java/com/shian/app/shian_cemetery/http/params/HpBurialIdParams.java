package com.shian.app.shian_cemetery.http.params;

import com.shian.app.shian_cemetery.http.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/4/15.
 */

public class HpBurialIdParams extends BaseHttpParams {
    private long content;

    public long getContent() {
        return content;
    }

    public void setContent(long content) {
        this.content = content;
    }
}
