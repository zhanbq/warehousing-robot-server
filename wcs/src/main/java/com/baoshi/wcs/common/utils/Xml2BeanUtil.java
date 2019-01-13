package com.baoshi.wcs.common.utils;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.wms.Order;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import org.dom4j.Element;
import org.jdom.input.SAXBuilder;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description: 利用反射和JDOM解析xml成bean/List
 * @Date: 2018/1/22 16:06
 */
public class Xml2BeanUtil {
    public void readStringXml(String xml) {
        Document doc = null;
        try {

            // 读取并解析XML文档
            // SAXReader就是一个管道，用一个流的方式，把xml文件读出来
            //
            // SAXReader reader = new SAXReader(); //User.hbm.xml表示你要解析的xml文档
            // Document document = reader.read(new File("User.hbm.xml"));
            // 下面的是通过解析xml字符串的
            doc = DocumentHelper.parseText(xml);// 将字符串转为XML

            Element rootElt = doc.getRootElement();// 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称

            Iterator iter = rootElt.elementIterator("order"); // 获取根节点下的子节点head

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
     * @description 将xml字符串转换成map
     * @param xml
     * @return Map
     */
    public static Map readStringXmlOut(String xml) {
        Map map = new HashMap();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 拿到根节点的名称
            System.out.println("根节点：" + rootElt.getName());

            // 获取根节点下的子节点head
            Iterator iter = rootElt.elementIterator("orders");
            // 遍历head节点
            while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                // 拿到head节点下的子节点title值
                String title = recordEle.elementTextTrim("title");
                System.out.println("title:" + title);
                map.put("title", title);
                // 获取子节点head下的子节点script
                Iterator iters = recordEle.elementIterator("script");
                // 遍历Header节点下的Response节点
                while (iters.hasNext()) {
                    Element itemEle = (Element) iters.next();
                    // 拿到head下的子节点script下的字节点username的值
                    String username = itemEle.elementTextTrim("username");
                    String password = itemEle.elementTextTrim("password");

                    System.out.println("username:" + username);
                    System.out.println("password:" + password);
                    map.put("username", username);
                    map.put("password", password);
                }
            }

            //获取根节点下的子节点body
            Iterator iterss = rootElt.elementIterator("body");
            // 遍历body节点
            while (iterss.hasNext()) {
                Element recordEless = (Element) iterss.next();
                // 拿到body节点下的子节点result值
                String result = recordEless.elementTextTrim("result");
                System.out.println("result:" + result);
                // 获取子节点body下的子节点form
                Iterator itersElIterator = recordEless.elementIterator("form");
                // 遍历Header节点下的Response节点
                while (itersElIterator.hasNext()) {
                    Element itemEle = (Element) itersElIterator.next();
                    // 拿到body下的子节点form下的字节点banlce的值
                    String banlce = itemEle.elementTextTrim("banlce");
                    String subID = itemEle.elementTextTrim("subID");

                    System.out.println("banlce:" + banlce);
                    System.out.println("subID:" + subID);
                    map.put("result", result);
                    map.put("banlce", banlce);
                    map.put("subID", subID);
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {

        // 下面是需要解析的xml字符串例子
//        String xmlString = "<html>" + "<head>" + "<title>dom4j解析一个例子</title>"
//                + "<script>" + "<username>yangrong</username>"
//                + "<password>123456</password>" + "</script>" + "</head>"
//                + "<body>" + "<result>0</result>" + "<form>"
//                + "<banlce>1000</banlce>" + "<subID>36242519880716</subID>"
//                + "</form>" + "</body>" + "</html>";

        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<rt><tid>20140318155513001</tid>\n" +
                "<rc>0000</rc><rm>成功</rm>\n" +
                "<orders><order><id>d72a9e9129194d4aa8bc1af882758bcd</id><cname>武迪实业</cname><ordercode>20190104185721368</ordercode><expresscode>STO</expresscode><sendcode>null</sendcode><prov>浙江省</prov><city>杭州市</city><district>滨江区</district><address>testpacth20190104185721368</address><tousername>王收件人</tousername><tophone>13858182349</tophone><wavecode>201901080064657</wavecode><flowstepname>已运行</flowstepname><skus><sku><ganme>null</ganme><outerid>csmrgbm7711</outerid><barcode>null</barcode><amount>2</amount><unit>J</unit></sku></skus></order></orders></rt>";

        /*
         * Test2 test = new Test2(); test.readStringXml(xmlString);
         */
        Map map = readStringXmlOut(xmlString);
        Iterator iters = map.keySet().iterator();
        while (iters.hasNext()) {
            String key = iters.next().toString(); // 拿到键
            String val = map.get(key).toString(); // 拿到值
            System.out.println(key + "=" + val);
        }
    }
}