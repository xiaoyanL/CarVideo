package com.fd.baselibrary.bean;

import java.io.Serializable;

public class ImgListDTO implements Serializable {
    private String diskPath;
    private Integer fileId;

    public String getDiskPath() {
        return diskPath;
    }

    public void setDiskPath(String diskPath) {
        this.diskPath = diskPath;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "ImgListDTO{" +
                "diskPath='" + diskPath + '\'' +
                ", fileId=" + fileId +
                '}';
    }
}
