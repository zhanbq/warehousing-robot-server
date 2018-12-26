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
    String consignAddr;

    /**
     * 收货人电话
     */
    String consignPhnoe;

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
    String itemAmount;

    public OrderDetailPojo() {
    }

    public OrderDetailPojo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public OrderDetailPojo(String consigneeName, String consignAddr, String consignPhnoe, String itemName, String itemSkuCode, String itemBarCode, String itemAmount) {
        this.consigneeName = consigneeName;
        this.consignAddr = consignAddr;
        this.consignPhnoe = consignPhnoe;
        this.itemName = itemName;
        this.itemSkuCode = itemSkuCode;
        this.itemBarCode = itemBarCode;
        this.itemAmount = itemAmount;
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

    public String getConsignAddr() {
        return consignAddr;
    }

    public void setConsignAddr(String consignAddr) {
        this.consignAddr = consignAddr;
    }

    public String getConsignPhnoe() {
        return consignPhnoe;
    }

    public void setConsignPhnoe(String consignPhnoe) {
        this.consignPhnoe = consignPhnoe;
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

    public String getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(String itemAmount) {
        this.itemAmount = itemAmount;
    }
}
