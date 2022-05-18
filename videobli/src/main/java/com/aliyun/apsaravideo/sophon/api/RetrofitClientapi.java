package com.aliyun.apsaravideo.sophon.api;

import com.fd.baselibrary.api.RetrofitClientJsonObject;
import com.fd.baselibrary.api.RetrofitClientform;

public class RetrofitClientapi extends RetrofitClientJsonObject {
    public static <T> T getAPIService(Class<T> service) {
        return getInstance().create(service);
    }

    public static TestService getTestService() {
        return getAPIService(TestService.class);
    }
}
