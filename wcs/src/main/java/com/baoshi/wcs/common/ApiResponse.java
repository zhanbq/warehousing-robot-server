package com.baoshi.wcs.common;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

    String msg;

    String status;

    T data;

    public void success(T data,String msg){
        this.data = data;
        status = "success";
        msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
