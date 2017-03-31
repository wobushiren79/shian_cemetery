package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrator on 2017/3/31.
 */

public enum BaseTitleEnum {
    NORMALTITLE(1);

    private int titleType;

    BaseTitleEnum(int titleType) {
        this.titleType = titleType;
    }

    public int getTitleType() {
        return titleType;
    }

    public void setTitleType(int titleType) {
        this.titleType = titleType;
    }
}
