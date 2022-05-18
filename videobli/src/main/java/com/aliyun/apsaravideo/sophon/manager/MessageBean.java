package com.aliyun.apsaravideo.sophon.manager;

import java.io.Serializable;


public class MessageBean implements Serializable {
    private Integer code;
    private String msg;
    private DataDTO data;

    public static class DataDTO implements  Serializable {
        private Integer calledId;
        private Integer channelId;
        private Integer type;
        private  String name;
        private  assessInfoVoBean assessInfoVo;

        public assessInfoVoBean getAssessInfoVo() {
            return assessInfoVo;
        }

        public void setAssessInfoVo(assessInfoVoBean assessInfoVo) {
            this.assessInfoVo = assessInfoVo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCalledId() {
            return calledId;
        }

        public void setCalledId(Integer calledId) {
            this.calledId = calledId;
        }

        public Integer getChannelId() {
            return channelId;
        }

        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
