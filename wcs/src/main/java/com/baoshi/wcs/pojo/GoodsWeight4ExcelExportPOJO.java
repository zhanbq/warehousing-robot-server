package com.baoshi.wcs.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.Date;

public class GoodsWeight4ExcelExportPOJO implements Serializable {

    /**
     * 物流单号
     */
    @ExcelProperty(value = "快递单号",index = 1)
    private String barCode;

    /**
     * 包裹重量
     */
    @ExcelProperty(value = "重量",index = 7)
    private Double weight;

    @ExcelIgnore
    String palletid;

    /**
     * 货主
     */
    @ExcelProperty(value = "货主",index = 0)
    String customer;

    @ExcelProperty(value = "收件人",index = 4)
    String consigneename;

    @ExcelProperty(value = "承运商",index = 5)
    String carrierId;

    @ExcelIgnore
    String taskId;
    /**
     * 扫码称重机id
     */
    @ExcelIgnore
    String gwRobotId;

    @ExcelProperty(value = "发货单号",index = 2)
    String orderNo;

    /**
     * 包裹长度 用于计算体积
     */
    @ExcelIgnore
    Double length;

    /**
     * 包裹宽度 用于计算体积
     */
    @ExcelIgnore
    Double width;

    /**
     * 包裹高度 用于计算体积
     */
    @ExcelIgnore
    Double height;

    @ExcelProperty(value = "体积",index = 9)
    Double volume;

    /**
     * 纸箱名称
     */
    @ExcelProperty(value = "纸箱名称",index = 10)
    String cartonName;

    /**
     * 长宽高
     */
    @ExcelProperty(value = "长宽高",index = 8)
    String threeHigh;

    @ExcelProperty(value = "操作时间",index = 3)
    Date modifyTime;

    @ExcelProperty(value = "设备",index = 6)
    String gwRobotName;

    public GoodsWeight4ExcelExportPOJO() {
    }

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

    public String getGwRobotId() {
        return gwRobotId;
    }

    public void setGwRobotId(String gwRobotId) {
        this.gwRobotId = gwRobotId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getCartonName() {
        return cartonName;
    }

    public void setCartonName(String cartonName) {
        this.cartonName = cartonName;
    }

    public String getThreeHigh() {
        if(this.height!=null && this.width!=null && this.length!=null){
            this.threeHigh = this.length+"*"+this.width+"*"+this.height;
        }
        return threeHigh;
    }

    public void setThreeHigh(String threeHigh) {
        this.threeHigh = threeHigh;

    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getGwRobotName() {
        return gwRobotName;
    }

    public void setGwRobotName(String gwRobotName) {
        this.gwRobotName = gwRobotName;
    }
}
