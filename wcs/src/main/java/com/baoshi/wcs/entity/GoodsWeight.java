package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 货物/包裹信息表
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class GoodsWeight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物流单号
     */
    private String barCode;

    /**
     * 包裹重量
     */
    private Double weight;

    /**
     * 修改人id
     */
    private Integer modifier;

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
    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "GoodsWeight{" +
        "barCode=" + barCode +
        ", weight=" + weight +
        ", modifier=" + modifier +
        "}";
    }
}
