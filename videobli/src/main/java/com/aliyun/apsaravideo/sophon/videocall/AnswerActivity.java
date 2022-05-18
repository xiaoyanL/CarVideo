package com.aliyun.apsaravideo.sophon.videocall;

import static com.fd.baselibrary.app.Constants.HANG_ANSWER;
import static com.fd.baselibrary.app.Constants.HANG_CALL;
import static com.fd.baselibrary.app.Constants.HANG_UP;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alirtc.beacontowner.R;
import com.alivc.rtc.AliRtcAuthInfo;
import com.aliyun.apsaravideo.sophon.Service.JWebSocketClientService;
import com.aliyun.apsaravideo.sophon.api.TestMvpPresenter;
import com.aliyun.apsaravideo.sophon.bean.AnswerBean;
import com.aliyun.apsaravideo.sophon.bean.RTCAuthInfo;
import com.aliyun.apsaravideo.sophon.login.HandlePhoneActivity;
import com.aliyun.apsaravideo.sophon.manager.JWebSocketClient;
import com.aliyun.apsaravideo.sophon.manager.assessInfoVoBean;
import com.aliyun.apsaravideo.sophon.network.RequestRTCAuthInfo;
import com.aliyun.apsaravideo.sophon.utils.MockAliRtcAuthInfo;
import com.aliyun.apsaravideo.sophon.utils.SpHistoryUtils;
import com.aliyun.apsaravideo.sophon.videocall.view.AlivcControlView;
import com.aliyun.apsaravideo.sophon.widget.SpreadView;
import com.fd.BaseApplication;
import com.fd.baselibrary.base.ToolbarActivity;
import com.fd.baselibrary.baseBean.TokenBean;
import com.fd.baselibrary.network.RxCallback;
import com.google.gson.Gson;
import com.mirkowu.basetoolbar.BaseToolbar;

import java.security.NoSuchAlgorithmException;

public class AnswerActivity extends ToolbarActivity<TestMvpPresenter> {
    SpreadView sw_Answer;
    SpreadView tv_refuse;
    private boolean ismHandler = true;
    MediaPlayer mp;
    int channelI;
    int calledId;
    private assessInfoVoBean assessInfoVo;
    private IntentFilter intentFilter;
    LocalReceiver receiver;
    TokenBean tokenBean;
    String name;
    private TextView tv_name;
    private  TextView tv_part;
    private TextView tv_specialName;

