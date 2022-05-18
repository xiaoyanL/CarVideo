package com.fd.baselibrary.bean;

import java.io.Serializable;

public class InsideListDTO implements Serializable {
    private Integer id;
    private Integer rjCustomerId;
    private Integer rjUserId;
    private Object createTime;
    private Object createUserId;
    private Object updateTime;
    private Object updateUserId;
    private Object deleted;
    private String rjCustomerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRjCustomerId() {
        return rjCustomerId;
    }

    public void setRjCustomerId(Integer rjCustomerId) {
        this.rjCustomerId = rjCustomerId;
    }

    public Integer getRjUserId() {
        return rjUserId;
    }

    public void setRjUserId(Integer rjUserId) {
        this.rjUserId = rjUserId;
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

    public String getRjCustomerName() {
        return rjCustomerName;
    }

    public void setRjCustomerName(String rjCustomerName) {
        this.rjCustomerName = rjCustomerName;
    }
}
