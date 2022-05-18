package com.aliyun.apsaravideo.sophon.manager;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class JWebSocketClient extends WebSocketClient {
    public JWebSocketClient(URI serverUri) {
        super(serverUri, new Draft_6455());
    }
    public JWebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, new Draft_6455(),httpHeaders);
    }
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("JWebSocketClientService", "onOpen()");
    }

    @Override
    public void onMessage(String message) {
        Log.e("JWebSocketClientService", "onMessage()");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("JWebSocketClientService", "onClose()");
    }

    @Override
    public void onError(Exception ex) {
        Log.e("JWebSocketClientService", "onError()");
    }
}
