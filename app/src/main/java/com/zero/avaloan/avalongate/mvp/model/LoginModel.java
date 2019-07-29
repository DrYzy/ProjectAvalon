package com.zero.avaloan.avalongate.mvp.model;

import com.zero.avaloan.avalongate.base.BaseModel;
import com.zero.avaloan.avalongate.bean.BaseResponse;
import com.zero.avaloan.avalongate.bean.LoginBody;
import com.zero.avaloan.avalongate.net.RetrofitServiceManager;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class LoginModel extends BaseModel {

    private LoginModelService service;

    public LoginModel() {
        this.service = RetrofitServiceManager.getInstance().create(LoginModelService.class);
    }

    public Observable<BaseResponse<Object>> login(LoginBody body) {
        return observe(service.login(body));
    }

    interface LoginModelService {

        @POST("v3/login341")
        Observable<BaseResponse<Object>> login(@Body LoginBody body);

    }

}
