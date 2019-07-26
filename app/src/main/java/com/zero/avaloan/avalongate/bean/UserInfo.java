package com.zero.avaloan.avalongate.bean;

import com.google.gson.annotations.Expose;

public class UserInfo {
    /**
     * 单例模式
     * 1、隐藏的构造函数
     * 2、隐藏、静态的自我对象生命
     * 3、开放的、静态的getInstance
     *
     * 注意：
     * 1、线程安全，多线程内的数据同步
     * 2、与界面、view相关的内存泄漏
     **/

    private UserInfo() {
    }

    /**
     * volatile关键字能够保证可见性
     * 被volatile修饰的变量
     * 在一个线程中被改变时会立刻同步到主内存中
     * 而另一个线程在操作这个变量时都会先从主内存更新这个变量的值
     * */

    private volatile static UserInfo mUserInfo;

    /**
     * 使用synchronized用来修饰方法
     *
     * 基本执行过程：
     * 当多个线程同时访问被synchronized修饰的方法的时候，
     * 有且只有一个线程可以访问，
     * 当一个线程在访问的时候，
     * 其他线程只能等待。
     * 当一个线程访问完毕后下一个线程才可以访问。
     * **/
    public static UserInfo getInstance(){

//        //最简单，但不是线程安全的
//        if (mUserInfo == null) {
//            mUserInfo = new UserInfo();
//        }
        if(mUserInfo==null){//不为空的情况下，不需要再调用
            synchronized (UserInfo.class){
                if(mUserInfo==null){
                    mUserInfo = new UserInfo();
                }
            }
        }
        return mUserInfo;
    }

    @Expose
    private String userName;

    @Expose
    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private void setUserInfo(UserInfo userInfo){
        this.userId = userInfo.getUserId();
        this.userName = userInfo.getUserName();

    }
}
