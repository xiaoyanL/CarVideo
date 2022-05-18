package com.fd.baselibrary.bean;

import java.io.Serializable;
import java.util.List;

public class ProjectImg implements Serializable {
    private List<ImgListDTO> list;
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private Integer totalPage;

    public List<ImgListDTO> getList() {
        return list;
    }

    public void setList(List<ImgListDTO> list) {
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
