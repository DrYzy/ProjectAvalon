package com.zero.avaloan.avalongate.utils;

import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;
import com.zero.avaloan.avalongate.application.App;

public class ToastUtils {
    /**
     * 让Toast可以在非UI线程调用。 短时间
     */
    public static void showToastSafeShort(final String str) {
        if (isRunInMainThread()) {
            showToast(str, Toast.LENGTH_SHORT);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str, Toast.LENGTH_SHORT);
                }
            });
        }
    }

    /**
     * 让Toast可以在非UI线程调用。 长时间
     */
    public static void showToastSafeLong(final String str) {
        if (isRunInMainThread()) {
            showToast(str, Toast.LENGTH_LONG);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str, Toast.LENGTH_LONG);
                }
            });
        }
    }

    /**
     * 让Toast可以在非UI线程调用。 短时间
     * 默认时间 short
     */
    public static void showDiyPosToastSafeShort(final String str, final int diyPosition) {
        if (isRunInMainThread()) {
            showToastDiyPosition(str, diyPosition);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToastDiyPosition(str, diyPosition);
                }
            });
        }
    }

    /**
     * 设置toast
     */
    private static void showToast(final String str, int delayTime) {
        Toast toast = Toast.makeText(App.getInstance(), null, delayTime);
        toast.setText(str);
        toast.show();
    }

    /**
     * 设置toast  并自定义他的位置
     */
    private static void showToastDiyPosition(final String str, int diyGravity) {
        Toast toast = Toast.makeText(App.getInstance(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return App.getInstance().getMainThreadHandler();
    }

    public static long getMainThreadId() {
        return App.getInstance().getMainThreadId();
    }
}
