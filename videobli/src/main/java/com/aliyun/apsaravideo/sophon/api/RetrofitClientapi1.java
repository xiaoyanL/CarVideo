package com.aliyun.apsaravideo.sophon.api;


import com.fd.baselibrary.api.RetrofitClient1;

public class RetrofitClientapi1 extends RetrofitClient1 {
    public static <T> T getAPIService(Class<T> service) {
        return getInstance().create(service);
    }

    public static TestService getTestService() {
        return getAPIService(TestService.class);
    }

}
