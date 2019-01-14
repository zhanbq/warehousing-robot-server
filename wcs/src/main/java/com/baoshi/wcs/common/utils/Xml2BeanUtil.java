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

/**
 * @Description: 利用反射和JDOM解析xml成bean/List
 * @Date: 2018/1/22 16:06
 */
public class Xml2BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(Xml2BeanUtil.class);

    public void readStringXml(String xml) {
        Document doc = null;
        try {

            doc = DocumentHelper.parseText(xml);// 将字符串转为XML

            Element rootElt = doc.getRootElement();// 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

            Iterator iter = rootElt.elementIterator("orders"); // 获取根节点下的子节点head

            // 遍历head节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到head节点下的子节点title值
                System.out.println("title:" + title);

                Iterator iters = recordEle.elementIterator("script"); // 获取子节点head下的子节点script

                // 遍历Header节点下的Response节点
                while (iters.hasNext()) {

                    Element itemEle = (Element) iters.next();

                    String username = itemEle.elementTextTrim("username"); // 拿到head下的子节点script下的字节点username的值
                    String password = itemEle.elementTextTrim("password");

                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                }
            }
            Iterator iterss = rootElt.elementIterator("body"); ///获取根节点下的子节点body
            // 遍历body节点
            while (iterss.hasNext()) {

                Element recordEless = (Element) iterss.next();
                String result = recordEless.elementTextTrim("result"); // 拿到body节点下的子节点result值
                System.out.println("result:" + result);

                Iterator itersElIterator = recordEless.elementIterator("form"); // 获取子节点body下的子节点form
                // 遍历Header节点下的Response节点
                while (itersElIterator.hasNext()) {

                    Element itemEle = (Element) itersElIterator.next();

                    String banlce = itemEle.elementTextTrim("banlce"); // 拿到body下的子节点form下的字节点banlce的值
                    String subID = itemEle.elementTextTrim("subID");

                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
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