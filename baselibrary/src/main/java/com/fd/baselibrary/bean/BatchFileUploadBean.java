package com.fd.baselibrary.bean;

import java.io.Serializable;
import java.util.List;

public class BatchFileUploadBean implements Serializable {
    private Integer code;
    private String message;
    private Object errors;
    private List<FileUploadBeanDTO> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public List<FileUploadBeanDTO> getData() {
        return data;
    }

    public void setData(List<FileUploadBeanDTO> data) {
        this.data = data;
    }
}
