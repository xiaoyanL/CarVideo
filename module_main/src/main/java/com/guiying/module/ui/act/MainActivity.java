package com.guiying.module.ui.act;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aliyun.apsaravideo.sophon.Service.JWebSocketClientService;
import com.aliyun.apsaravideo.sophon.base.PermissionUtil;
import com.aliyun.apsaravideo.sophon.login.HandlePhoneActivity;
import com.aliyun.apsaravideo.sophon.manager.IMMessageEvent;
import com.aliyun.apsaravideo.sophon.manager.JGApplication;
import com.aliyun.apsaravideo.sophon.manager.MessageBean;
import com.aliyun.apsaravideo.sophon.videocall.AnswerActivity;
import com.fd.BaseApplication;
import com.fd.baselibrary.api.ViewManager;
import com.fd.baselibrary.base.BaseFragment;
import com.fd.baselibrary.base.FragmentAdapter;
import com.fd.baselibrary.base.ToolbarActivity;
import com.fd.baselibrary.utils.GetGPS;
import com.fd.baselibrary.utils.SPManager;
import com.fd.baselibrary.utils.ToastUtil;
import com.fd.baselibrary.widget.NoScrollViewPager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.guiying.module.bean.TabEntityBean;
import com.guiying.module.main.R;
import com.guiying.module.main.R2;

import com.guiying.module.ui.fragment.MeFragment;
import com.guiying.module.ui.fragment.ProjectListFragment;
import com.mirkowu.basetoolbar.BaseToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.fd.baselibrary.utils.RxPermissionsUtil.CAMERA_STORAGE;

/**
 * <p>类说明</p>
 *
 * @version V1.2.0
 * @name MainActivity
 */
public class MainActivity extends ToolbarActivity {



    @Override
    protected int getLayoutId() {
        isStatusBar = false;
        return R.layout.activity_main;

    }

    @Override
    protected void initialize() {


    }




    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }


    /**
     * 再按一次退出程序
     */
    private long currentBackPressedTime = 0;
    private static int BACK_PRESSED_INTERVAL = 5000;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
                currentBackPressedTime = System.currentTimeMillis();
                ToastUtil.s("再按一次，退出应用！");
                return true;
            } else {
                ViewManager.getInstance().exitApp(this);
            }
            return false;

        } else if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
