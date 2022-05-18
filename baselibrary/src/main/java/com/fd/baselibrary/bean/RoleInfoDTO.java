package com.fd.baselibrary.bean;

import java.io.Serializable;

public class RoleInfoDTO implements Serializable {
    private Integer id;
    private String name;
    private Object description;
    private Object createTime;
    private Object createUserId;
    private Object updateTime;
    private Object updateUserId;
    private Object deleted;
    private Object dataAuth;
    private Object webAuth;
    private Object appAuth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
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

    public Object getDataAuth() {
        return dataAuth;
    }

    public void setDataAuth(Object dataAuth) {
        this.dataAuth = dataAuth;
    }

    public Object getWebAuth() {
        return webAuth;
    }

    public void setWebAuth(Object webAuth) {
        this.webAuth = webAuth;
    }

    public Object getAppAuth() {
        return appAuth;
    }

    public void setAppAuth(Object appAuth) {
        this.appAuth = appAuth;
    }
}
