package com.baoshi.wcs.common.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseException extends RuntimeException implements Serializable {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final long serialVersionUID = -6060609260849161002L;
    /**
     * 状态码
     */
    private String code;
    private String msg;

    /**
     *返回的信息
     */
    private Object data;

    /**
     * 引发错误的数据
     */
    private Object exData;

    public BaseException(String msg) {
        this.msg = msg;
        logger.error(msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
