package com.zero.avaloan.avalongate.mvp.presenter;

import android.support.annotation.NonNull;
import com.zero.avaloan.avalongate.mvp.model.MainModel;
import com.zero.avaloan.avalongate.mvp.view.MainCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;

public class MainPresenter {
    private MainCallBack callBack;
    private MainModel model;

    public MainPresenter(MainCallBack callBack) {
        this.callBack = callBack;
        model=new MainModel();
    }

    public void getUrlData(String url){
        model.getData(url).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.error();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                callBack.getMessage(response.body().string());
            }
        });
    }
}
