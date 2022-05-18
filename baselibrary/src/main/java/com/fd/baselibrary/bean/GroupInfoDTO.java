package com.fd.baselibrary.bean;

import java.io.Serializable;

public class GroupInfoDTO implements Serializable {
    private Integer id;
    private Integer parentId;
    private String parentIds;
    private String name;
    private Object enable;
    private Object description;
    private Object createTime;
    private Object createUserId;
    private Object updateTime;
    private Object updateUserId;
    private Object deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getEnable() {
        return enable;
    }

    public void setEnable(Object enable) {
        this.enable = enable;
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
}
