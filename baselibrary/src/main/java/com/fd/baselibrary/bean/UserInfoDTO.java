package com.fd.baselibrary.bean;

import com.fd.baselibrary.baseBean.UserBean;

import java.io.Serializable;
import java.util.List;

public class UserInfoDTO implements Serializable {
    private Integer id;
    private String password;
    private String account;
    private String name;
    private Integer sex;
    private String mobile;
    private Integer enable;
    private Object status;
    private String headImgUrl;
    private Integer headImgId;
    private Integer userType;
    private Object createTime;
    private Object createUserId;
    private Object updateTime;
    private Object updateUserId;
    private Object deleted;
    private Object userAlise;
    private RoleInfoDTO roleInfo;
    private GroupInfoDTO groupInfo;
    private List<InsideListDTO> insideList;
    private Object outsideInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Integer getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(Integer headImgId) {
        this.headImgId = headImgId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Object createUserId) {
        this.createUserId = createUserId;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Object updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public Object getUserAlise() {
        return userAlise;
    }

    public void setUserAlise(Object userAlise) {
        this.userAlise = userAlise;
    }

    public RoleInfoDTO getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfoDTO roleInfo) {
        this.roleInfo = roleInfo;
    }

    public GroupInfoDTO getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfoDTO groupInfo) {
        this.groupInfo = groupInfo;
    }

    public List<InsideListDTO> getInsideList() {
        return insideList;
    }

    public void setInsideList(List<InsideListDTO> insideList) {
        this.insideList = insideList;
    }

    public Object getOutsideInfo() {
        return outsideInfo;
    }

    public void setOutsideInfo(Object outsideInfo) {
        this.outsideInfo = outsideInfo;
    }
}
