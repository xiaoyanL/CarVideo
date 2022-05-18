package com.aliyun.apsaravideo.sophon.manager;

public class IMMessageEvent {
    private String info;
    private String duration;
    private int flag;

    public IMMessageEvent(String info, int flag) {
        this.info = info;
        this.flag = flag;
    }

    public IMMessageEvent(String info, String duration, int flag) {
        this.info = info;
        this.duration = duration;
        this.flag = flag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
