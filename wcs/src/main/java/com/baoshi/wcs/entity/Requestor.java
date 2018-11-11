package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 调用方/请求方请信息
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class Requestor extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    /**
     * 修改时间
     */
    private Date modifyTime;

    private Integer modifier;

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
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "Requestor{" +
        "requestor=" + requestor +
        ", eventCode=" + eventCode +
        ", reqDate=" + reqDate +
        ", modifyTime=" + modifyTime +
        ", modifier=" + modifier +
        "}";
    }
}
