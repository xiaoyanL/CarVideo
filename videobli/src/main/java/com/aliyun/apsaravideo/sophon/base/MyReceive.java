package com.aliyun.apsaravideo.sophon.base;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.aliyun.apsaravideo.sophon.manager.MessageBean;
import com.aliyun.apsaravideo.sophon.videocall.AnswerActivity;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.fd.baselibrary.app.Constants.HANG_CALL;

public class MyReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(HANG_CALL)){

            MessageBean bean= (MessageBean) intent.getSerializableExtra("bean");
            Intent intent1 = new Intent(context,AnswerActivity. class );
            intent1.putExtra("channelId", bean.getData().getChannelId());
            intent1.putExtra("name", bean.getData().getName());
            intent1.putExtra("calledId", bean.getData().getCalledId());
            intent1.addCategory(Intent.CATEGORY_LAUNCHER);
            intent1.setAction(Intent.ACTION_MAIN);
            intent1.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            intent1.setFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(context,intent1);

        }


    }
    ActivityManager activityManager;
    private  boolean moveToFront(Context context){
       activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台"
                            + appProcess.processName);
                    activityManager.moveTaskToFront(appProcess.importanceReasonPid, 0);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;

    }

    private void startActivity(Context context,Intent intent){
        if (!moveToFront(context)){
            Log.e("LLL", "Now is in background");
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
//                // TODO 防止 moveToFront 失败，可以多尝试调用几次
//                context.startActivity(intent);
//            }else {
                context.startActivity(intent) ;
//            }
        }else {
            Log.e("LLL", "Now is in foreground");
            context.startActivity(intent) ;
        }
    }

}
