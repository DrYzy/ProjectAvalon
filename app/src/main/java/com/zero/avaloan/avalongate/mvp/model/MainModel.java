package com.zero.avaloan.avalongate.mvp.model;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 在mvp结构中，作为网络访问，获取数据等过程所在的部分
 * 是个没有人权的工具人
 **/

public class MainModel {
    public Call getData(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }
}