    @Override
    protected int getLayoutId() {
        isStatusBar = false;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //锁屏显示
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD //解锁
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON //保持屏幕不息屏
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);//点亮屏幕
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD //解锁
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON //保持屏幕不息屏
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);//点亮屏幕
        if (Build.VERSION.SDK_INT > 27) {
            setShowWhenLocked(true);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        //低调模式, 会隐藏不重要的状态栏图标，https://blog.csdn.net/QQsongQQ/article/details/89312763
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        getWindow().setAttributes(params);

        return R.layout.activity_answer;
    }

    @Override
    protected void initialize() {

        mp = new MediaPlayer();
        try {
            mp.setDataSource(this, RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_RINGTONE));
            mp.prepare();
            mp.setLooping(true);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sw_Answer = findViewById(R.id.sw_Answer);
        tv_refuse = findViewById(R.id.tv_refuse);
        tv_name = findViewById(R.id.tv_name);
        tv_part= findViewById(R.id.tv_part);
        tv_specialName= findViewById(R.id.tv_specialName);
        channelI = getIntent().getIntExtra("channelId", 0);
        calledId = getIntent().getIntExtra("calledId", 0);
        name = getIntent().getStringExtra("name");
        assessInfoVo= (assessInfoVoBean) getIntent().getSerializableExtra("bean");
        if (assessInfoVo!=null){
            tv_name.setText(assessInfoVo.getProjectName());
            tv_part.setText(assessInfoVo.getSectionName());
            tv_specialName.setText(assessInfoVo.getSpecialName());
        }

        sw_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().token("1", channelI + "").safeSubscribe(new RxCallback<TokenBean>() {
                    @Override
                    public void onSuccess(@Nullable TokenBean data) {
                        AnswerBean bean = new AnswerBean(1, calledId);
                        tokenBean = data;
                        if (jWebSClientService != null) {
                            jWebSClientService.sendMsg(new Gson().toJson(bean));
                        }

                    }
                });
            }
        });
        tv_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnswerBean bean = new AnswerBean(2, calledId);
                if (jWebSClientService != null) {
                    jWebSClientService.sendMsg(new Gson().toJson(bean));
                }

                finish();
            }
        });

        bindService();
        mHandler.postDelayed(heartBeatRunnable, 2 * 60 * 1000);
        intentFilter = new IntentFilter(HANG_ANSWER);
        intentFilter.addAction(HANG_UP);
        intentFilter.addAction(HANG_CALL);
        receiver = new LocalReceiver();
        registerReceiver(receiver, intentFilter);
    }

    public void send() {

    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    public void joinChannel(String userName, String channelId, String token, TokenBean data) {
        SpHistoryUtils.saveSearchHistory(AnswerActivity.this, channelId);
        startVideoCallActivity(channelId, userName, token, data);
        finish();
    }

    private void startVideoCallActivity(final String channelId, final String userName, String token, TokenBean data) {

        //本地生成token
        try {
            AliRtcAuthInfo authInfo = MockAliRtcAuthInfo.mockAuthInfo(channelId, MockAliRtcAuthInfo.createUserId(channelId, userName));
            RTCAuthInfo info = new RTCAuthInfo();
            RTCAuthInfo.RTCAuthInfo_Data info_data = new RTCAuthInfo.RTCAuthInfo_Data();
            info.data = info_data;
            info.data.appid = data.getAppId();
            info.data.userid = data.getUserId();
            info.data.nonce = data.getNonce();
            info.data.timestamp = data.getTimestamp();
//            info.data.token = authInfo.mToken;
            info.data.token = data.getToken();
            String[] strs1 = data.getGslbList().toArray(new String[data.getGslbList().size()]);
            info.data.gslb = strs1;
            info.data.ConferenceId = data.getChannelId();
            showAuthInfo(data.getChannelId(), info, userName);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }


    /**
     * 网络获取加入频道信息
     *
     * @param rtcAuthInfo
     */
    public void showAuthInfo(String channelId, RTCAuthInfo rtcAuthInfo, String userName) {
        Intent intent = new Intent(this, VideoCallActivity.class);
        Bundle b = new Bundle();
        //用户名
        b.putString("username", userName);
        //频道号
        b.putString("channel", channelId);
        //音频播放
        b.putSerializable("rtcAuthInfo", rtcAuthInfo);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(b);
        startActivity(intent);
    }

    JWebSocketClientService jWebSClientService;
    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClient client;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "服务与活动成功绑定");
            binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
            AnswerBean bean = new AnswerBean(6, calledId);
            if (jWebSClientService != null) {
                jWebSClientService.sendMsg(new Gson().toJson(bean));
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };

    /**
     * 绑定服务
     */
    private void bindService() {
        Intent bindIntent = new Intent(this, JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        ismHandler = false;
        if (mHandler != null && heartBeatRunnable != null) {
            mHandler.removeCallbacks(heartBeatRunnable);
        }
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
        mp.stop();
    }

    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (ismHandler) {
                AnswerBean bean = new AnswerBean(2, calledId);
                if (jWebSClientService != null) {
                    jWebSClientService.sendMsg(new Gson().toJson(bean));
                }
                finish();
            }
        }
    };

    public class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
// TODO Auto-generated method stub
            if (arg1.getAction().equals(HANG_ANSWER)) {
                if (tokenBean != null) {
                    joinChannel(name, channelI + "", tokenBean.getToken(), tokenBean);
                }
            } else if (arg1.getAction().equals(HANG_UP)) {
                finish();
            }else if (arg1.getAction().equals(HANG_CALL)) {
                finish();
            }


        }
    }

}