package com.baoshi.wcs.pojo;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

public class ShipperCartonPojo implements Serializable {

    private static final long serialVersionUID = -8645406023806590638L;

    private String shipperName;
    private String cartonName;
    private Double cartonLength;
    private Double cartonWidth;
    private Double cartonHeight;

    private String cartonVolume;

    /**
     * 纸箱边长测量误差
     */
    private double digitalErrorValue;

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

    public double getDigitalErrorValue() {
        return digitalErrorValue;
    }

    public void setDigitalErrorValue(double digitalErrorValue) {
        this.digitalErrorValue = digitalErrorValue;
    }
}
