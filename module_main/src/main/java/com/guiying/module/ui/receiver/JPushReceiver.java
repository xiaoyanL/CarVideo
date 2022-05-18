package com.guiying.module.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;



import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;


public class JPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JIGUANG";
    public static final String PRIMARY_CHANNEL = "default";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Log.e(TAG,"[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.e(TAG,"[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Log.e(TAG,"[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Log.e(TAG,"[MyReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Log.e(TAG,"[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.e(TAG,"[MyReceiver] 用户点击打开了通知");
                receivingNotification(context, bundle);
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.e(TAG,"[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                if (!connected) {
                    JPushInterface.init(context.getApplicationContext());
                    JPushInterface.resumePush(context.getApplicationContext());
                    Log.e(TAG,"重连极光 ---> ");
                }
                Log.e(TAG,"[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Log.e(TAG,"[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }
    }

    private void receivingNotification(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        Log.e(TAG," title : " + title);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        Log.e(TAG,"message : " + message);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG,"extras : " + extras);


    }


    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        String contentType = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG,"title=" + title + ",message=" + message + ",contentType=" + contentType);
        Log.e(TAG,"extras=" + extras);
       // EventBus.getDefault().post(new TestEvent("title=" + title + ",message=" + message + ",contentType=" + contentType + "extras=" + extras));
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Log.e(TAG,"This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG,"Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        String contentType = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        Log.e(TAG,"收到推送下来的消息: \ntitle=" + Data(title) + "\nmessage=" + Data(message) +
                "\ncontentType=" + Data(contentType) + "\nextras=" + Data(extras));
        if (contentType.equals("1")) { //设备网关推送消息
//            mNotificationNum++;
            /*EventBus.getDefault().post(new TopicEvent(message));
            TopicBean topicBean = new Gson().fromJson(message, TopicBean.class); //转为实体类
            if (topicBean != null) {

            }*/
        }
    }
   // base64解码
    private String Data(String data){
        return new String(Base64.decode(data,Base64.DEFAULT));
    }
  /*  //base64解码
    String str2 = new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT));*/
}
