package com.shian.app.shian_cemetery.http.model;

/**
 * Created by zm.
 */

public class BurialRecord {

    /**
     * ashesCarry : 1
     * ashesDate : 1494012840000
     * ashesLocation : 接运地点
     * buryCardNo : 0021
     * buryDatePre : 1495057140000
     * buryInfo : {"buryInfoId":49,"detail":"安葬详情","isMultiBurial":0,"stoneStatus":0,"tombCertificateHandleAt":1495441082000,"tombCertificateNo":"121345465","updatedAt":1495423119000,"updatedBy":12}
     * buryName : 丽丝
     * buryStatus : 0
     * detail : 安葬详情
     * familyMemberPhone : 2343
     * familyMemberSign : 地方
     * remark : 填制碑文备注
     * stoneCarveStatus : 1
     * stoneFileFinishDate : 1495165856000
     * stoneFileIds : 7bd4d6b5afde4dfb8d80cc23c3cbda20.jpg
     * stoneFileSetDate : 1495100546000
     * updatedAt : 1495423119000
     * updatedBy : 12
     */
    private int buryType;//安葬状态  0安葬   1合葬
    private long buryRecordId;
    private int ashesCarry;
    private String ashesDate;
    private String ashesLocation;
    private String buryCardNo;
    private String buryDatePre;
    private BuryInfoBean buryInfo;
    private String buryName;
    private int buryStatus;
    /**
     * 安葬详情
     */
    private String detail;
    private String familyMemberPhone;
    private String familyMemberSign;
    private String remark;
    private int stoneCarveStatus;
    private String stoneFileFinishDate;
    /**
     * 立碑照片
     */
    private String stoneFileIds;
    private String stoneFileSetDate;
    private String updatedAt;
    private int updatedBy;
    /**
     * 客户签名文件id
     */
    private String signFileId;


    public String getAshesDate() {
        return ashesDate;
    }

    public void setAshesDate(String ashesDate) {
        this.ashesDate = ashesDate;
    }

    public String getBuryDatePre() {
        return buryDatePre;
    }

    public void setBuryDatePre(String buryDatePre) {
        this.buryDatePre = buryDatePre;
    }

    public String getStoneFileFinishDate() {
        return stoneFileFinishDate;
    }

    public void setStoneFileFinishDate(String stoneFileFinishDate) {
        this.stoneFileFinishDate = stoneFileFinishDate;
    }

    public String getStoneFileSetDate() {
        return stoneFileSetDate;
    }

