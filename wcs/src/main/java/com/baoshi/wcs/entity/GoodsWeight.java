package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

//@Alias(value = "goodsWeight")
public class GoodsWeight extends BaseEntity implements Serializable {
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
