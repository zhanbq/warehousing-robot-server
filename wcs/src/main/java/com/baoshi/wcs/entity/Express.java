package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-12-26
 */
public class Express extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 发货单号/快递单号
     */
    private String invoiceNo;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人地址
     */
    private String consigneeAddr;

    /**
     * 收货人电话
     */
    private String consigneePhone;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品编码sku码
     */
    private String itemSkuCode;

    /**
     * 商品条码
     */
    private String itemBarCode;

    /**
     * 商品数量
     */
    private Integer itemAmount;

    private String owner;

    private String waveNo;

    private String waveType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }
    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemSkuCode() {
        return itemSkuCode;
    }

    public void setItemSkuCode(String itemSkuCode) {
        this.itemSkuCode = itemSkuCode;
    }
    public String getItemBarCode() {
        return itemBarCode;
    }

    public void setItemBarCode(String itemBarCode) {
        this.itemBarCode = itemBarCode;
    }
    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getWaveNo() {
        return waveNo;
    }

    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }
    public String getWaveType() {
        return waveType;
    }

    public void setWaveType(String waveType) {
        this.waveType = waveType;
    }

    @Override
    public String toString() {
        return "Express{" +
        "orderNo=" + orderNo +
        ", invoiceNo=" + invoiceNo +
        ", consigneeName=" + consigneeName +
        ", consigneeAddr=" + consigneeAddr +
        ", consigneePhone=" + consigneePhone +
        ", itemName=" + itemName +
        ", itemSkuCode=" + itemSkuCode +
        ", itemBarCode=" + itemBarCode +
        ", itemAmount=" + itemAmount +
        ", owner=" + owner +
        ", waveNo=" + waveNo +
        ", waveType=" + waveType +
        "}";
    }
}
