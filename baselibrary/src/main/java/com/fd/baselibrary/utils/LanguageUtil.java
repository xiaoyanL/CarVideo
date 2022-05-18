package com.fd.baselibrary.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.fd.baselibrary.base.LanguageBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

/**
 * @author by DELL
 * @date on 2017/11/29
 * @describe
 */

public class LanguageUtil {
    /**
     * 保存SharedPreferences的文件名
     */
    private static final String LANGUAGE_FILE = "language_file";
    private static final String LANGUAGE = "language";

    /**
     * 应用语言
     *
     * @param context 此context必须和{@link #attachBaseContext(Context, Locale)} 中的context一致 ，否则切换会失效
     * @param locale
     */
    public static void switchLanguage(Context context, Locale locale) {
        //应用内配置语言
        Resources resources = context.getResources();//获得res资源对象
        Configuration config = resources.getConfiguration();//获得设置对象
        DisplayMetrics dm = resources.getDisplayMetrics();//获得屏幕参数：主要是分辨率，像素等。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        resources.updateConfiguration(config, dm);
    }

    public static Locale getLanguage(Context context) {
        //应用内配置语言
        Resources resources = context.getResources();//获得res资源对象
        Configuration config = resources.getConfiguration();//获得设置对象
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return config.getLocales().get(0);
        } else {
            return config.locale;
        }

    }

    /**
     * 获取对应的context
     *
     * @param context
     * @param locale
     * @return
     */
    public static Context attachBaseContext(Context context, Locale locale) {
        //8.0 updateConfiguration 失效  用createConfigurationContext 代替
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return createConfigurationResources(context, locale);
        } else {
            return context;
        }
    }


    /**
     * 8.0 配置语言方法
     *
     * @param context
     * @param locale
     * @return
     */
    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    /**
     * 更新Locale
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void updateLocale(Locale userLocale) {
        if (needUpdateLocale(userLocale)) {
            Context context = UIUtils.getContext().getApplicationContext();
            Configuration configuration = context.getResources().getConfiguration();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                configuration.setLocale(userLocale);
            } else {
                configuration.locale = userLocale;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration, displayMetrics);
            saveUserLocale(userLocale);
            EventBus.getDefault().post(new LanguageBean(userLocale));
        }
    }
    /**
     * 判断需不需要更新
     */
    private static boolean needUpdateLocale(Locale locale) {
        return locale != null && !getCurrentLocale().equals(locale);
    }
    /**
     * 获取当前的Locale
     */
    public static Locale getCurrentLocale() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
            locale = UIUtils.getContext().getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = UIUtils.getContext().getResources().getConfiguration().locale;
        }
        return locale;
    }

    /**
     * 获取用户设置的Locale
     */
  public   static Locale getUserLocale() {
        SharedPreferences sp = UIUtils.getContext().getSharedPreferences(LANGUAGE_FILE, Context.MODE_PRIVATE);
        String local = sp.getString(LANGUAGE, "");
        if (!TextUtils.isEmpty(local)) {
            return jsonToLocale(local);
        }
        return jsonToLocale(localeToJson(getCurrentLocale()));
    }

    /**
     * 保存用户设置的Locale
     */
    private static void saveUserLocale(Locale pUserLocale) {
        SharedPreferences sp = UIUtils.getContext().getSharedPreferences(LANGUAGE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        String localeJson = localeToJson(pUserLocale);
        edit.putString(LANGUAGE, localeJson);
        edit.apply();
    }
    /**
     * Locale转成json
     */
    private static String localeToJson(Locale locale) {
        Gson gson = new Gson();
        return gson.toJson(locale);
    }
    /**
     * json转成Locale
     */
    private static Locale jsonToLocale(String local) {
        Gson gson = new Gson();
        return gson.fromJson(local, Locale.class);
    }
}
