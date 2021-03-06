package com.baoshi.wcs.entity;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 库位信息
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class Storeroom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 工作区
     */
    private String workArea;

    /**
     * 货主
     */
    private String goodsOwner;

    /**
     * 巷道
     */
    private String laneWay;

    /**
     * 库位号
     */
    private String storeroomCode;

    /**
     * 库位类型
     */
    private String storeroomType;

    /**
     * 库位状态  0 空库位; 1 未装满 3 满库位
     */
    private String status;

    /**
     * 改库位 绑定的容器类型 一个库位 只存放一种类型的库位
     */
    private String containerType;

    /**
     * 一个库位能放的容器数量
     */
    private BigDecimal containerAmount;

    /**
     * 备用字段1
     */
    private String backupFieldOne;

    private String backupFieldTwo;

    private String backupFieldThree;


    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    public String getWorkArea() {
        return workArea;
    }

    public void setWorkArea(String workArea) {
        this.workArea = workArea;
    }
    public String getGoodsOwner() {
        return goodsOwner;
    }

    public void setGoodsOwner(String goodsOwner) {
        this.goodsOwner = goodsOwner;
    }
    public String getLaneWay() {
        return laneWay;
    }

    public void setLaneWay(String laneWay) {
        this.laneWay = laneWay;
    }
    public String getStoreroomCode() {
        return storeroomCode;
    }

    public void setStoreroomCode(String storeroomCode) {
        this.storeroomCode = storeroomCode;
    }
    public String getStoreroomType() {
        return storeroomType;
    }

    public void setStoreroomType(String storeroomType) {
        this.storeroomType = storeroomType;
    }
    public String getBackupFieldOne() {
        return backupFieldOne;
    }

    public void setBackupFieldOne(String backupFieldOne) {
        this.backupFieldOne = backupFieldOne;
    }
    public String getBackupFieldTwo() {
        return backupFieldTwo;
    }

    public void setBackupFieldTwo(String backupFieldTwo) {
        this.backupFieldTwo = backupFieldTwo;
    }
    public String getBackupFieldThree() {
        return backupFieldThree;
    }

    public void setBackupFieldThree(String backupFieldThree) {
        this.backupFieldThree = backupFieldThree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public BigDecimal getContainerAmount() {
        return containerAmount;
    }

    public void setContainerAmount(BigDecimal containerAmount) {
        this.containerAmount = containerAmount;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
