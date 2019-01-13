package com.baoshi.wcs.common.wms;

import java.io.Serializable;

public class Sku implements Serializable {


    String ganme;

    String outerid;

    String barcode;

    String amount;

    String unit;

    public String getGanme() {
        return ganme;
    }

    public void setGanme(String ganme) {
        this.ganme = ganme;
    }

    public String getOuterid() {
        return outerid;
    }

    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
