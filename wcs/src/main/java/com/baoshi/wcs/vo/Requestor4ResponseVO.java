package com.baoshi.wcs.vo;

import java.util.Date;

public class Requestor4ResponseVO {

    /**
     * 请求调用方
     */
    private String requestor;

    /**
     * 事件编码/业务编码(设备号+年月日时分秒+6位随机数)
     */
    private String eventCode;

    /**
     * 请求时间
     */
    private Date reqDate;

    private Date resDate;

    public Requestor4ResponseVO(String requestor, String eventCode, Date reqDate) {
        this.requestor = requestor;
        this.eventCode = eventCode;
        this.reqDate = reqDate;
    }

    public Requestor4ResponseVO() {
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }
}
