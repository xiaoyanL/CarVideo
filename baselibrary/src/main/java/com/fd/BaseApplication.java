package com.fd;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.fd.baselibrary.R;
import com.fd.baselibrary.api.ClassUtils;
import com.fd.baselibrary.api.IApplicationDelegate;

import com.fd.baselibrary.utils.GetGPS;
import com.fd.baselibrary.utils.UIUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.fd.baselibrary.utils.ActivityManager;
import com.fd.baselibrary.BuildConfig;

//import com.squareup.leakcanary.LeakCanary;
//import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

import static com.fd.baselibrary.utils.BaseSPManager.LANGUAGE;
import static me.jessyan.autosize.utils.LogUtils.isDebug;


/**
 * Created by MirkoWu on 2017/4/26 0026.
 */

public class BaseApplication extends Application  {

    private static BaseApplication instance;
    public  Double lone = 114.06751; //??????
    public  Double late = 22.534696; //??????
    private String city="??????";
    private String locationCity="??????";
    public int cityNumr = 440300;
    private static final String LANGUAGE_FILE = "language_file";
    public static final String ROOT_PACKAGE = "com.fd.hlt";
    private List<IApplicationDelegate> mAppDelegateList;
    public Locale locale;
    SharedPreferences sp ;
    public boolean islive;



    //static ?????????????????????????????????
    static {
        //???????????????Header?????????
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //layout.setPrimaryColorsId(android.R.color.transparent);//????????????????????????
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//???????????????Header???????????? ???????????????Header
            }
        });
        //???????????????Footer?????????
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //???????????????Footer???????????? BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    public static BaseApplication getInstance() {
        return instance;
    }

//    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sp = UIUtils.getContext().getSharedPreferences(LANGUAGE_FILE, Context.MODE_PRIVATE);

//        //??????????????????
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        mRefWatcher = BuildConfig.DEBUG ? LeakCanary.install(this) : RefWatcher.DISABLED;
//         mRefWatcher = RefWatcher.DISABLED;

        //????????????
        //AutoSizeConfig.getInstance().setDesignWidthInDp(360).setDesignHeightInDp(640);
        Logger.addLogAdapter(new AndroidLogAdapter());//logger
        registerActivityLifecycle();


        if (isDebug()) {           // ?????????????????????init??????????????????????????????init??????????????????
            ARouter.openLog();     // ????????????
            ARouter.openDebug();   // ??????????????????(?????????InstantRun?????????????????????????????????????????????????????????????????????,?????????????????????)
        }
        ARouter.init(this); // ????????????????????????Application????????????

        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }

        //?????????OkHttp
        OkHttpFinalConfiguration.Builder   builder = new OkHttpFinalConfiguration.Builder();
        builder.setTimeout(20000);
//        //  ?????????????????????????????????????????????
//        Stetho.initializeWithDefaults(this);
//        // ??????????????????????????????????????????(?????????okhttp3??????)
//        List<Interceptor> interceptors = new ArrayList<>();
//        interceptors.add(new StethoInterceptor());
//        builder.setNetworkInterceptors(interceptors);
        OkHttpFinal.getInstance().init(builder.build());

    }

//    public RefWatcher getRefWatcher() {
//        return mRefWatcher;
//    }

    /**
     * ????????????Activity
     */
    private void registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityManager.getInstance().add(activity);//???????????????

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManager.getInstance().remove(activity);//???????????????
//                mRefWatcher.watch(activity);
            }
        });
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }



}
