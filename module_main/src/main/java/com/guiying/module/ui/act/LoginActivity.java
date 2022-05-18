package com.guiying.module.ui.act;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.fd.baselibrary.app.Constants.MAIN_LOING_ACTIVITY;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.aliyun.apsaravideo.sophon.Service.JWebSocketClientService;
import com.aliyun.apsaravideo.sophon.api.TestMvpPresenter;
import com.aliyun.apsaravideo.sophon.videocall.AnswerActivity;
import com.fd.baselibrary.api.ViewManager;
import com.fd.baselibrary.base.ToolbarActivity;
import com.fd.baselibrary.baseBean.UserBean;
import com.fd.baselibrary.network.ApiException;
import com.fd.baselibrary.network.RxCallback;
import com.fd.baselibrary.utils.EmptyUtil;
import com.fd.baselibrary.utils.SPManager;
import com.fd.baselibrary.utils.ToastUtil;
import com.google.gson.JsonParseException;
import com.guiying.module.main.R;
import com.guiying.module.main.R2;
import com.mirkowu.basetoolbar.BaseToolbar;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = MAIN_LOING_ACTIVITY)
public class LoginActivity extends ToolbarActivity<TestMvpPresenter> {
    @BindView(R2.id.ed_account)
    EditText ed_account;
    @BindView(R2.id.ed_password)
    EditText ed_password;
    @BindView(R2.id.ch_password)
    CheckBox ch_password;
    @BindView(R2.id.ch_login)
    CheckBox ch_login;
    private int type;
    @Override
    protected int getLayoutId() {
        isStatusBar = false;
        return R.layout.activity_login;
    }

    @Override
    protected void initialize() {
        ch_password.setChecked(SPManager.getIsPassword());
        ch_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ch_password.setChecked(b);
//                SPManager.setIsPassword(b);
            }
        });
        ch_login.setChecked(SPManager.getIsLogin());
        ch_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ch_login.setChecked(b);
//                SPManager.setIsLogin(b);
            }
        });
        UserBean data = SPManager.getUserData();
        if (ch_password.isChecked()) {
            if (data != null && EmptyUtil.isNotEmpty(data.getUserInfo().getPassword())) {
                ed_account.setText(data.getUserInfo().getAccount());
                ed_password.setText(data.getUserInfo().getPassword());
            }
        }

        if (ch_login.isChecked()) {
            if (data != null && EmptyUtil.isNotEmpty(data.getUserInfo().getPassword())) {
                login(data.getUserInfo().getAccount(), data.getUserInfo().getPassword());
            }
        }
        if (EmptyUtil.isNotEmpty(SPManager.getUserToken())) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            stopService(new Intent(getActivity(), JWebSocketClientService.class));
            ToastUtil.s("你已被去强制退出，请重新登录");
        } else if (type == 2) {
            stopService(new Intent(getActivity(), JWebSocketClientService.class));
        }

//        ed_account.setText("100065");
//        ed_password.setText("100065");

    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    @OnClick({R2.id.tv_lonig})
    public void OnClick(View v) {
        if (v.getId() == R.id.tv_lonig) {
            if (EmptyUtil.isEmpty(ed_account.getText().toString())) {
                ToastUtil.s("请输入用户名");
                return;
            } else if (EmptyUtil.isEmpty(ed_password.getText().toString())) {
                ToastUtil.s("请输入密码");
                return;
            }
            login(ed_account.getText().toString(), ed_password.getText().toString());
        }
    }

    public void login(String account, String password) {
        getPresenter().login(account, password).safeSubscribe(new RxCallback<UserBean>() {
            @Override
            public void onSuccess(@Nullable UserBean data) {
                if (ch_password.isChecked()) {
                    data.getUserInfo().setPassword(ed_password.getText().toString());
                } else {
                    data.getUserInfo().setPassword("");
                }
                SPManager.setIsPassword(ch_password.isChecked());
                SPManager.setIsLogin(ch_login.isChecked());
                SPManager.setUserToken(data.getTokenHead() + " " + data.getToken());
                SPManager.setUserWebTokenKey(data.getToken());
                SPManager.setUserData(data);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
                if (t instanceof ApiException) {
                    //通用的Api异常处理
                    ApiException exception = (ApiException) t;
                    if (exception.getStatus() == 202) {
                        showlogDialog();
                    } else {
                        getBaseDelegate().showError(t);
                    }
                }
            }
        });
    }

    public void showlogDialog() {

        new android.app.AlertDialog.Builder(getActivity())
                .setTitle(com.fd.baselibrary.R.string.base_prompt_message)
                .setMessage("是否强制登录")
                .setNegativeButton(com.fd.baselibrary.R.string.base_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(com.fd.baselibrary.R.string.base_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().forceLogin(ed_account.getText().toString(), ed_password.getText().toString()).safeSubscribe(new RxCallback<UserBean>() {
                            @Override
                            public void onSuccess(@Nullable UserBean data) {
                                if (ch_password.isChecked()) {
                                    data.getUserInfo().setPassword(ed_password.getText().toString());
                                } else {
                                    data.getUserInfo().setPassword("");
                                }
                                SPManager.setIsPassword(ch_password.isChecked());
                                SPManager.setIsLogin(ch_login.isChecked());
                                SPManager.setUserToken(data.getTokenHead() + " " + data.getToken());
                                SPManager.setUserWebTokenKey(data.getToken());
                                SPManager.setUserData(data);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });
                        dialog.dismiss();

                    }
                }).show();
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


}