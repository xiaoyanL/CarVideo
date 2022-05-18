package com.aliyun.apsaravideo.sophon.videocall;

import static com.alivc.rtc.AliRtcEngine.AliRtcVideoTrack.AliRtcVideoTrackBoth;
import static com.alivc.rtc.AliRtcEngine.AliRtcVideoTrack.AliRtcVideoTrackCamera;
import static com.fd.baselibrary.app.Constants.HANG_ANSWER;
import static com.fd.baselibrary.app.Constants.HANG_CALL;
import static com.fd.baselibrary.app.Constants.HANG_SIGN;
import static com.fd.baselibrary.app.Constants.HANG_WEB;
import static com.fd.baselibrary.app.Constants.MAIN_LOING_ACTIVITY;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alirtc.beacontowner.R;
import com.alivc.rtc.AliRtcEngine;
import com.alivc.rtc.AliRtcEngineEventListener;
import com.aliyun.apsaravideo.sophon.Service.JWebSocketClientService;
import com.aliyun.apsaravideo.sophon.bean.AnswerBean;
import com.aliyun.apsaravideo.sophon.bean.RTCAuthInfo;
import com.aliyun.apsaravideo.sophon.manager.JWebSocketClient;
import com.aliyun.apsaravideo.sophon.rtc.RTCBeaconTowerImpl;
import com.aliyun.apsaravideo.sophon.utils.ApplicationContextUtil;
import com.aliyun.apsaravideo.sophon.utils.StringUtil;
import com.fd.BaseApplication;
import com.fd.baselibrary.baseBean.LocationBean;
import com.fd.baselibrary.utils.SPManager;
import com.google.gson.Gson;

public class VideoCallActivity extends AppCompatActivity implements View.OnClickListener {

    AlivcVideoCallView alivcVideoCallView;
    String displayName;
    String channel;
    private RTCAuthInfo mRtcAuthInfo;
    private TextView mTitleTv;
    private TextView mCopyTv;
    private boolean ismHandler;
    private LocationBean locationBean;

    private IntentFilter intentFilter;
    LocalReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //锁屏显示
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD //解锁
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON //保持屏幕不息屏
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);//点亮屏幕
        setContentView(R.layout.aliyun_video_call_main);


        getIntentData();
        bindService();

        ismHandler = true;
        mTitleTv = findViewById(R.id.tv_title);
        mCopyTv = findViewById(R.id.tv_copy);
        alivcVideoCallView = findViewById(R.id.alivc_videocall_view);
        mCopyTv.setOnClickListener(this);
        locationBean = new LocationBean();

        alivcVideoCallView.setAlivcVideoCallNotifyListner(new AlivcVideoCallView.AlivcVideoCallNotifyListner() {
            @Override
            public void onLeaveChannel() {
                finish();
            }
        });

        mTitleTv.setText(String.format(getResources().getString(R.string.str_channel_code), channel));
        alivcVideoCallView.auth(displayName, channel, mRtcAuthInfo);
        mHandler.postDelayed(heartBeatRunnable, 2000);
        intentFilter = new IntentFilter(HANG_CALL);
        intentFilter.addAction(HANG_WEB);
        intentFilter.addAction(HANG_SIGN);

        receiver = new LocalReceiver();
        registerReceiver(receiver, intentFilter);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        AliRtcEngine.getInstance(this). setCameraZoom(0.1f,false,true);
        BaseApplication.getInstance().islive=true;
        registerPhoneStateListener();
    }

    private void getIntentData() {
        if (getIntent().getExtras() != null) {
            displayName = getIntent().getExtras().getString("username");
            channel = getIntent().getExtras().getString("channel");
            mRtcAuthInfo = (RTCAuthInfo) getIntent().getExtras().getSerializable("rtcAuthInfo");
        }
    }

    @Override
    protected void onDestroy() {
        ismHandler = false;
        AnswerBean bean = new AnswerBean(5, Integer.valueOf(channel));
        if (mHandler != null && heartBeatRunnable != null) {
            mHandler.removeCallbacks(heartBeatRunnable);
        }
        if (receiver!=null){
            unregisterReceiver(receiver);
        }
        if (jWebSClientService != null) {
            jWebSClientService.sendMsg(new Gson().toJson(bean));
        }
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
        if (alivcVideoCallView != null) {
            alivcVideoCallView.leave();
        }
        BaseApplication.getInstance().islive=false;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_copy) {
            StringUtil.clipChannelId(channel, VideoCallActivity.this);
        }
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

    private static final long HEART_BEAT_RATE = 60 * 1000;//每隔60秒发送一次地理位置
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("VideoCallActivity", "发送地理位置");
            if (ismHandler) {
                //每隔一定的时间，发送一次地理位置
                locationBean.setType("4");
                locationBean.setUserId(channel);
                locationBean.setLatitude(BaseApplication.getInstance().late + "");
                locationBean.setLongitude(BaseApplication.getInstance().lone + "");
                if (jWebSClientService != null) {
                    jWebSClientService.sendMsg(new Gson().toJson(locationBean));
                }
                mHandler.postDelayed(this, HEART_BEAT_RATE);
            }

        }
    };

    public class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
// TODO Auto-generated method stub
            if (arg1.getAction().equals(HANG_CALL)||arg1.getAction().equals(HANG_WEB) ) {
                AliRtcEngine.getInstance(getApplicationContext()).leaveChannel();
                finish();
            }else  if (arg1.getAction().equals(HANG_SIGN) ) {
                AliRtcEngine.getInstance(getApplicationContext()).leaveChannel();
                SPManager.setUserToken("");
                SPManager.setIsLogin(false);
                ARouter.getInstance().build(MAIN_LOING_ACTIVITY).withInt("type",1).navigation();
                finish();
            }

        }
    }
    public void showDialog() {
        new android.app.AlertDialog.Builder(this)
                .setTitle(com.fd.baselibrary.R.string.base_prompt_message)
                .setMessage("请重新登录")
                .setPositiveButton(com.fd.baselibrary.R.string.base_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SPManager.setUserToken("");
                        SPManager.setIsLogin(false);
                        ARouter.getInstance().build(MAIN_LOING_ACTIVITY).withInt("type",1).navigation();
                     finish();
                    }
                }).show();
    }
    private void registerPhoneStateListener() {
//        CustomPhoneStateListener customPhoneStateListener = new CustomPhoneStateListener(this);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }
    PhoneStateListener listener=new PhoneStateListener(){

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //注意，方法必须写在super方法后面，否则incomingNumber无法获取到值。
            super.onCallStateChanged(state, incomingNumber);
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE:
                    System.out.println("挂断");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("接听");
                    finish();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    System.out.println("响铃:来电号码"+incomingNumber);
                    //输出来电号码
                    break;
            }
        }
    };
}
