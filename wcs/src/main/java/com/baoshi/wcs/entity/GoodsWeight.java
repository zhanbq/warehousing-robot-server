package com.baoshi.wcs.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
//@Alias(value = "goodsWeight")
public class GoodsWeight implements Serializable {

    String barCode;

    Double weight;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
