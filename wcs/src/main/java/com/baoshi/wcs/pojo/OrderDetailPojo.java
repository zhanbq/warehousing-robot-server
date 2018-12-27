package com.baoshi.wcs.pojo;

import java.io.Serializable;


public class OrderDetailPojo implements Serializable {


    private static final long serialVersionUID = 4522969772098818529L;
    /**
     * 发货单号
     */
    String invoiceNo;

    /**
     * 收货人姓名
     */
    String consigneeName;

    /**
     * 收货人地址
     */
    String consigneeAddr;

    /**
     * 收货人电话
     */
    String consigneePhone;

    /**
     * 商品名称
     */
    String itemName;

    /**
     * 商品编码
     */
    String itemSkuCode;

    /**
     * 商品条码
     */
    String itemBarCode;

    /**
     * 商品数量
     */
    int itemAmount;

    public OrderDetailPojo() {
    }

    public OrderDetailPojo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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

    public int getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int itemAmount) {
        this.itemAmount = itemAmount;
    }
}
