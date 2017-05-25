package com.shian.app.shian_cemetery.http.model;

/**
 * Created by Administrator on 2017/4/14.
 */

public class BurialInfoModel {
    private long buryDatePre;// 客户预期安葬日期
    private int buryStatus;//安葬状态 0未安葬  1 已安葬
    private String detail;//落葬详情
    private long stoneDatePre;//客户预计立碑日期
    private int stoneStatus;//立碑状态  0未立碑， 1已立碑
    private String buryOneName;//安葬者1姓名
    private String buryTwoName;//安葬者2姓名
    private int isMultiBurial;//是否以后合葬(0否1是)



    /**
     * 墓穴证号
     */
    private String tombCertificateNo;
    /**
     * 墓穴证办理日期
     */
    private long tombCertificateHandleAt;
    /**
     * 安葬卡号
     */
    private String buryCardNo;

    private int stoneCarveStatus; //碑文刊刻状态 值：0未刊刻、1已刊刻
    private long stoneFileSetDate; //碑文填制日期
    private long stoneFleFinishDate; //碑文完成刊刻日期
    private String stoneRemark; //立碑备注

    public String getBuryOneName() {
        return buryOneName;
    }

    public void setBuryOneName(String buryOneName) {
        this.buryOneName = buryOneName;
    }

    public String getBuryTwoName() {
        return buryTwoName;
    }

    public void setBuryTwoName(String buryTwoName) {
        this.buryTwoName = buryTwoName;
    }

    public int getIsMultiBurial() {
        return isMultiBurial;
    }

    public void setIsMultiBurial(int isMultiBurial) {
        this.isMultiBurial = isMultiBurial;
    }

    public String getBuryCardNo() {
        return buryCardNo;
    }

    public void setBuryCardNo(String buryCardNo) {
        this.buryCardNo = buryCardNo;
    }

    public int getStoneCarveStatus() {
        return stoneCarveStatus;
    }

    public void setStoneCarveStatus(int stoneCarveStatus) {
        this.stoneCarveStatus = stoneCarveStatus;
    }

    public String getTombCertificateNo() {
        return tombCertificateNo;
    }

    public void setTombCertificateNo(String tombCertificateNo) {
        this.tombCertificateNo = tombCertificateNo;
    }

    public long getTombCertificateHandleAt() {
        return tombCertificateHandleAt;
    }

    public void setTombCertificateHandleAt(long tombCertificateHandleAt) {
        this.tombCertificateHandleAt = tombCertificateHandleAt;
    }

    public long getStoneFileSetDate() {
        return stoneFileSetDate;
    }

    public void setStoneFileSetDate(long stoneFileSetDate) {
        this.stoneFileSetDate = stoneFileSetDate;
    }

    public long getStoneFleFinishDate() {
        return stoneFleFinishDate;
    }

    public void setStoneFleFinishDate(long stoneFleFinishDate) {
        this.stoneFleFinishDate = stoneFleFinishDate;
    }

    public String getStoneRemark() {
        return stoneRemark;
    }

    public void setStoneRemark(String stoneRemark) {
        this.stoneRemark = stoneRemark;
    }

    public long getBuryDatePre() {
        return buryDatePre;
    }

    public void setBuryDatePre(long buryDatePre) {
        this.buryDatePre = buryDatePre;
    }

    public int getBuryStatus() {
        return buryStatus;
    }

    public void setBuryStatus(int buryStatus) {
        this.buryStatus = buryStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getStoneDatePre() {
        return stoneDatePre;
    }

    public void setStoneDatePre(long stoneDatePre) {
        this.stoneDatePre = stoneDatePre;
    }

    public int getStoneStatus() {
        return stoneStatus;
    }

    public void setStoneStatus(int stoneStatus) {
        this.stoneStatus = stoneStatus;
    }
}
