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

    /**
     * 包裹长度 计算体积用
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
    Double length;

    /**
     * 包裹宽度 计算体积用
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
    Double width;

    /**
     * 包裹高度 计算体积用
     */
    @NotEmpty(groups = {GoodsWeightValidator.class})
    Double height;

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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取包裹体积
     * @return
     */
//    public double getVolume() {
//        BigDecimal lengthDecimal = new BigDecimal(this.length);
//        BigDecimal widthDecimal = new BigDecimal(this.width);
//        BigDecimal heightDecimal = new BigDecimal(this.height);
//        return lengthDecimal.multiply(widthDecimal).multiply(heightDecimal).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
//    }

    public interface GoodsWeightValidator{

    }

}
