package com.fd.baselibrary.baseBean;

import java.util.List;

public class TokenBean {

    private String appId;
    private String channelId;
    private List<String> gslbList;
    private String nonce;
    private Integer timestamp;
    private String token;
    private Integer type;
    private String userId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<String > getGslbList() {
        return gslbList;
    }

    public void setGslbList(List<String> gslbList) {
        this.gslbList = gslbList;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
