package com.zero.avaloan.avalongate.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import com.egoo.chat.ChatSDKManager;
import com.egoo.sdk.GlobalManager;

public class App extends Application {
    private static App appInstance;
    public static App getInstance() {
        return appInstance;
    }
    //主线程Handler
    private static Handler mMainThreadHandler;
    //主线程ID
    private static int mMainThreadId = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainThreadHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        initChat();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    private void initChat(){
        GlobalManager.getInstance().initApp(this);
        ChatSDKManager.initChatSDK(this);

    }
}
