package com.fd.baselibrary.baseBean;

import com.fd.baselibrary.bean.UserInfoDTO;

import java.io.Serializable;
import java.util.List;


public class UserBean implements Serializable {


    private String tokenHead;
    private UserInfoDTO userInfo;
    private String token;

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
