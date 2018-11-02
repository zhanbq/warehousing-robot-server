package com.baoshi.wcs.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 通用返回值
 * @param <T>
 */
public final class ApiResponse<T> implements Serializable {

    String msg;

    int code;

    T data;

    public ApiResponse() {
    }

    public ApiResponse(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
