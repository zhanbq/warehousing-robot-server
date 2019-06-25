package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 货架 层管理
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
public class Layer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 层数--层高1 层高2
     */
    private Integer layerNum;

    /**
     * 层高m
     */
    private Double height;

    /**
     * 单位
     */
    private String unit;

    /**
     * 货架id
     */
    private Integer shelvesId;

    public Integer getLayerNum() {
        return layerNum;
    }

    public void setLayerNum(Integer layerNum) {
        this.layerNum = layerNum;
    }
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Integer getShelvesId() {
        return shelvesId;
    }

    public void setShelvesId(Integer shelvesId) {
        this.shelvesId = shelvesId;
    }

    @Override
    public String toString() {
        return "Layer{" +
        "layerNum=" + layerNum +
        ", height=" + height +
        ", unit=" + unit +
        ", shelvesId=" + shelvesId +
        "}";
    }
}
