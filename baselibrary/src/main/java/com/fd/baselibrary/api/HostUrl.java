package com.fd.baselibrary.api;

public interface HostUrl {
//  String HOST_URL = "https://smart1.szridge.com/assessApi/";
//  String HOST_URL = "http://8.135.5.80:1086/assessApi/";
//    String HOST_URL = "http://192.168.0.149:1086/assessApi/";//本地
//  String HOST_URL = "https://rtc.mikao1688.com/assessApi/"; //外网
//  String HOST_URL = " https://smart1.szridge.com/assessApi/";//内外
  String IMAGE_URL = "https://smart-site-bucket.oss-cn-zhangjiakou.aliyuncs.com/";//图片

  String HOST_URL = "https://smart.szridge.com/";//生产


//    String Socket_URL = "ws://192.168.0.149:8084/ws/connect?type=1"; //本地
//  String Socket_URL = "wss://rtc.mikao1688.com/socket/ws/connect?type=1"; //外网
//  String Socket_URL = "wss://smart1.szridge.com/socket/ws/connect?type=1"; //内外
  String Socket_URL = "wss://smart.szridge.com/socket/ws/connect?type=1"; //生产



    public static final String SCREEN_OFF = "android.intent.action.SCREEN_OFF";
    public static final String HANDLEPHONEACTIVITY = "/voide/handlephoneactivity";


    public static final String LOGIN = "rjApp/login";//登录
    public static final String FORCELOGIN = "rjApp/forceLogin";//强制登录
    public static final String PROJECTLIST = "rjApp/project/list";//分页查询项目列表

    public static final String PROJECTINFO = "rjApp/project/survey";//查看项目概况信息

    public static final String PROJECTIMG = "rjApp/project/img/list";//分页查询项目图片

    public static final String PROJECTDELETEIMG = "rjApp/deleteFile";//删除图片

    public static final String BATCHFILEUPLOAD = HOST_URL + "rjApp/fileUpload";//批量文件上传

    public static final String CHECKCALLRECORD = HOST_URL + "rtcCallRecord/list";//查询通话记录

    public static final String CHECKUPDATE = HOST_URL + "rtcAppVersion/check";//检查新版本data: true最新版,false需要更新

    public static final String GETNEWVERSION = HOST_URL + "rtcAppVersion/getNewVersion";//获取最新版本
    public static final String TOKEN = HOST_URL + "rtc/app/token";//获取通话token

}
