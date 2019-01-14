package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.common.utils.Xml2BeanUtil;
import com.baoshi.wcs.common.wms.Order;
import com.baoshi.wcs.common.wms.WMSServiceResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.vo.GoodsWeightVO;

import com.baoshi.wcs.web.basic.BaseController;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.*;

@Controller
@RequestMapping("/robot")
public class RobotController extends BaseController {

    @Autowired
    private GoodsWeightService goodsWeightService;

    private static final String goodsWeightKey = "2C7FACD3AFC3FFE547FC54CDA076A25D";

    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    /**
     *
     * @param goodsWeightVO
     * @return
     */
    @RequestMapping(value = "/goods/weight", method = RequestMethod.POST)
    @ResponseBody
    public Object weigt(@RequestBody GoodsWeightVO goodsWeightVO){
        WCSApiResponse<Boolean> apiResponse = new WCSApiResponse<>();
        if(StringUtils.isEmpty(goodsWeightVO)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("数据不能为空");
            return apiResponse;
        }
        if(StringUtils.isEmpty(goodsWeightVO.getKey())){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("key不能为空");
            return apiResponse;
        }
        if(! goodsWeightKey.equals(goodsWeightVO.getKey())){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("错误的key值");
            return apiResponse;
        }
        String barCode = goodsWeightVO.getBarCode();
        if(StringUtils.isEmpty(barCode)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("barcode 不能为空");
            return apiResponse;
        }
        Double weight = goodsWeightVO.getWeight();
        if(null == weight){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("weight 不能为空");
            return apiResponse;
        }

        WMSServiceResponse<List<Order>> wmsServiceResponse = checkBarcode2Wms(barCode);
        logger.info("wms 快递单 验证 结果: {}",JSON.toJSONString(wmsServiceResponse));
        String rc = wmsServiceResponse.getRc();
        if(!"0000".equals(rc)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("验证barcode失败 wms 状态码: "+rc+" msg: "+ wmsServiceResponse.getRm() );
            return apiResponse;
        }

        GoodsWeight goodsWeight = new GoodsWeight();
        goodsWeight.setBarCode(barCode);
        goodsWeight.setWeight(goodsWeightVO.getWeight());
        boolean saveRes = goodsWeightService.save(goodsWeight);
        if(saveRes){
            apiResponse.success(saveRes,"barcode 验证成功,并保存成功,已发送包裹重量至WMS");
        }

        Runnable goodsWeight2WMS = new Runnable() {
            @Override
            public void run() {
                JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
                String wsUrl = "http://test3.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";
                Client client = dcf.createClient(wsUrl);
                String method = "getOrders";//webservice的方法名
                Object[] result = null;
                String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<setOrderWeight>\n" +
                        "<tid>20140318155513001</tid>\n" +
                        "<cid>9a49554856d04de384c645019634c1d8</cid>\n" +
                        "<pwd>c18c9035e3d746faaf6661a0bda6c6b1</pwd>\n" +
                        "<warehouseid>cf3c23f41a6142fa9e4d011b71ed8018</warehouseid>\n" +
                        "<expresscode>"+barCode+"</expresscode>\n" +
                        "<weight>"+weight+"</weight>\n" +
                        "<unit>g</unit>\n" +
                        "</setOrderWeight>";
                try {
                    client.invoke(method, reqXml);//调用webservice
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("保存 wms 扫码称重 异常 :{}",e);
                }
            }
        };
        executor.submit(goodsWeight2WMS);
        return apiResponse;
    }


    private WMSServiceResponse<List<Order>>  checkBarcode2Wms(String barcode){
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
                "<sendcode>"+barcode+"</sendcode>\n" +
                "</getOrders>";
        WMSServiceResponse<List<Order>> orderDetails = null;
        try {
            result = client.invoke(method, reqXml);//调用webservice

            orderDetails = Xml2BeanUtil.getOrderDetails(result[0].toString());
            return orderDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
}
