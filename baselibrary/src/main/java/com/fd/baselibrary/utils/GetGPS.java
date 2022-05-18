package com.fd.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.ServiceSettings;
import com.fd.BaseApplication;


public class GetGPS {
    private Context mContext;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    public static double longitude = 0;
    public static double latitude = 0;
    private boolean isRefreshAnim = false; // 是否在执行刷新动画
    private OnItemClickListener mOnItemClickListener = null;
    public GetGPS(Context context) {
        this.mContext = context;
    }

    public void startLocation() {
        //初始化client
        try {
            ServiceSettings.updatePrivacyShow(mContext,true,true);
            ServiceSettings.updatePrivacyAgree(mContext,true);

            locationClient = new AMapLocationClient(mContext);
            locationOption = getDefaultOption();
            //设置定位参数
            locationClient.setLocationOption(locationOption);
            // 设置定位监听
            locationClient.setLocationListener(locationListener);
            // 设置定位参数
            locationClient.setLocationOption(locationOption);
            // 启动定位
            locationClient.startLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopLocatio(){
        if (null!=locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        //如果网络可用就选择高精度
        if (isNetworkAvailable(mContext)) {
            mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
            mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        }
        //否则就选择仅设备模式
        else {
            mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
            mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        }
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(true);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            StringBuilder sb = new StringBuilder();
            if (null != location) {
                Log.e("location.getErrorCode()",location.getErrorCode() +"");
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    if (isRefreshAnim) {
                        Log.e("loc","获取实时位置成功!");
                    }

                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    Log.e("定位经纬度",longitude+"  "+latitude);
                    String address = location.getAddress();
                    locationSuccess(longitude, latitude, location, address);
                    //定位完成的时间
                    BaseApplication.getInstance().lone=longitude;
                    BaseApplication.getInstance().late=latitude;
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                    LocationFarile("获取失败", location);
                    if (isRefreshAnim) {
                        Log.e("loc","获取实时位置失败!");
                    }
                }
            } else {
                LocationFarile("获取失败", location);

                if (isRefreshAnim) {
                    Log.e("loc","获取实时位置失败!");
                }
            }
        }
    };

    private void LocationFarile(String count, AMapLocation location) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(0, 0, location, count, "");
        }
    }

    //dingw
    public void locationSuccess(double longitude, double latitude, AMapLocation location, String address) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(longitude, latitude, location, "", address);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(double longitude, double latitude, AMapLocation location, String count, String address);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * 检查当前网络是否可用
     *
     * @return
     */

    public boolean isNetworkAvailable(Context context)
    {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
