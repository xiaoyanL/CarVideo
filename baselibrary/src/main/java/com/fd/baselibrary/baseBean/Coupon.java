package com.fd.baselibrary.baseBean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 卡券表
 */
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @ApiModelProperty(value = "主键标识")
    private Integer counPkid;

    //    @ApiModelProperty(value = "优惠券编码")
    private String counCode;

    //    @ApiModelProperty(value = "商户类型")
    private String mertType;

    //    @ApiModelProperty(value = "商户编码")
    private String mertCode;

    //    @ApiModelProperty(value = "优惠券类型（1现金券，2折扣券）")
    private String counType;

    //    @ApiModelProperty(value = "优惠值（优惠券必须≥1，且是1的正整数倍，折扣券必须正整数，1的整数倍且最高不可超过100）")
    private String counVale;

    //    @ApiModelProperty(value = "领取方式（1免费领取，2支付购买）")
    private String gainMetd;

    //    @ApiModelProperty(value = "卡券数量（0无限，）")
    private String counTotl;

    //    @ApiModelProperty(value = "每人限领（0无限，）")
    private String counLimt;

    //    @ApiModelProperty(value = "使用门槛（1的整数倍金额）")
    private String counThrd;

    //    @ApiModelProperty(value = "产品范围（0所有，1部分）")
    private String counScoe;

    //    @ApiModelProperty(value = "状态（0可用，1失效）")
    private String counStas;

    //    @ApiModelProperty(value = "创建时间")
    private String creeTime;

    //    @ApiModelProperty(value = "创建人")
    private String creeUser;

    //    @ApiModelProperty(value = "修改时间")
    private String modyTime;

    //    @ApiModelProperty(value = "修改人")
    private String modyUser;

    //    @ApiModelProperty(value = "有效期起始时间")
    private String statTime;

    //    @ApiModelProperty(value = "有效期结束时间")
    private String endTime;

    //    @ApiModelProperty(value = "用户优惠券集合")
    private List<UserCoupon> userCoupons;

    //    @ApiModelProperty(value = "是否可以使用(0可以使用，1不可使用)")
    private String useFlag;

    public String getMertType() {
        return mertType;
    }

    public void setMertType(String mertType) {
        this.mertType = mertType;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }

    public List<UserCoupon> getUserCoupons() {
        return userCoupons;
    }

    public void setUserCoupons(List<UserCoupon> userCoupons) {
        this.userCoupons = userCoupons;
    }

    public Integer getCounPkid() {
        return counPkid;
    }

    public Coupon setCounPkid(Integer counPkid) {
        this.counPkid = counPkid;
        return this;
    }

    public String getCounCode() {
        return counCode;
    }

    public Coupon setCounCode(String counCode) {
        this.counCode = counCode;
        return this;
    }

    public String getMertCode() {
        return mertCode;
    }

    public Coupon setMertCode(String mertCode) {
        this.mertCode = mertCode;
        return this;
    }

    public String getCounType() {
        return counType;
    }

    public Coupon setCounType(String counType) {
        this.counType = counType;
        return this;
    }

    public String getCounVale() {
        return counVale;
    }

    public Coupon setCounVale(String counVale) {
        this.counVale = counVale;
        return this;
    }

    public String getGainMetd() {
        return gainMetd;
    }

    public Coupon setGainMetd(String gainMetd) {
        this.gainMetd = gainMetd;
        return this;
    }

    public String getCounTotl() {
        return counTotl;
    }

    public Coupon setCounTotl(String counTotl) {
        this.counTotl = counTotl;
        return this;
    }

    public String getCounLimt() {
        return counLimt;
    }

    public Coupon setCounLimt(String counLimt) {
        this.counLimt = counLimt;
        return this;
    }

    public String getCounThrd() {
        return counThrd;
    }

    public Coupon setCounThrd(String counThrd) {
        this.counThrd = counThrd;
        return this;
    }

    public String getCounScoe() {
        return counScoe;
    }

    public Coupon setCounScoe(String counScoe) {
        this.counScoe = counScoe;
        return this;
    }

    public String getCounStas() {
        return counStas;
    }

    public Coupon setCounStas(String counStas) {
        this.counStas = counStas;
        return this;
    }

    public String getCreeTime() {
        return creeTime;
    }

    public Coupon setCreeTime(String creeTime) {
        this.creeTime = creeTime;
        return this;
    }

    public String getCreeUser() {
        return creeUser;
    }

    public Coupon setCreeUser(String creeUser) {
        this.creeUser = creeUser;
        return this;
    }

    public String getModyTime() {
        return modyTime;
    }

    public Coupon setModyTime(String modyTime) {
        this.modyTime = modyTime;
        return this;
    }

    public String getModyUser() {
        return modyUser;
    }

    public Coupon setModyUser(String modyUser) {
        this.modyUser = modyUser;
        return this;
    }

    public String getStatTime() {
        return statTime;
    }

    public Coupon setStatTime(String statTime) {
        this.statTime = statTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public Coupon setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    @Override
    public String toString() {
        return "Coun{" +
                "counPkid=" + counPkid +
                ", counCode=" + counCode +
                ", mertCode=" + mertCode +
                ", counType=" + counType +
                ", counVale=" + counVale +
                ", gainMetd=" + gainMetd +
                ", counTotl=" + counTotl +
                ", counLimt=" + counLimt +
                ", counThrd=" + counThrd +
                ", counScoe=" + counScoe +
                ", counStas=" + counStas +
                ", creeTime=" + creeTime +
                ", creeUser=" + creeUser +
                ", modyTime=" + modyTime +
                ", modyUser=" + modyUser +
                ", statTime=" + statTime +
                ", endTime=" + endTime +
                "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                counPkid
                ,counCode
                ,mertCode
                ,counType
                ,counVale
                ,gainMetd
                ,counTotl
                ,counLimt
                ,counThrd
                ,counScoe
                ,counStas
                ,creeTime
                ,creeUser
                ,modyTime
                ,modyUser
                ,statTime
                ,endTime
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon that = (Coupon) o;
        return
                Objects.equals(counPkid, that.counPkid)
                        && Objects.equals(counCode, that.counCode)
                        && Objects.equals(mertCode, that.mertCode)
                        && Objects.equals(counType, that.counType)
                        && Objects.equals(counVale, that.counVale)
                        && Objects.equals(gainMetd, that.gainMetd)
                        && Objects.equals(counTotl, that.counTotl)
                        && Objects.equals(counLimt, that.counLimt)
                        && Objects.equals(counThrd, that.counThrd)
                        && Objects.equals(counScoe, that.counScoe)
                        && Objects.equals(counStas, that.counStas)
                        && Objects.equals(creeTime, that.creeTime)
                        && Objects.equals(creeUser, that.creeUser)
                        && Objects.equals(modyTime, that.modyTime)
                        && Objects.equals(modyUser, that.modyUser)
                        && Objects.equals(statTime, that.statTime)
                        && Objects.equals(endTime, that.endTime)
                ;
    }
}
