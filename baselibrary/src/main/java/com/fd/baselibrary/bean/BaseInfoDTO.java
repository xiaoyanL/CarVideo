package com.fd.baselibrary.bean;

import java.io.Serializable;

public class BaseInfoDTO implements Serializable {
    private String areaName;
    private String cityName;
    private Integer id;
    private String projectSectionName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectSectionName() {
        return projectSectionName;
    }

    public void setProjectSectionName(String projectSectionName) {
        this.projectSectionName = projectSectionName;
    }
}
