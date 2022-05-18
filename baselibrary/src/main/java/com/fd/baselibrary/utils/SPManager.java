package com.fd.baselibrary.utils;

import com.fd.baselibrary.base.HistoryBean;
import com.fd.baselibrary.baseBean.UserBean;

import java.util.HashMap;
import java.util.Map;

public class SPManager extends BaseSPManager {
    //访问网络时候的token
    public static final String USER_TOKEN = "user_token"; //token值
    public static final String USER_TOKEN_KEY = "token"; //token键
    public static final String USER_TOKEN_WEBKEY = "Webtoken"; //token键
    //访问网络的公共参数
    public static final String USER_PUBLIC_MAP = "user_public_map"; //
    public static final String USER_DATA = "user_data"; //用户实体
    public static final String USER_ISPASSWORD = "user_isPassword"; //是否记住密码
    public static final String USER_ISLOGIN = "user_islogin"; //是否自动登录


    public static String getUserToken() {
        return (String) SPUtil.get(USER_TOKEN,"");
    }
    public static void setUserToken(String login_cookie) {
        SPUtil.put(USER_TOKEN, login_cookie);
    }


    public static String getUserTokenKey() {
        return (String) SPUtil.get(USER_TOKEN_KEY,"Authorization");
    }
    public static void setUserTokenKey(String token) {
        SPUtil.put(USER_TOKEN_KEY, token);
    }


    public static String getUserWebTokenKey() {
        return (String) SPUtil.get(USER_TOKEN_WEBKEY,"Authorization");
    }
    public static void setUserWebTokenKey(String token) {
        SPUtil.put(USER_TOKEN_WEBKEY, token);
    }
    public static Map<String, String> getUserPublicMap() {
        Map<String, String> map=    (Map<String, String>) SPUtil.getSerializableObject(USER_PUBLIC_MAP);
        if (map==null){
            map=new HashMap<>();
        }
        return map;
    }
    public static void setUserPublicMap(Map<String, String> map) {
        SPUtil.putSerializableObject(USER_PUBLIC_MAP, map);
    }
    public static void setUserData(UserBean bean) {
        SPUtil.putSerializableObject(USER_DATA, bean);
    }

    public static UserBean getUserData() {
        return  SPUtil.getSerializableObject(USER_DATA);
    }


    public static void setIsPassword(boolean IsPassword) {
        SPUtil.put(USER_ISPASSWORD, IsPassword);
    }

    public static boolean getIsPassword() {
        return (boolean) SPUtil.get(USER_ISPASSWORD,false);
    }


    public static void setIsLogin(boolean IsLogin) {
        SPUtil.put(USER_ISLOGIN, IsLogin);
    }

    public static boolean getIsLogin() {
        return (boolean) SPUtil.get(USER_ISLOGIN,false);
    }
}
