package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 包裹容器-库位绑定表 允许沉余多个关键字段
 * </p>
 *
 * @author jobob
 * @since 2018-11-12
 */
public class ContainerStoreroom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 包裹容器id
     */
    private String containerCode;

    /**
     * 容器类型
     */
    private String containerType;

    /**
     * 库位号
     */
    private String storeroomCode;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }
    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }
    public String getStoreroomCode() {
        return storeroomCode;
    }

    public void setStoreroomCode(String storeroomCode) {
        this.storeroomCode = storeroomCode;
    }
    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    @Override
    public String toString() {
        return "ContainerStoreroom{" +
        "containerCode=" + containerCode +
        ", containerType=" + containerType +
        ", storeroomCode=" + storeroomCode +
        ", warehouseCode=" + warehouseCode +
        "}";
    }
}
