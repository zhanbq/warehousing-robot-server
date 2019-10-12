package com.baoshi.wcs.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baoshi.wcs.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-10-08
 */
public class ShipperCarton extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ExcelProperty(index = 0)
    private String shipperName;
    @ExcelProperty(index = 1)
    private String cartonName;
    @ExcelProperty(index = 2)
    private Double cartonLength;
    @ExcelProperty(index = 3)
    private Double cartonWidth;
    @ExcelProperty(index = 4)
    private Double cartonHeight;

    private String cartonVolume;

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }
    public String getCartonName() {
        return cartonName;
    }

    public void setCartonName(String cartonName) {
        this.cartonName = cartonName;
    }
    public Double getCartonLength() {
        return cartonLength;
    }

    public void setCartonLength(Double cartonLength) {
        this.cartonLength = cartonLength;
    }
    public Double getCartonWidth() {
        return cartonWidth;
    }

    public void setCartonWidth(Double cartonWidth) {
        this.cartonWidth = cartonWidth;
    }
    public Double getCartonHeight() {
        return cartonHeight;
    }

    public void setCartonHeight(Double cartonHeight) {
        this.cartonHeight = cartonHeight;
    }
    public String getCartonVolume() {
        return cartonVolume;
    }

    public void setCartonVolume(String cartonVolume) {
        this.cartonVolume = cartonVolume;
    }

    @Override
    public String toString() {
        return "ShipperCarton{" +
        "shipperName=" + shipperName +
        ", cartonName=" + cartonName +
        ", cartonLength=" + cartonLength +
        ", cartonWidth=" + cartonWidth +
        ", cartonHeight=" + cartonHeight +
        ", cartonVolume=" + cartonVolume +
        "}";
    }
}
