package com.fd.baselibrary.bean;

import java.io.Serializable;

public class CallListBean implements Serializable {
    private int callDuration;  //通话时长(时间戳毫秒)
    private int  callOutUserId;  //呼叫用户id
    private String callOutUserName;  //呼叫用户名称
    private String  endTime;  //结束时间
    private String startTime;  //开始时间
    private int  userGroupId;  //用户组id
    private String  userGroupName;  //用户组名称

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public int getCallOutUserId() {
        return callOutUserId;
    }

    public void setCallOutUserId(int callOutUserId) {
        this.callOutUserId = callOutUserId;
    }

    public String getCallOutUserName() {
        return callOutUserName;
    }

    public void setCallOutUserName(String callOutUserName) {
        this.callOutUserName = callOutUserName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }
}
