package com.fd.baselibrary.bean;

import java.io.Serializable;
import java.util.List;

public class ListDTO  implements Serializable {
    private Integer abandoned;
    private Integer areaOverground;
    private Integer areaUnderground;
    private String completedRecordTime;
    private List<ConstructionUnitListDTO> constructionUnitList;
    private String createTime;
    private Integer createUserId;
    private Integer deleted;
    private String deliveryTime;
    private Integer deliveryType;
    private Integer id;
    private Integer itemStatus;
    private Integer latitude;
    private String location;
    private Integer longitude;
    private String projectChargeMobile;
    private String projectChargePerson;
    private String projectCode;
    private String projectName;
    private String projectSectionName;
    private Integer rjAreaId;
    private Integer rjCityId;
    private Integer rjCustomerId;
    private Integer sectionArea;
    private String sectionName;
    private Integer sectionType;
    private List<SupervisingUnitListDTO> supervisingUnitList;
    private String updateTime;
    private Integer updateUserId;

    public Integer getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(Integer abandoned) {
        this.abandoned = abandoned;
    }

    public Integer getAreaOverground() {
        return areaOverground;
    }

    public void setAreaOverground(Integer areaOverground) {
        this.areaOverground = areaOverground;
    }

    public Integer getAreaUnderground() {
        return areaUnderground;
    }

    public void setAreaUnderground(Integer areaUnderground) {
        this.areaUnderground = areaUnderground;
    }

    public String getCompletedRecordTime() {
        return completedRecordTime;
    }

    public void setCompletedRecordTime(String completedRecordTime) {
        this.completedRecordTime = completedRecordTime;
    }

    public List<ConstructionUnitListDTO> getConstructionUnitList() {
        return constructionUnitList;
    }

    public void setConstructionUnitList(List<ConstructionUnitListDTO> constructionUnitList) {
        this.constructionUnitList = constructionUnitList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public String getProjectChargeMobile() {
        return projectChargeMobile;
    }

    public void setProjectChargeMobile(String projectChargeMobile) {
        this.projectChargeMobile = projectChargeMobile;
    }

    public String getProjectChargePerson() {
        return projectChargePerson;
    }

    public void setProjectChargePerson(String projectChargePerson) {
        this.projectChargePerson = projectChargePerson;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectSectionName() {
        return projectSectionName;
    }

    public void setProjectSectionName(String projectSectionName) {
        this.projectSectionName = projectSectionName;
    }

    public Integer getRjAreaId() {
        return rjAreaId;
    }

    public void setRjAreaId(Integer rjAreaId) {
        this.rjAreaId = rjAreaId;
    }

    public Integer getRjCityId() {
        return rjCityId;
    }

    public void setRjCityId(Integer rjCityId) {
        this.rjCityId = rjCityId;
    }

    public Integer getRjCustomerId() {
        return rjCustomerId;
    }

    public void setRjCustomerId(Integer rjCustomerId) {
        this.rjCustomerId = rjCustomerId;
    }

    public Integer getSectionArea() {
        return sectionArea;
    }

    public void setSectionArea(Integer sectionArea) {
        this.sectionArea = sectionArea;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getSectionType() {
        return sectionType;
    }

    public void setSectionType(Integer sectionType) {
        this.sectionType = sectionType;
    }

    public List<SupervisingUnitListDTO> getSupervisingUnitList() {
        return supervisingUnitList;
    }

    public void setSupervisingUnitList(List<SupervisingUnitListDTO> supervisingUnitList) {
        this.supervisingUnitList = supervisingUnitList;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}
