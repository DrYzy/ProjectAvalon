package com.zero.avaloan.avalongate.net;


import com.zero.avaloan.avalongate.bean.BaseResponse;

import io.reactivex.functions.Function;

public class PayLoad<T> implements Function<BaseResponse<T>, BaseResponse<T>> {
    
    private static final String TAG = "PayLoad";

    @Override
    public BaseResponse<T> apply(BaseResponse<T> response) throws Exception {
        if (!response.isSuccess()) {
            /* 服务器端返回errno失败 */
            throw new ServerException(response.code, response.message);
        }
        /* 成功获取 */
        return response;
    }

}
