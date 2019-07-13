package com.baoshi.wcs.common.utils;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.wms.Order;
import com.baoshi.wcs.common.wms.Sku;
import com.baoshi.wcs.common.wms.WMSServiceResponse;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 利用反射和JDOM解析xml成bean/List
 * @Date: 2018/1/22 16:06
 */
public class Xml2BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(Xml2BeanUtil.class);

    //可以在中括号内加上任何想要替换的字符
    private static final String SPECIAL_SYMBOLS_$ = "&";
    public static WMSServiceResponse getBaseWMSResp(String xml) {
        WMSServiceResponse<List<Order>> wmsServiceResponse = new WMSServiceResponse<>();
        ArrayList<Order> orders4Resp = new ArrayList<>();
        Document document = null;
        try {
            // 将字符串转为XML
            document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            String tid = root.elementText("tid");
            wmsServiceResponse.setTid(tid);
            String rc = root.elementTextTrim("rc");
            wmsServiceResponse.setRc(rc);
            String rm = root.elementTextTrim("rm");
            wmsServiceResponse.setRm(rm);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        wmsServiceResponse.setData(orders4Resp);
        return wmsServiceResponse;
    }
    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    public static WMSServiceResponse<List<Order>> getOrderDetails(String xml) {
        WMSServiceResponse<List<Order>> wmsServiceResponse = new WMSServiceResponse<>();
        ArrayList<Order> orders4Resp = new ArrayList<>();
        Document document = null;
        try {
            Pattern p = Pattern.compile(SPECIAL_SYMBOLS_$);

            Matcher m = p.matcher(xml);//这里把想要替换的字符串传进来

            xml = m.replaceAll("").trim();
            // 将字符串转为XML
            document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            String tid = root.elementText("tid");
            wmsServiceResponse.setTid(tid);
            String rc = root.elementTextTrim("rc");
            wmsServiceResponse.setRc(rc);
            String rm = root.elementTextTrim("rm");
            wmsServiceResponse.setRm(rm);
            Element ordersChild = root.element("orders");

            if(null == ordersChild){
                return wmsServiceResponse;
            }
            List<Element> orders = ordersChild.elements();
            if(CollectionUtils.isEmpty(orders)){
                return wmsServiceResponse;
            }

            for(Element order : orders){
                Order orderResp = new Order();
                String id = order.elementTextTrim("id");
                orderResp.setId(id);
                String cname = order.elementTextTrim("cname");
                orderResp.setCname(cname);
                String ordercode = order.elementTextTrim("ordercode");
                orderResp.setOrdercode(ordercode);
                String expresscode = order.elementTextTrim("expresscode");
                orderResp.setExpresscode(expresscode);
                String sendcode = order.elementTextTrim("sendcode");
                orderResp.setSendcode(sendcode);
                String prov = order.elementTextTrim("prov");
                orderResp.setProv(prov);
                String city = order.elementTextTrim("city");
                orderResp.setCity(city);
                String district = order.elementTextTrim("district");
                orderResp.setDistrict(district);
                String address = order.elementTextTrim("address");
                orderResp.setAddress(address);
                String tousername = order.elementTextTrim("tousername");
                orderResp.setTousername(tousername);
                String tophone = order.elementTextTrim("tophone");
                orderResp.setTophone(tophone);
                String wavecode = order.elementTextTrim("wavecode");
                orderResp.setWavecode(wavecode);
                String flowstepname = order.elementTextTrim("flowstepname");
                orderResp.setFlowstepname(flowstepname);
                Element skuChild = order.element("skus");

                if(null == skuChild){
                    orders4Resp.add(orderResp);
                    continue;
                }
                List<Element> skus = skuChild.elements();
                if(CollectionUtils.isEmpty(skus)){
                    orders4Resp.add(orderResp);
                    continue;
                }
                ArrayList<Sku> skus4Resp = new ArrayList<>();
                for(Element sku: skus){
                    Sku sku4Resp = new Sku();
                    String ganme = sku.elementTextTrim("ganme");
                    sku4Resp.setGanme(ganme);
                    String outerid = sku.elementTextTrim("outerid");
                    sku4Resp.setOuterid(outerid);
                    String barcode = sku.elementTextTrim("barcode");
                    sku4Resp.setBarcode(barcode);
                    String amount = sku.elementTextTrim("amount");
                    sku4Resp.setAmount(amount);
                    String unit = sku.elementTextTrim("unit");
                    sku4Resp.setUnit(unit);
                    skus4Resp.add(sku4Resp);
                }
                orderResp.setSkus(skus4Resp);
                orders4Resp.add(orderResp);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        wmsServiceResponse.setData(orders4Resp);
        return wmsServiceResponse;
    }

    public static String stripNonValidXMLChars(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        return str.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
    }

//    public static void main(String[] args) {
//
//        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<rt><tid>20140318155513001</tid>\n" +
//                "<rc>0000</rc><rm>成功</rm>\n" +
//                "<orders><order><id>d72a9e9129194d4aa8bc1af882758bcd</id>" +
//                "<cname>武迪实业</cname><ordercode>20190104185721368</ordercode>" +
//                "<expresscode>STO</expresscode><sendcode>null</sendcode>" +
//                "<prov>浙江省</prov><city>杭州市</city><district>滨江区</district>" +
//                "<address>testpacth20190104185721368</address><tousername>王收件人</tousername>" +
//                "<tophone>13858182349</tophone><wavecode>201901080064657</wavecode>" +
//                "<flowstepname>已运行</flowstepname><skus><sku><ganme>null</ganme>" +
//                "<outerid>csmrgbm7711</outerid><barcode>null</barcode><amount>2</amount><unit>J</unit></sku></skus></order></orders>" +
//                "</rt>";
//
//        /*
//         * Test2 test = new Test2(); test.readStringXml(xmlString);
//         */
//        WMSServiceResponse<List<Order>> wmsServiceResponse = getOrderDetails(xmlString);
//        logger.info(JSON.toJSONString(wmsServiceResponse));
//    }
}