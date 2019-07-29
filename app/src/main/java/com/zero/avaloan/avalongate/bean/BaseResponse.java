package com.zero.avaloan.avalongate.bean;

public class BaseResponse<T> {

    public int code;

    public String message;

    public T data;

    public boolean isSuccess() {
        return code == 200;
    }

}
