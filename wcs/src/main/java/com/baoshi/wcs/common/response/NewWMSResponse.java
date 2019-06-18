package com.baoshi.wcs.common.response;

import org.slf4j.Logger;

import java.io.Serializable;

public class NewWMSResponse<T> implements Serializable {

    String flag;

    String code;

    String message;

    T data;

    public void failed(String message) {
        this.code = "555";
        this.flag = "W";
    }

    public void failed(String message, Logger logger) {
        this.code = "555";
        this.flag = "W";
        logger.error(message);
    }

    public void success(T data) {
        this.flag = "Y";
        this.message = "成功";
        this.data = data;
        this.code = "000";
    }


    public NewWMSResponse() {
    }

    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
