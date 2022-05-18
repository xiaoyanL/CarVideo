package com.fd.baselibrary.Receiver;

import static com.fd.baselibrary.api.HostUrl.HANDLEPHONEACTIVITY;
import static com.fd.baselibrary.api.HostUrl.SCREEN_OFF;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
               if (intent.getAction().equals(SCREEN_OFF)){
//                   String message=intent.getStringExtra("message");
//                   ARouter.getInstance().build(HANDLEPHONEACTIVITY).withString("message",message).navigation();
               }
    }
}
