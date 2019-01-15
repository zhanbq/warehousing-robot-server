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
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        String wsUrl = "http://baoshi.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";
        Client client = dcf.createClient(wsUrl);
        String method = "setOrderWeight";//webservice的方法名
        Object[] result = null;
//        String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//                "<getOrders>\n" +
//                "<tid>20140318155513001</tid>\n" +
//                "<cid>d03821229a964f189effdbb9360fdc13</cid>\n" +
//                "<pwd>36ef934cdc6544f1b8e59eda75c14fde</pwd>\n" +
//                "<warehouseid>cf3c23f41a6142fa9e4d011b71ed8018</warehouseid>\n" +
//                "<sendcode>75124050026194</sendcode>\n" +
//                "</getOrders>";
        String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<setOrderWeight>\n" +
                "<tid>20140318155513001</tid>\n" +
                "<cid>33d231fa134e47e3bc112ae06eaedc5c</cid>\n" +
                "<pwd>37de5907f2964e52846d9d2213eea3b4</pwd>\n" +
                "<warehouseid>fac5d7fd03e044698a9df1dcaab08006</warehouseid>\n" +
                "<sendcode>75124050026194</sendcode>\n" +
                "<weight>2.3</weight>\n" +
                "<unit>kg</unit>\n" +
                "</setOrderWeight>";
        try {
            result = client.invoke(method, reqXml);//调用webservice
            System.out.println(JSON.toJSONString(result));
            System.out.println(result[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
