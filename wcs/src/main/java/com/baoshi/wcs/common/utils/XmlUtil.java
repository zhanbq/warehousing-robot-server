package com.baoshi.wcs.common.utils;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.wms.Order;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 利用反射和JDOM解析xml成bean/List
 * @Date: 2018/1/22 16:06
 */
public class XmlUtil {
    /**
     * xml转换成bean
     *
     * @param xml
     * @param obj
     * @return
     */
    public static Object xmlToBean(String xml, Class obj) {
        SAXBuilder builder = new SAXBuilder();
        Field[] fields = obj.getDeclaredFields();
        String beanName = obj.getSimpleName();
        try {
            Object object = Class.forName(obj.getName()).newInstance();
            Document doc = builder.build(new StringReader(xml));
            Element books = doc.getRootElement();
            List booklist = books.getChildren(beanName);
            for (Iterator iter = booklist.iterator(); iter.hasNext(); ) {
                Element book = (Element) iter.next();
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    if (!fields[j].toString().contains("final")) {
                        fields[j].set(object, book.getChildTextTrim(fields[j].getName()) == null ? "" : book.getChildTextTrim(fields[j].getName()));
                    }
                }
            }
            System.out.println(fields.toString());
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * xml转换成list
     *
     * @param xml
     * @param obj
     * @return
     */
    public static List<?> xmlToList(String xml, Class obj) {
        SAXBuilder builder = new SAXBuilder();
        Field[] fields = obj.getDeclaredFields();
        String beanName = obj.getSimpleName();
        try {
            Object object = Class.forName(obj.getName()).newInstance();
            List<Object> list = new ArrayList<Object>();
            Document doc = builder.build(new StringReader(xml));
            Element books = doc.getRootElement();
            List booklist = books.getChildren(beanName);
            for (Iterator iter = booklist.iterator(); iter.hasNext(); ) {
                Element book = (Element) iter.next();
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    if (!fields[j].toString().contains("final")) {
                        fields[j].set(object, book.getChildTextTrim(fields[j].getName()) == null ? "" : book.getChildTextTrim(fields[j].getName()));
                    }
                }
                list.add(object);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bean转换成xml
     */
    public static String beanToXmls(Object obj) {
        Class t = (Class) obj.getClass();
        Field[] fields = t.getDeclaredFields();
        StringBuffer buffer = new StringBuffer();
        buffer.append("<Data><Vehicle>");
        try {
            Object object = Class.forName(obj.getClass().getName()).newInstance();
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                String val = fields[j].get(obj) == null ? "" : fields[j].get(obj) + "";
                buffer.append("<" + fields[j].getName() + ">" + val + "</" + fields[j].getName() + ">\n");
            }
            buffer.append("</Data></Vehicle>");
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Order> o = (List<Order>) xmlToList("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<rt><tid>20140318155513001</tid>\n" +
                "<rc>0000</rc><rm>成功</rm>\n" +
                "<orders><order><id>d72a9e9129194d4aa8bc1af882758bcd</id><cname>武迪实业</cname><ordercode>20190104185721368</ordercode><expresscode>STO</expresscode><sendcode>null</sendcode><prov>浙江省</prov><city>杭州市</city><district>滨江区</district><address>testpacth20190104185721368</address><tousername>王收件人</tousername><tophone>13858182349</tophone><wavecode>201901080064657</wavecode><flowstepname>已运行</flowstepname><skus><sku><ganme>null</ganme><outerid>csmrgbm7711</outerid><barcode>null</barcode><amount>2</amount><unit>J</unit></sku></skus></order></orders></rt>", Order.class);
        System.out.println(JSON.toJSONString(o));
    }
}