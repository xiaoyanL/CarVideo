package com.aliyun.apsaravideo.sophon.manager;

import java.io.Serializable;

public class assessInfoVoBean implements Serializable {
    private String projectName;
    private String sectionName;
    private String specialName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }
}
