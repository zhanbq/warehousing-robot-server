package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 货物/包裹信息表
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class GoodsWeight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 物流单号
     */
    private String barCode;

    /**
     * 包裹重量
     */
    private Double weight;

    String palletid;

    String customer;

    String consigneename;

    String carrierId;

    String taskId;
    /**
     * 扫码称重机id
     */
    String gwRobotId;

    String orderNo;

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
    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public String getGwRobotId() {
        return gwRobotId;
    }

    public void setGwRobotId(String gwRobotId) {
        this.gwRobotId = gwRobotId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getPalletid() {
        return palletid;
    }

    public void setPalletid(String palletid) {
        this.palletid = palletid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
