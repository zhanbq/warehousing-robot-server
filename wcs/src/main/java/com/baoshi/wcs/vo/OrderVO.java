package com.baoshi.wcs.vo;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.pojo.OrderDetailPojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderVO implements Serializable {

    private static final long serialVersionUID = -368762772344763179L;
    /**
     * 波次号
     */
    String waveNo;

    /**
     * 波次类型
     */
    String waveType;

    /**
     * 货主
     */
    String owner;

    /**
     * 订单号
     */
    String orderNo;

    /**
     * 订单信息
     */
    List<OrderDetailPojo> orderDetailList = new ArrayList<>();

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<OrderDetailPojo> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailPojo> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public static void main(String[] args) {
        OrderVO orderVO = new OrderVO();
        orderVO.setOwner("123");
        orderVO.setWaveNo("1dsad");
        orderVO.setWaveType("dsadsa");
        ArrayList<OrderDetailPojo> orderDetailPojos = new ArrayList<>();
        OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
        orderDetailPojo.setConsignAddr("北京");
        orderDetailPojo.setConsigneeName("李四");

        orderDetailPojos.add(orderDetailPojo);
        orderVO.setOrderDetailList(orderDetailPojos);
        System.out.println(JSON.toJSONString(orderVO));
    }
}
