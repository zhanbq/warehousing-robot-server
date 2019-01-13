package com.baoshi.wcs.common.wms;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    String id;

    String cname;

    String ordercode;

    String expresscode;

    String sendcode;

    String prov;

    String city;

    String district;

    String address;

    String tousername;

    String tophone;

    String wavecode;

    String flowstepname;

    List<Sku> skus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getExpresscode() {
        return expresscode;
    }

    public void setExpresscode(String expresscode) {
        this.expresscode = expresscode;
    }

    public String getSendcode() {
        return sendcode;
    }

    public void setSendcode(String sendcode) {
        this.sendcode = sendcode;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }

    public String getTophone() {
        return tophone;
    }

    public void setTophone(String tophone) {
        this.tophone = tophone;
    }

    public String getWavecode() {
        return wavecode;
    }

    public void setWavecode(String wavecode) {
        this.wavecode = wavecode;
    }

    public String getFlowstepname() {
        return flowstepname;
    }

    public void setFlowstepname(String flowstepname) {
        this.flowstepname = flowstepname;
    }

    public List<Sku> getSkus() {
        return skus;
    }

    public void setSkus(List<Sku> skus) {
        this.skus = skus;
    }
}
