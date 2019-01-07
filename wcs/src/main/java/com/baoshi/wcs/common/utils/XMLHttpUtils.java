package com.baoshi.wcs.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baoshi.wcs.vo.GetOrderVO;
import com.baoshi.wcs.vo.SoapParams;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class XMLHttpUtils {
    private static final Logger log = LoggerFactory.getLogger(XMLHttpUtils.class);

    public static String sendSyncRequest(Map<String, Object> params) {
        HttpURLConnection httpConn;
        BufferedReader reader = null;

        try {
            httpConn = (HttpURLConnection) new URL("http://demo.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl").openConnection();
            httpConn.setRequestProperty("Content-Type","text/xml; charset=utf-8"); // 设置在header中
//            httpConn.setRequestProperty("username", params.getUsername());
//            httpConn.setRequestProperty("password", params.getPassword());

            for (Map.Entry<String, Object> entry: params.entrySet()){
                if("url".equalsIgnoreCase(entry.getKey())){
                    continue;
                }
                httpConn.setRequestProperty(entry.getKey(),entry.getValue().toString());
            }

            httpConn.setRequestMethod("GET");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.connect();

            // 获取服务器响应状态
            int code = httpConn.getResponseCode();
            String tempString = null;
            StringBuffer retMsg = new StringBuffer();
            if (code == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
                while ( (tempString = reader.readLine()) != null ) {
                    retMsg.append(tempString);
                }
                log.info("res "+retMsg.toString());
                return parseXML(retMsg.toString());
            } else {
                return "";
            }
        } catch ( Exception e ) {
            log.error("send soap error: ", e);
            return "";
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("close BufferedReader error: ", e);
                }
            }
        }
    }

    /**
     * 解析XML
     *
     * @param input
     * @return
     */
    private static String parseXML(String input) {
        Document doc;
        try {
            doc = DocumentHelper.parseText(input);
            // 获取根节点
            Element root = doc.getRootElement();
            return root.getText();
        } catch (Exception e) {
            log.error("parse xml error: ", e);
            return "";
        }
    }


    public static void main(String[] args) {
        GetOrderVO getOrderVO = new GetOrderVO();
        getOrderVO.setTid("20140318155513001");
        getOrderVO.setCid("35f26a33df794032b48b578e9352ff91");
        getOrderVO.setPwd("a56f3c9fce41441992b687d49290dd50");
        getOrderVO.setWarehouseid("653b9ffe991140a68be8579f55c80e05");
        getOrderVO.setTimetype("check");
        getOrderVO.setStartdate("2016-09-01 10:00:00");
        getOrderVO.setEnddate(">2016-09-13 10:00:00");
        getOrderVO.setPageno(1);
        getOrderVO.setPagesize(50);
        String vo = JSON.toJSONString(getOrderVO);
        JSONObject jsonObject = JSON.parseObject(vo);
        XMLHttpUtils.sendSyncRequest(jsonObject);
    }
}
