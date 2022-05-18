package com.aliyun.apsaravideo.sophon.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alirtc.beacontowner.R;
import com.alivc.rtc.AliRtcAuthInfo;
import com.aliyun.apsaravideo.sophon.bean.RTCAuthInfo;
import com.aliyun.apsaravideo.sophon.manager.MessageBean;
import com.aliyun.apsaravideo.sophon.network.RequestRTCAuthInfo;
import com.aliyun.apsaravideo.sophon.utils.MockAliRtcAuthInfo;
import com.aliyun.apsaravideo.sophon.utils.SpHistoryUtils;
import com.aliyun.apsaravideo.sophon.videocall.VideoCallActivity;
import com.fd.baselibrary.base.ToolbarActivity;
import com.google.gson.Gson;
import com.mirkowu.basetoolbar.BaseToolbar;

import java.security.NoSuchAlgorithmException;

public class HandlePhoneActivity extends ToolbarActivity {
    MessageBean bean;
   String  message;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_handle_phone;
    }

    @Override
    protected void initialize() {
        message=  getIntent().getStringExtra("message");
        try {
            bean=new Gson().fromJson(message,MessageBean.class);
        }catch (Exception e){

        }
         if (bean==null){
             finish();
             return;
         }
//        joinChannel(bean.getUsername(),bean.getChannelId());
    }

    @Nullable
    @Override
    protected BaseToolbar.Builder setToolbar(@NonNull BaseToolbar.Builder builder) {
        return null;
    }

    public void joinChannel(String userName,String channelId) {
        SpHistoryUtils.saveSearchHistory(HandlePhoneActivity.this, channelId);
        startVideoCallActivity(channelId, userName);
    }

    private void startVideoCallActivity(final String channelId, final String userName) {

//        //本地生成token
//        try {
//            AliRtcAuthInfo authInfo = MockAliRtcAuthInfo.mockAuthInfo(channelId, MockAliRtcAuthInfo.createUserId(channelId, userName));
//            RTCAuthInfo info = new RTCAuthInfo();
//            RTCAuthInfo.RTCAuthInfo_Data info_data = new RTCAuthInfo.RTCAuthInfo_Data();
//            info.data = info_data;
//            info.data.appid = authInfo.mAppid;
//            info.data.userid = authInfo.mUserId;
//            info.data.nonce = authInfo.mNonce;
//            info.data.timestamp = authInfo.mTimestamp;
//            info.data.token = authInfo.mToken;
//            info.data.gslb = authInfo.mGslb;
//            info.data.ConferenceId = authInfo.mConferenceId;
//            showAuthInfo(channelId, info, userName);
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }


//        网络获取token
        RequestRTCAuthInfo.getAuthInfo(userName, channelId, new RequestRTCAuthInfo.OnRequestAuthInfoListener() {
            @Override
            public void onObtainAuthInfo(RTCAuthInfo rtcAuthInfo) {
                rtcAuthInfo.data.ConferenceId = channelId;
                showAuthInfo(channelId, rtcAuthInfo, userName);
            }

            @Override
            public void onFailure(String failure) {
                Toast.makeText(HandlePhoneActivity.this, failure, Toast.LENGTH_SHORT).show();
            }
        });
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
        intent.putExtras(b);
        startActivity(intent);
    }

}