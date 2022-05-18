package com.fd.baselibrary.bean;

import java.io.Serializable;

public class AssessInfoListDTO implements Serializable {
    private String assessTime;
    private Integer id;
    private Integer totalNum;
    private String versionDes;

    public String getAssessTime() {
        return assessTime;
    }

    public void setAssessTime(String assessTime) {
        this.assessTime = assessTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getVersionDes() {
        return versionDes;
    }

    public void setVersionDes(String versionDes) {
        this.versionDes = versionDes;
    }
}
