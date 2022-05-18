package com.aliyun.apsaravideo.sophon.bean;

import com.fd.baselibrary.bean.ImgListDTO;

import java.util.List;

public class ImageAsBean {
    private Integer id;
    private List<ImgListDTO> list;
    private Integer type;

    public ImageAsBean(Integer id, List<ImgListDTO> list, Integer type) {
        this.id = id;
        this.list = list;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ImgListDTO> getList() {
        return list;
    }

    public void setList(List<ImgListDTO> list) {
        this.list = list;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
