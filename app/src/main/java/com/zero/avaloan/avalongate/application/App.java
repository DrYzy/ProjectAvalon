package com.zero.avaloan.avalongate.application;

import android.app.Application;
import android.os.Handler;

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
}