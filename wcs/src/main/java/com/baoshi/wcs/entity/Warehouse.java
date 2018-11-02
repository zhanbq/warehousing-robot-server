package com.baoshi.wcs.entity;

import java.io.Serializable;

public class Warehouse implements Serializable {

    /**
     * 主键
     */
    String id;

    /**
     * 仓库编码 定位物理仓库(北京仓库,广州仓库...)
     */
    String warehouseCode;

    /**
     * 工作区
     */
    String workArea;

    /**
     * 货主 商家
     */
    String goodsOwner;

    /**
     * 巷道
     */
    String laneWay;

    /**
     * 库位号
     */
    String storeroomCode;

    /**
     * 库位类型
     */
    String storeroomType;

    /**
     * 备用字段1
     */
    String backupFieldOne;

    /**
     * 备用字段2
     */
    String backupFieldTwo;

    /**
     * 备用字段3
     */
    String backupFieldThree;

    public Warehouse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
