package com.fd.baselibrary.bean;

import java.io.Serializable;

public class CheckUpdate implements Serializable {
    private Integer commonId;
    private Integer  id;
    private String url;
    private String  version;

    public Integer getCommonId() {
        return commonId;
    }

    public void setCommonId(Integer commonId) {
        this.commonId = commonId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
