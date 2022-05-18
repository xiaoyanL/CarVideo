package com.fd.baselibrary.baseBean;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户-卡券表
 */
public class UserCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty(value = "主键标识")
    private Integer usecPkid;

    //    @ApiModelProperty(value = "用户编码")
    private String userCode;

    //    @ApiModelProperty(value = "卡券编码")
    private String counCode;

    //    @ApiModelProperty(value = "状态（0未使用，1已使用，2已过期）")
    private String usecStas;

    //    @ApiModelProperty(value = "领取时间")
    private String creeTime;

    //    @ApiModelProperty(value = "领取人")
    private String creeUser;

    //    @ApiModelProperty(value = "使用时间")
    private String usaeTime;

    //    @ApiModelProperty(value = "过期时间")
    private String expeTime;

    //    @ApiModelProperty(value = "产品名称")
    private String protName;

    //    @ApiModelProperty(value = "商户编码")
    private String mertCode;


    public String getMertCode() {
        return mertCode;
    }

    public void setMertCode(String mertCode) {
        this.mertCode = mertCode;
    }

    public Integer getUsecPkid() {
        return usecPkid;
    }

    public UserCoupon setUsecPkid(Integer usecPkid) {
        this.usecPkid = usecPkid;
        return this;
    }

    public String getUserCode() {
        return userCode;
    }

    public UserCoupon setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getCounCode() {
        return counCode;
    }

    public UserCoupon setCounCode(String counCode) {
        this.counCode = counCode;
        return this;
    }

    public String getUsecStas() {
        return usecStas;
    }

    public UserCoupon setUsecStas(String usecStas) {
        this.usecStas = usecStas;
        return this;
    }

    public String getCreeTime() {
        return creeTime;
    }

    public UserCoupon setCreeTime(String creeTime) {
        this.creeTime = creeTime;
        return this;
    }

    public String getCreeUser() {
        return creeUser;
    }

    public UserCoupon setCreeUser(String creeUser) {
        this.creeUser = creeUser;
        return this;
    }

    public String getUsaeTime() {
        return usaeTime;
    }

    public UserCoupon setUsaeTime(String usaeTime) {
        this.usaeTime = usaeTime;
        return this;
    }

    public String getExpeTime() {
        return expeTime;
    }

    public UserCoupon setExpeTime(String expeTime) {
        this.expeTime = expeTime;
        return this;
    }

    public String getProtName() {
        return protName;
    }

    public UserCoupon setProtName(String protName) {
        this.protName = protName;
        return this;
    }

    @Override
    public String toString() {
        return "UserCoun{" +
                "usecPkid=" + usecPkid +
                ", userCode=" + userCode +
                ", counCode=" + counCode +
                ", usecStas=" + usecStas +
                ", creeTime=" + creeTime +
                ", creeUser=" + creeUser +
                ", usaeTime=" + usaeTime +
                ", expeTime=" + expeTime +
                ", protName=" + protName +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                usecPkid
                ,userCode
                ,counCode
                ,usecStas
                ,creeTime
                ,creeUser
                ,usaeTime
                ,expeTime
                ,protName
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCoupon that = (UserCoupon) o;
        return
                Objects.equals(usecPkid, that.usecPkid)
                        && Objects.equals(userCode, that.userCode)
                        && Objects.equals(counCode, that.counCode)
                        && Objects.equals(usecStas, that.usecStas)
                        && Objects.equals(creeTime, that.creeTime)
                        && Objects.equals(creeUser, that.creeUser)
                        && Objects.equals(usaeTime, that.usaeTime)
                        && Objects.equals(expeTime, that.expeTime)
                        && Objects.equals(protName, that.protName)
                ;
    }

}
