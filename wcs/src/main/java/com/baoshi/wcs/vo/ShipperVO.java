package com.baoshi.wcs.vo;


import com.baoshi.wcs.common.validator.ShipperValidationGroup;
import com.baoshi.wcs.entity.ShipperCarton;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShipperVO implements Serializable {

    /**
     * 货主名
     */
    @NotNull(groups = {ShipperValidationGroup.class},message = "货主姓名不能为空")
    private String shipperName;

    /**
     * 是否参与计数Y参与/N不参与
     */
    private String whetherTheCount = "N";

    List<ShipperCarton> shipperCartonList = new ArrayList<ShipperCarton>();

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

    public List<ShipperCarton> getShipperCartonList() {
        return shipperCartonList;
    }

    public void setShipperCartonList(List<ShipperCarton> shipperCartonList) {
        this.shipperCartonList = shipperCartonList;
    }

    @Override
    public String toString() {
        return "Shipper{" +
                "shipperName=" + shipperName +
                ", whetherTheCount=" + whetherTheCount +
                "}";
    }

}
