package com.zero.avaloan.avalongate.mvp.presenter;

import android.annotation.SuppressLint;
import com.google.gson.internal.LinkedTreeMap;
import com.zero.avaloan.avalongate.bean.BaseResponse;
import com.zero.avaloan.avalongate.bean.ContentInfo;
import com.zero.avaloan.avalongate.bean.LoginBody;
import com.zero.avaloan.avalongate.mvp.model.LoginModel;
import com.zero.avaloan.avalongate.mvp.view.LoginContract;
import com.zero.avaloan.avalongate.net.ServerException;
import com.zero.avaloan.avalongate.utils.EncryptUtils;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.ResourceObserver;

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
        body.loginName = name;
        body.loginPwd = EncryptUtils.md5(password);

        view.setLoading(true);
//        model.login(body)
//                .subscribe(response -> {
//                    view.callback("data"+response.data+"meg:"+response.message+"code"+response.code);  // 成功回调
//                    view.setLoading(false);
//                }, throwable -> {
//                    ServerException exception = (ServerException) throwable;
//                    view.errorCallback(exception);
//                    view.setLoading(false);
//                });
        model.login(body)
                .subscribe(new Consumer<BaseResponse<Object>>() {
                    @Override
                    public void accept(BaseResponse<Object> stringBaseResponse) throws Exception {
                        if("0".equalsIgnoreCase(stringBaseResponse.resCode)){
                            LinkedTreeMap mInfo = (LinkedTreeMap)stringBaseResponse.content;
                            String displayInfo = mInfo.get("displayInfo").toString();
                            view.callback(displayInfo);
                        }else{

                        }

                        view.setLoading(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.setLoading(false);
                    }
                });
    }
}

