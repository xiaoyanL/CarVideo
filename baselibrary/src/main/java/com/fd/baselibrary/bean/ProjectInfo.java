package com.fd.baselibrary.bean;

import java.util.List;

public class ProjectInfo {
    private List<AssessInfoListDTO> assessInfoList;
    private BaseInfoDTO basicInfo;
    private List<ImgListDTO>imgList;
    private String message;


    public List<AssessInfoListDTO> getAssessInfoList() {
        return assessInfoList;
    }

    public void setAssessInfoList(List<AssessInfoListDTO> assessInfoList) {
        this.assessInfoList = assessInfoList;
    }

    public BaseInfoDTO getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BaseInfoDTO basicInfo) {
        this.basicInfo = basicInfo;
    }

    public List<ImgListDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgListDTO> imgList) {
        this.imgList = imgList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
