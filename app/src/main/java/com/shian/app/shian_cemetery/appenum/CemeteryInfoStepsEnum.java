package com.shian.app.shian_cemetery.appenum;

/**
 * Created by Administrato
 */

public enum CemeteryInfoStepsEnum {
    UNFILL(0, "未填写信息"),
    FILLPRE(1, "填写了购墓订单"),
    FILLDEADMAN(2, "填写了使用者信息"),
    FILLAGENTMAN(3, "3填写了经办人信息");

    private int code;
    private String name;

    CemeteryInfoStepsEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
