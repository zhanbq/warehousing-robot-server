package com.baoshi.wcs.common.wms;

import java.io.Serializable;

public class WMSServiceResponse<T> implements Serializable {

    String tid = "";
    String rc = "";
    String rm = "";
    
    T data;
    
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
