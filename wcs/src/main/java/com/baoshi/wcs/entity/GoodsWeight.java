package com.baoshi.wcs.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
//@Alias(value = "goodsWeight")
public class GoodsWeight implements Serializable {
    Long id;
    String barCode;

    Double weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
