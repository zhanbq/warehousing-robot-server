package com.baoshi.wcs.vo;

import java.io.Serializable;

public class BaseWMSVO implements Serializable {

    private String tid;

    private String cid;

    private String pwd;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
