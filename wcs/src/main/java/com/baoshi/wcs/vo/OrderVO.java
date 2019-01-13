package com.baoshi.wcs.vo;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.pojo.OrderDetailPojo;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public static void main(String[] args) {
//        OrderVO orderVO = new OrderVO();
//        orderVO.setOwner("123");
//        orderVO.setWaveNo("1dsad");
//        orderVO.setWaveType("dsadsa");
//        orderVO.setOrderNo("od-dsadmkl132mkl");
//        ArrayList<OrderDetailPojo> orderDetailPojos = new ArrayList<>();
//        OrderDetailPojo orderDetailPojo = new OrderDetailPojo();
//        orderDetailPojo.setConsigneeAddr("北京");
//        orderDetailPojo.setConsigneeName("李四");
//        orderDetailPojo.setConsigneePhone("15788889999");
//
//        orderDetailPojo.setInvoiceNo("123123123");
//
//        orderDetailPojo.setItemAmount(137);
//        orderDetailPojo.setItemBarCode("dsae213da13d4r5");
//        orderDetailPojo.setItemName("旺仔小馒头");
//        orderDetailPojo.setItemSkuCode("12315-123dsa-cxdass");
//        OrderDetailPojo orderDetailPojo2 = new OrderDetailPojo();
//        orderDetailPojo2.setConsigneeAddr("成都");
//        orderDetailPojo2.setConsigneeName("小明");
//        orderDetailPojo2.setConsigneePhone("15766667777");
//
//        orderDetailPojo2.setInvoiceNo("123123123");
//
//        orderDetailPojo2.setItemAmount(123);
//        orderDetailPojo2.setItemBarCode("dsae213da13d4r5");
//        orderDetailPojo2.setItemName("旺仔小馒头");
//        orderDetailPojo2.setItemSkuCode("12315-123dsa-cxdass");
//        orderDetailPojos.add(orderDetailPojo);
//        orderDetailPojos.add(orderDetailPojo2);
//        orderVO.setOrderDetailList(orderDetailPojos);
//        System.out.println(JSON.toJSONString(orderVO));
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        String wsUrl = "http://demo.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";

        String wsUrl = "http://test3.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";
        Client client = dcf.createClient(wsUrl);
        String method = "getOrders";//webservice的方法名
        Object[] result = null;
        String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<getOrders>\n" +
                "<tid>20140318155513001</tid>\n" +
                "<cid>d03821229a964f189effdbb9360fdc13</cid>\n" +
                "<pwd>36ef934cdc6544f1b8e59eda75c14fde</pwd>\n" +
                "<warehouseid>cf3c23f41a6142fa9e4d011b71ed8018</warehouseid>\n" +
                "<ordercode>20190104185721368</ordercode>\n" +
                "</getOrders>";
//        String reqXml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//        reqXml=reqXml+"<getOrders>";
//        reqXml=reqXml+"<tid>20140905155513001</tid>";
//        reqXml=reqXml+"<cid>5f8572e81f074a26bd5006c222546edb</cid>";
//        reqXml=reqXml+"<pwd>19a1cce91b6e48a1bf038ac979ab7e64</pwd>";
//        reqXml=reqXml+"<outerid>200522428</outerid>";
//        reqXml=reqXml+"</getOrders>";
        try {
            result = client.invoke(method, reqXml);//调用webservice
            System.out.println(JSON.toJSONString(result));
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
