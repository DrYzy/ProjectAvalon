package com.zero.avaloan.avalongate.mvp.presenter;

import android.annotation.SuppressLint;
import com.zero.avaloan.avalongate.bean.LoginBody;
import com.zero.avaloan.avalongate.mvp.model.LoginModel;
import com.zero.avaloan.avalongate.mvp.view.LoginContract;
import com.zero.avaloan.avalongate.net.ServerException;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginModel model;

    public LoginPresenter(LoginContract.View view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    @SuppressLint("CheckResult")
    @Override
    public void login(String name, String password) {
        LoginBody body = new LoginBody();
//        body.name = name;
//        body.password = password;

        view.setLoading(true);
        model.login(body)
                .subscribe(response -> {
                    view.callback(true);  // 成功回调
                    view.setLoading(false);
                }, throwable -> {
                    ServerException exception = (ServerException) throwable;
                    view.errorCallback(exception);
                    view.setLoading(false);
                });
    }
}

