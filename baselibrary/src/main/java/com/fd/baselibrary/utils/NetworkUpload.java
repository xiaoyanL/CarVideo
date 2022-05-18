package com.fd.baselibrary.utils;

import com.fd.baselibrary.network.Callback;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Headers;

public class NetworkUpload {
    public static void uploadbycode(RequestParams params, String url, String dataVale, Callback callback) {
        params.addFormDataPart("dataVale", dataVale);
        HttpRequest.post(url, params, new BaseHttpRequestCallback() {
            //这里只是网络请求成功了（也就是服务器返回JSON合法）没有没有分装具体的业务成功与失败，大家可以参考demo去分装自己公司业务请求成功与失败

            @Override
            public void onStart() {
                super.onStart();
                callback.onStart();
            }

            @Override
            public void onResponse(String response, Headers headers) {
                super.onResponse(response, headers);
                try {
                    callback.onSuccess(response);
                } catch (Exception e) {

                }
            }

            //请求失败（服务返回非法JSON、服务器异常、网络异常）
            @Override
            public void onFailure(int errorCode, String msg) {
                callback.onError(null);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                callback.onFinish();

            }
        });

    }
    public static void uploadbycode(RequestParams params, String url, Callback callback) {

//        String dataVale = "";
//        for (int i = 0; i < 4; i++) {
//            dataVale += String.valueOf((int) (Math.random() * 10));
//        }
//        params.addFormDataPart("dataVale", dataVale);

        HttpRequest.post(url, params, new BaseHttpRequestCallback() {
            //这里只是网络请求成功了（也就是服务器返回JSON合法）没有没有分装具体的业务成功与失败，大家可以参考demo去分装自己公司业务请求成功与失败

            @Override
            public void onStart() {
                super.onStart();
                callback.onStart();
            }

            @Override
            public void onResponse(String response, Headers headers) {
                super.onResponse(response, headers);
                try {
                    callback.onSuccess(response);
                } catch (Exception e) {

                }
            }

            //请求失败（服务返回非法JSON、服务器异常、网络异常）
            @Override
            public void onFailure(int errorCode, String msg) {
                callback.onError(null);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                callback.onFinish();

            }
        });



    }

}
