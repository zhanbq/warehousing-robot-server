package com.baoshi.wcs.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date beginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date endTime;

    /**
     * 纸箱名称
     */
    String cartonName;

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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCartonName() {
        return cartonName;
    }

    public void setCartonName(String cartonName) {
        this.cartonName = cartonName;
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
