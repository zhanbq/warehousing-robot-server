package com.baoshi.wcs.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class GoodsWeightVO implements Serializable {

    private static final long serialVersionUID = -2508360618947273381L;
    /**
     * 物流单号=快递单号
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
    String barCode;

    /**
     * 重量
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
    Double weight;

    String key;

    String id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public interface GoodsWeightValidator{

    }

}
