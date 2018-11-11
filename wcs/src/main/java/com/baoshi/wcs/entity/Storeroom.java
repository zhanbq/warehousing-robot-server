package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

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
     * 备用字段1
     */
    private String backupFieldOne;

    private String backupFieldTwo;

    private String backupFieldThree;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人id
     */
    private Integer modifier;

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
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "Storeroom{" +
        "warehouseCode=" + warehouseCode +
        ", workArea=" + workArea +
        ", goodsOwner=" + goodsOwner +
        ", laneWay=" + laneWay +
        ", storeroomCode=" + storeroomCode +
        ", storeroomType=" + storeroomType +
        ", backupFieldOne=" + backupFieldOne +
        ", backupFieldTwo=" + backupFieldTwo +
        ", backupFieldThree=" + backupFieldThree +
        ", modifyTime=" + modifyTime +
        ", modifier=" + modifier +
        "}";
    }
}
