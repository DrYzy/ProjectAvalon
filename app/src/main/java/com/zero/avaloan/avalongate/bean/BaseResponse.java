package com.zero.avaloan.avalongate.bean;

import com.google.gson.annotations.Expose;

public class BaseResponse<T> {

//    public int code;
//
//    public String message;
//
//    public T data;
//
//    public boolean isSuccess() {
//        return code == 200;
//    }
    /**
     * retrofit 会直接通过GSON将返回数据转化为对象
     * 要求对象内存在的字段和返回字段必须一一对应
     * 如果存在  是否为空值不一定 数据类型不一定的内部对象
     * 数据类型用泛型T代替
     * */
    @Expose
    public String resCode;
    @Expose
    public String resMsg;
    @Expose
    public T content;

//    public class ContentInfo{
//        public String displayInfo;
//
//        public int loginStatus;
//
//        public String userId;
//
//        public boolean operationResult;
//
//        public String token;
//
//    }
}
