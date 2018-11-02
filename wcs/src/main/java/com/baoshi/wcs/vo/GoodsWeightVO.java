package com.baoshi.wcs.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

public class GoodsWeightVO implements Serializable {

    private static final long serialVersionUID = -2508360618947273381L;
    /**
     * 物流单号
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
    String barCode;

    /**
     * 重量
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
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

    public interface GoodsWeightValidator{

    }

}