    public void setStoneFileSetDate(String stoneFileSetDate) {
        this.stoneFileSetDate = stoneFileSetDate;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getBuryType() {
        return buryType;
    }

    public void setBuryType(int buryType) {
        this.buryType = buryType;
    }

    public long getBuryRecordId() {
        return buryRecordId;
    }

    public void setBuryRecordId(long buryRecordId) {
        this.buryRecordId = buryRecordId;
    }

    public String getSignFileId() {
        return signFileId;
    }

    public void setSignFileId(String signFileId) {
        this.signFileId = signFileId;
    }

    public int getAshesCarry() {
        return ashesCarry;
    }

    public void setAshesCarry(int ashesCarry) {
        this.ashesCarry = ashesCarry;
    }



    public String getAshesLocation() {
        return ashesLocation;
    }

    public void setAshesLocation(String ashesLocation) {
        this.ashesLocation = ashesLocation;
    }

    public String getBuryCardNo() {
        return buryCardNo;
    }

    public void setBuryCardNo(String buryCardNo) {
        this.buryCardNo = buryCardNo;
    }



    public BuryInfoBean getBuryInfo() {
        return buryInfo;
    }

    public void setBuryInfo(BuryInfoBean buryInfo) {
        this.buryInfo = buryInfo;
    }

    public String getBuryName() {
        return buryName;
    }

    public void setBuryName(String buryName) {
        this.buryName = buryName;
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

    public String getFamilyMemberPhone() {
        return familyMemberPhone;
    }

    public void setFamilyMemberPhone(String familyMemberPhone) {
        this.familyMemberPhone = familyMemberPhone;
    }

    public String getFamilyMemberSign() {
        return familyMemberSign;
    }

    public void setFamilyMemberSign(String familyMemberSign) {
        this.familyMemberSign = familyMemberSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStoneCarveStatus() {
        return stoneCarveStatus;
    }

    public void setStoneCarveStatus(int stoneCarveStatus) {
        this.stoneCarveStatus = stoneCarveStatus;
    }



    public String getStoneFileIds() {
        return stoneFileIds;
    }

    public void setStoneFileIds(String stoneFileIds) {
        this.stoneFileIds = stoneFileIds;
    }




    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public static class BuryInfoBean {
        /**
         * buryInfoId : 49
         * detail : 安葬详情
         * isMultiBurial : 0
         * stoneStatus : 0
         * tombCertificateHandleAt : 1495441082000
         * tombCertificateNo : 121345465
         * updatedAt : 1495423119000
         * updatedBy : 12
         */

        private int buryInfoId;
        /**
         * 安葬率 1表示墓穴不再合墓 0.5表示会合墓
         */
        private String buryRate;
        private String detail;
        /**
         * 是否以后合葬(0否1是)
         */
        private int isMultiBurial;
        /**
         * 立碑状态 值：0未立碑、1已立碑
         */
        private int stoneStatus;
        /**
         * 墓穴证办理日期
         */
        private String tombCertificateHandleAt;
        /**
         * 墓穴证号
         */
        private String tombCertificateNo;
        private String updatedAt;
        private int updatedBy;
        /**
         * 立碑备注
         */
        private String stoneRemark;

        /**
         * 预计立碑时间
         */
        private String stoneDatePre;
        /**
         * 实际立碑时间
         */
        private String stoneDateReal;
        /**
         * 墓位ID
         */
        private Long tombPositionId;

        public Long getTombPositionId() {
            return tombPositionId;
        }

        public void setTombPositionId(Long tombPositionId) {
            this.tombPositionId = tombPositionId;
        }

        public String getBuryRate() {
            return buryRate;
        }

        public void setBuryRate(String buryRate) {
            this.buryRate = buryRate;
        }

        public String getTombCertificateHandleAt() {
            return tombCertificateHandleAt;
        }

        public void setTombCertificateHandleAt(String tombCertificateHandleAt) {
            this.tombCertificateHandleAt = tombCertificateHandleAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getStoneDatePre() {
            return stoneDatePre;
        }

        public void setStoneDatePre(String stoneDatePre) {
            this.stoneDatePre = stoneDatePre;
        }

        public String getStoneDateReal() {
            return stoneDateReal;
        }

        public void setStoneDateReal(String stoneDateReal) {
            this.stoneDateReal = stoneDateReal;
        }

        public String getStoneRemark() {
            return stoneRemark;
        }

        public void setStoneRemark(String stoneRemark) {
            this.stoneRemark = stoneRemark;
        }

        public int getBuryInfoId() {
            return buryInfoId;
        }

        public void setBuryInfoId(int buryInfoId) {
            this.buryInfoId = buryInfoId;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getIsMultiBurial() {
            return isMultiBurial;
        }

        public void setIsMultiBurial(int isMultiBurial) {
            this.isMultiBurial = isMultiBurial;
        }

        public int getStoneStatus() {
            return stoneStatus;
        }

        public void setStoneStatus(int stoneStatus) {
            this.stoneStatus = stoneStatus;
        }


        public String getTombCertificateNo() {
            return tombCertificateNo;
        }

        public void setTombCertificateNo(String tombCertificateNo) {
            this.tombCertificateNo = tombCertificateNo;
        }


        public int getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(int updatedBy) {
            this.updatedBy = updatedBy;
        }
    }
}
