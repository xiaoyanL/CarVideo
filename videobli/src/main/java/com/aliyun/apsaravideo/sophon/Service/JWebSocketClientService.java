package com.aliyun.apsaravideo.sophon.Service;

import static com.fd.baselibrary.api.HostUrl.SCREEN_OFF;
import static com.fd.baselibrary.api.HostUrl.Socket_URL;
import static com.fd.baselibrary.app.Constants.HANG_ANSWER;
import static com.fd.baselibrary.app.Constants.HANG_CALL;
import static com.fd.baselibrary.app.Constants.HANG_SIGN;
import static com.fd.baselibrary.app.Constants.HANG_UP;
import static com.fd.baselibrary.app.Constants.HANG_WEB;
import static com.fd.baselibrary.app.Constants.MAIN_LOING_ACTIVITY;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alirtc.beacontowner.R;
import com.aliyun.apsaravideo.sophon.base.MyReceive;
import com.aliyun.apsaravideo.sophon.manager.IMMessageEvent;
import com.aliyun.apsaravideo.sophon.manager.JGApplication;
import com.aliyun.apsaravideo.sophon.manager.JWebSocketClient;
import com.aliyun.apsaravideo.sophon.manager.MessageBean;
import com.aliyun.apsaravideo.sophon.videocall.AnswerActivity;
import com.fd.BaseApplication;
import com.fd.baselibrary.utils.SPManager;
import com.fd.baselibrary.utils.ToastUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class JWebSocketClientService extends Service {
    public JWebSocketClient client;
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();
    private final static int GRAY_SERVICE_ID = 1001;
    private NotificationManager notificationManager;
    private String notificationId = "channelId";
    private String notificationName = "nuonuo";
    Map<String, String> httpHeaders;
    private Intent intent;

    //????????????
    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }

    PowerManager.WakeLock wakeLock;//????????????

    //???????????????????????????????????????????????????????????????CPU??????????????????
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock) {
                wakeLock.acquire();
            }

        }
    }

    // ?????????????????????
    private void releaseWakeLock() {
        if (null != wakeLock && wakeLock.isHeld()) {
            wakeLock.release();
            wakeLock = null;
        }
    }

    //??????Activity???service??????
    public class JWebSocketClientBinder extends Binder {
        public JWebSocketClientService getService() {
            return JWebSocketClientService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotification();
        httpHeaders = new HashMap<>();
        httpHeaders.put("Sec-WebSocket-Protocol", SPManager.getUserWebTokenKey());
        intent = new Intent();
        intentFilter = new IntentFilter(HANG_CALL);
        receiver = new MyReceive();
        registerReceiver(receiver, intentFilter);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //?????????websocket
        if (client==null){
            initSocketClient();
            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//??????????????????
        }
        acquireWakeLock();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        closeConnect();
        stopForeground(true);
        releaseWakeLock();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

        if (mHandler != null && heartBeatRunnable != null) {
            mHandler.removeCallbacks(heartBeatRunnable);
        }
        Log.e("JWebSocketClientService", "onDestroy???");
        EventBus.getDefault().unregister(this);
    }

    public JWebSocketClientService() {
    }


    /**
     * ?????????websocket??????
     */
    private void initSocketClient() {
        URI uri = URI.create(Socket_URL);

        client = new JWebSocketClient(uri, httpHeaders) {
            @Override
            public void onMessage(String message) {
                Log.e("JWebSocketClientService", "??????????????????" + message);
//                EventBus.getDefault().post(new IMMessageEvent(message, JGApplication.SEND_IM_MESSAGE)); //?????????Im??????
//                intent.putExtra("message",message);
//                sendBroadcast(intent);
//                startActivity(new Intent(this, HandlePhoneActivity.class).putExtra("message",message));
                json(message);
            }

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                super.onOpen(handshakedata);
                Log.e("JWebSocketClientService", "websocket????????????");
            }

            @Override
            public void onError(Exception ex) {
                super.onError(ex);
                Log.e("JWebSocketClientService", "websocket????????????");
            }
        };
//        client.onopen=function(){
//            ws.send(token)
//        }
        connect();
//        EventBus.getDefault().post(new IMMessageEvent("{\"code\":200,\"callUsername\":\"zs\",\"channelId\":\"548974\",\"username\":\"zs\"}", JGApplication.SEND_IM_MESSAGE)); //?????????Im??????
    }

    /**
     * ??????websocket
     */
    private void connect() {
        new Thread() {
            @Override
            public void run() {
                try {
                    //connectBlocking?????????????????????????????????????????????????????????????????????????????????
                    client.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * ????????????
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        if (null != client) {
            Log.e("JWebSocketClientService", "??????????????????" + msg);
            client.send(msg);
        }
    }

    /**
     * ????????????
     */
    private void closeConnect() {
        try {
            if (null != client) {
                client.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }


    //    -------------------------------------websocket????????????------------------------------------------------
    private static final long HEART_BEAT_RATE = 10 * 1000;//??????10??????????????????????????????????????????
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
//            Log.e("latelone", BaseApplication.getInstance().late+"      "+BaseApplication.getInstance().lone);
//            Log.e("JWebSocketClientService", "???????????????websocket????????????");
            if (client != null) {
                if (client.isClosed()) {
                    reconnectWs();
                } else {
                    sendMsg("{\"type\": 88}");
                }
            } else {
                //??????client?????????????????????????????????
                client = null;
                initSocketClient();
            }
            //????????????????????????????????????????????????????????????
            mHandler.postDelayed(this, HEART_BEAT_RATE);

        }
    };

    /**
     * ????????????
     */
    private void reconnectWs() {
        mHandler.removeCallbacks(heartBeatRunnable);
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.e("JWebSocketClientService", "????????????"+client.toString());
                    client.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void exitLogin() {
        if (mHandler != null && heartBeatRunnable != null) {
            mHandler.removeCallbacks(heartBeatRunnable);
        }
        closeConnect();
        stopSelf();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().unregister(this);
            }
        }, 500);
    }

    public void json(String message) {
        MessageBean bean = new Gson().fromJson(message, MessageBean.class);
        if (bean.getCode() == 200) {
            if (bean.getData().getType() == 0) {
                Intent intent = new Intent(this,AnswerActivity. class );
                intent.putExtra("channelId", bean.getData().getChannelId());
                intent.putExtra("name", bean.getData().getName());
                intent.putExtra("calledId", bean.getData().getCalledId());
                intent.putExtra("bean", bean.getData().getAssessInfoVo());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                wakeUpAndUnlock(getApplicationContext());
                startActivity(intent);

            } else if (bean.getData().getType() == 1) {
                sendBroadcast(new Intent(HANG_ANSWER));//??????
            } else if (bean.getData().getType() == 5) {//??????
                sendBroadcast(new Intent(HANG_UP));
            }else if(bean.getData().getType() == 7){
                if (  BaseApplication.getInstance().islive){
                    sendBroadcast(new Intent(HANG_SIGN));
                }else {

                    SPManager.setUserToken("");
//                        SPManager.setIsPassword(false);
                    SPManager.setIsLogin(false);
                    ARouter.getInstance().build(MAIN_LOING_ACTIVITY).withInt("type",1).navigation();
                }

            }

        }else if(bean.getCode() == 500){
            sendBroadcast(new Intent(HANG_WEB));
        }
    }

    public void showDialog() {

    }
    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //??????NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(notificationId, notificationName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification= getNotification();
        //????????????
        startForeground(1,notification);
    }

    private Notification getNotification() {
        //??????PendingIntent
        Intent intentCancel = new Intent(this, MyReceive.class);
        intentCancel.setAction(HANG_CALL);
        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(this, 0, intentCancel, PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.logo)//???????????????
                .setContentTitle("???????????????")
//                .setContentIntent(pendingIntentCancel)
                .setContentText("???????????????");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(notificationId);
        }
        Notification notification = builder.build();
        return notification;
    }

    public void aaa() {
        try {
            //???????????????????????????
            Log.e("zhiyinqing", "??????????????????");
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                    PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
            wl.acquire(); //????????????
            //wl.release();//??????

            //????????????
            KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            //??????????????????????????????
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            //?????????LogCat?????????Tag
            kl.disableKeyguard();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private IntentFilter intentFilter;
    MyReceive receiver;




    /**
     * ??????????????????????????????????????????
     *
     * @param context
     * @return ?????????????????????????????????????????? true??????????????? false
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
        /**????????????*/
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void Notify() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("110", "bob", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(this);
            //?????????????????????channelID
            builder.setChannelId("110");
            builder.setShowWhen(true);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setAutoCancel(true);
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), AnswerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);
        } else {
            Notification.Builder messageNotification = new Notification.Builder(getApplication());
            messageNotification.setShowWhen(true);
            messageNotification.setWhen(System.currentTimeMillis());
            messageNotification.setDefaults(Notification.DEFAULT_ALL);
            messageNotification.setAutoCancel(true);
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), AnswerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);

        }
    }
    public void wakeUpAndUnlock(Context context) {
        //???????????????
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //??????
        kl.disableKeyguard();
        //???????????????????????????
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //??????PowerManager.WakeLock??????,???????????????|???????????????????????????,????????????LogCat?????????Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,  ":bright");
        //????????????
        wl.acquire();
        //??????
        wl.release();
    }


}
