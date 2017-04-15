package com.shian.app.shian_cemetery.appenum;

/**
 * 预约-状态，值：1未接单、2已接单、3洽谈失败（未购墓）、4洽谈成功（购墓）、5服务结束、6再次洽谈、7未分配、8未指派
 * Created by Administrator
 */
public enum CemeteryBeSpeakStateEnum {
    unProcess(1, "未接单"),
    accepted(2, "已接单"),
    talkFail(3, "洽谈失败（未购墓）"),
    talkSuccess(4, "洽谈成功（购墓）"),
    serviceOver(5, "服务结束"),
    talkAgain(6, "再次洽谈"),
    undistributed(7, "未分配"),
    unassigned(8, "未指派");
    private Integer code;
    private String text;

    CemeteryBeSpeakStateEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
