package com.baoshi.wcs.entity.newwms;

import java.io.Serializable;

public class OrderVO4NewWms extends BaseNewWMS implements Serializable {

    /**
     * 任务订单号
     */
    String Orderno;

    /**
     * 发货单号
     */
    String palletid;


    /**
     * 快递单号
     */
    String SOReference5;


    /**
     * 货主
     */
    String customer;

    /**
     *收货人姓名
     */
    String consigneename;

    /**
     * 承运商
     */
    String CarrierID;

    public String getOrderno() {
        return Orderno;
    }

    public void setOrderno(String orderno) {
        Orderno = orderno;
    }

    public String getPalletid() {
        return palletid;
    }

    public void setPalletid(String palletid) {
        this.palletid = palletid;
    }

    public String getSOReference5() {
        return SOReference5;
    }

    public void setSOReference5(String SOReference5) {
        this.SOReference5 = SOReference5;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getCarrierID() {
        return CarrierID;
    }

    public void setCarrierID(String carrierID) {
        CarrierID = carrierID;
    }

    public String getTASKID() {
        return TASKID;
    }

    public void setTASKID(String TASKID) {
        this.TASKID = TASKID;
    }
}
