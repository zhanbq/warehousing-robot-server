package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 货架信息管理
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
public class Shelves extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 货架设备名称
     */
    private String name;

    /**
     * 设备类型0分播墙;1自动立库
     */
    private Integer type;

    /**
     * 货架设备编号
     */
    private String code;

    /**
     * 库区
     */
    private Integer warehouseAreaId;

    /**
     * 巷道编号
     */
    private Integer roadwayNo;

    /**
     * 容器堆叠数量
     */
    private Integer stackNum;

    /**
     * 容器类型
     */
    private String containerType;

    /**
     * 容器层数
     */
    private Integer pliesNum;

    /**
     * 列数
     */
    private Integer columnsNum;

    /**
     * 层数管理id
     */
    private Integer layerId;

    /**
     * 仓库id
     */
    private Integer warehouseId;

    /**
     * 列数管理id
     */
    private Integer columnsId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Integer getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(Integer warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }
    public Integer getRoadwayNo() {
        return roadwayNo;
    }

    public void setRoadwayNo(Integer roadwayNo) {
        this.roadwayNo = roadwayNo;
    }
    public Integer getStackNum() {
        return stackNum;
    }

    public void setStackNum(Integer stackNum) {
        this.stackNum = stackNum;
    }
    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }
    public Integer getPliesNum() {
        return pliesNum;
    }

    public void setPliesNum(Integer pliesNum) {
        this.pliesNum = pliesNum;
    }
    public Integer getColumnsNum() {
        return columnsNum;
    }

    public void setColumnsNum(Integer columnsNum) {
        this.columnsNum = columnsNum;
    }
    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }
    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
    public Integer getColumnsId() {
        return columnsId;
    }

    public void setColumnsId(Integer columnsId) {
        this.columnsId = columnsId;
    }

    @Override
    public String toString() {
        return "Shelves{" +
        "name=" + name +
        ", type=" + type +
        ", code=" + code +
        ", warehouseAreaId=" + warehouseAreaId +
        ", roadwayNo=" + roadwayNo +
        ", stackNum=" + stackNum +
        ", containerType=" + containerType +
        ", pliesNum=" + pliesNum +
        ", columnsNum=" + columnsNum +
        ", layerId=" + layerId +
        ", warehouseId=" + warehouseId +
        ", columnsId=" + columnsId +
        "}";
    }
}
