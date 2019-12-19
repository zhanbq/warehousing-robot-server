package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanbq
 * @since 2019-11-01
 */
public class Shipper extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 货主名
     */
    private String shipperName;

    /**
     * 是否参与计数Y参与/N不参与
     */
    private String whetherTheCount = "N";

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }
    public String getWhetherTheCount() {
        return whetherTheCount;
    }

    public void setWhetherTheCount(String whetherTheCount) {
        this.whetherTheCount = whetherTheCount;
    }

    @Override
    public String toString() {
        return "Shipper{" +
        "shipperName=" + shipperName +
        ", whetherTheCount=" + whetherTheCount +
        "}";
    }
}
