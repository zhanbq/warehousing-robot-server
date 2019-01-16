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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${com.wcs.wms.url}")
    private String wmsServiceUrl;

    @Value("${com.wcs.wms.cid}")
    private String wmsServiceCid;

    @Value("${com.wcs.wms.pwd}")
    private String wmsServicePwd;

    @Value("${com.wcs.wms.warehouseid}")
    private String wmsServiceWarehouseId;

    @Value("${com.wcs.wms.unit}")
    private String wmsServiceUnit;

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
    public Object weigt(@RequestBody GoodsWeightVO goodsWeightVO) throws InterruptedException, ExecutionException, TimeoutException {
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

        Callable<String> goodsWeight2WMS = new Callable<String>() {
            @Override
            public String call() throws Exception {
                JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//                String wsUrl = "http://test3.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";
                String wsUrl = wmsServiceUrl;
                Client client = dcf.createClient(wsUrl);
                String method = "setOrderWeight";//webservice的方法名
                Object[] result = null;
                String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<setOrderWeight>\n" +
                        "<tid>20140318155513001</tid>\n" +
                        "<cid>" + wmsServiceCid + "</cid>\n" +
                        "<pwd>"+wmsServicePwd+"</pwd>\n" +
                        "<warehouseid>"+wmsServiceWarehouseId+"</warehouseid>\n" +
                        "<sendcode>"+barCode+"</sendcode>\n" +
                        "<weight>"+weight+"</weight>\n" +
                        "<unit>"+wmsServiceUnit+"</unit>\n" +
                        "</setOrderWeight>";
                Object[] res = null;
                try {
                    //调用webservice
                    res = client.invoke(method, reqXml);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("保存 wms 扫码称重 异常 :{}",e);
                }
                return res[0].toString();
            }

        };
        if(saveRes) {

            Future<String> future = executor.submit(goodsWeight2WMS);
            String res = future.get(5, TimeUnit.SECONDS);
            WMSServiceResponse resp = Xml2BeanUtil.getBaseWMSResp(res);
            if (resp != null) {
                String goodsweihgtRc = resp.getRc();
                if (!"0000".equals(goodsweihgtRc)) {
                    apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    apiResponse.setServerMsg("保存快递单号 重量失败  wms 状态码: " + rc + " msg: " + resp.getRm());
                    return apiResponse;
                }
            }
            apiResponse.success(saveRes,"barcode 验证成功,并保存成功, 快递单号和重量成功推送到WMS");
        }
        return apiResponse;
    }


    private WMSServiceResponse<List<Order>>  checkBarcode2Wms(String barcode){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        String wsUrl = "http://demo.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";

//        String wsUrl = "http://test3.kucangbao.com/kcb-1.0/cxf/warehouse?wsdl";
        String wsUrl = wmsServiceUrl;
        Client client = dcf.createClient(wsUrl);
        String method = "getOrders";//webservice的方法名
        Object[] result = null;
        String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<getOrders>\n" +
                "<tid>20140318155513001</tid>\n" +
                "<cid>"+wmsServiceCid+"</cid>\n" +
                "<pwd>"+wmsServicePwd+"</pwd>\n" +
                "<warehouseid>"+wmsServiceWarehouseId+"</warehouseid>\n" +
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
