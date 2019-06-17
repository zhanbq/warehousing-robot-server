package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baoshi.wcs.common.config.NewWMSHttpProp;
import com.baoshi.wcs.common.config.WMSWebserviceProperties;
import com.baoshi.wcs.common.response.NewWMSResponse;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.common.utils.Xml2BeanUtil;
import com.baoshi.wcs.common.wms.Order;
import com.baoshi.wcs.common.wms.WMSServiceResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.entity.RobotInfo;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.service.RobotInfoService;
import com.baoshi.wcs.vo.GoodsWeightVO;
import com.baoshi.wcs.web.basic.BaseController;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    NewWMSHttpProp newWMSHttpProp;

    @Autowired
    RobotInfoService robotInfoService;

    private static final String goodsWeightKey = "2C7FACD3AFC3FFE547FC54CDA076A25D";

    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    private static Client client = null;
    static {
        client = initClient();
    }
    private static Client initClient(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WMSWebserviceProperties.getUrl());
        return client;
    }
    /**
     *
     * @param goodsWeightVO
     * @return
     */
    @RequestMapping(value = "/goods/weight", method = RequestMethod.POST)
    @ResponseBody
    public Object weigt(@RequestBody GoodsWeightVO goodsWeightVO) throws InterruptedException, ExecutionException, TimeoutException {
        logger.info("扫码称重入参, goodsWeightVO: {}",JSON.toJSONString(goodsWeightVO));
        WCSApiResponse<Boolean> apiResponse = new WCSApiResponse<>();
        NewWMSResponse<Object> newWMSResponse = new NewWMSResponse<>();
        if(StringUtils.isEmpty(goodsWeightVO)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("数据不能为空");
            return apiResponse;
        }

        if(StringUtils.isEmpty(goodsWeightVO.getId())){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("robotId 数据不能为空");
            return apiResponse;
        }

        RobotInfo robotInfo = robotInfoService.getById(goodsWeightVO.getId());

        if(robotInfo == null){
            logger.error("机器id错误 : id {}",JSON.toJSONString(goodsWeightVO.getId()));
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("机器id错误");
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

        //根据单号查询信息
        QueryWrapper<GoodsWeight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bar_code",goodsWeightVO.getBarCode());
        GoodsWeight goodsWeightResOne = goodsWeightService.getOne(queryWrapper);
        //验证
        if(null == goodsWeightResOne){
            newWMSResponse.setCode("555");
            newWMSResponse.setMessage("查询不到快递单号");
            newWMSResponse.setFlag("W");
            return newWMSResponse;
        }

        //更新
        UpdateWrapper<GoodsWeight> goodsWeightUpdateWrapper = new UpdateWrapper<>();
        goodsWeightUpdateWrapper.eq("version",goodsWeightResOne.getVersion());
        goodsWeightUpdateWrapper.eq("bar_code",goodsWeightVO.getBarCode());
        GoodsWeight goodsWeightParam = new GoodsWeight();
        goodsWeightParam.setBarCode(barCode);
        goodsWeightParam.setWeight(goodsWeightVO.getWeight());
        goodsWeightParam.setGwRobotId(goodsWeightVO.getId());
        goodsWeightParam.setVersion(goodsWeightResOne.getVersion()+1);
        boolean update = goodsWeightService.update(goodsWeightParam, goodsWeightUpdateWrapper);

        if(!update){
            newWMSResponse.setCode("555");
            newWMSResponse.setMessage("快递单号更新失败");
            newWMSResponse.setFlag("W");
            return newWMSResponse;
        }
        goodsWeightResOne.setWeight(goodsWeightVO.getWeight());

        //推送重量至wms
        RestTemplate restTemplate = new RestTemplate();
        String url = NewWMSHttpProp.orderUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("TASKID", goodsWeightResOne.getTaskId());
        map.add("SOReference5",goodsWeightVO.getBarCode());
        map.add("Weigh",goodsWeightVO.getWeight().toString());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        logger.info("称重回传wms res : {}",JSON.toJSONString(response.getBody()));
        return goodsWeightResOne;
//        WMSServiceResponse<List<Order>> wmsServiceResponse = checkBarcode2Wms(barCode);
//        logger.info("wms 快递单 验证 结果: {}",JSON.toJSONString(wmsServiceResponse));
//        String rc = wmsServiceResponse.getRc();
//        if(!"0000".equals(rc)){
//            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            apiResponse.setServerMsg("验证barcode失败 wms 状态码: "+rc+" msg: "+ wmsServiceResponse.getRm() );
//            logger.error("快递单号验证失败:{}",JSON.toJSONString(wmsServiceResponse));
//            return apiResponse;
//        }
//
//        GoodsWeight goodsWeight = new GoodsWeight();
//        goodsWeight.setBarCode(barCode);
//        goodsWeight.setWeight(goodsWeightVO.getWeight());
//        goodsWeight.setGwRobotId(goodsWeightVO.getId());
//        boolean saveRes = goodsWeightService.save(goodsWeight);
//
//        Callable<String> goodsWeight2WMS = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                String method = "setOrderWeight";//webservice的方法名
//                Object[] result = null;
//                String reqXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
//                        "<setOrderWeight>\n" +
//                        "<tid>20140318155513001</tid>\n" +
//                        "<cid>" + wmsServiceCid + "</cid>\n" +
//                        "<pwd>"+wmsServicePwd+"</pwd>\n" +
//                        "<warehouseid>"+wmsServiceWarehouseId+"</warehouseid>\n" +
//                        "<sendcode>"+barCode+"</sendcode>\n" +
//                        "<weight>"+weight+"</weight>\n" +
//                        "<unit>"+wmsServiceUnit+"</unit>\n" +
//                        "</setOrderWeight>";
//                logger.debug("推送重量 报文 ,{}",reqXml);
//                Object[] res = null;
//                try {
//                    //调用webservice
//                    res = client.invoke(method, reqXml);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    logger.error("保存 wms 扫码称重 异常 :{}",e);
//                }
//
//                return res[0].toString();
//            }
//
//        };
//        if(saveRes) {
//
//            Future<String> future = executor.submit(goodsWeight2WMS);
//            String res = future.get(5, TimeUnit.SECONDS);
//            WMSServiceResponse resp = Xml2BeanUtil.getBaseWMSResp(res);
//            logger.info("推送快递单号重量返回：{}",JSON.toJSONString(resp));
//            if (resp != null) {
//                String goodsweihgtRc = resp.getRc();
//                if (!"0000".equals(goodsweihgtRc)) {
//                    apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//                    apiResponse.setServerMsg("保存快递单号 重量失败  wms 状态码: " + rc + " msg: " + resp.getRm());
//                    logger.error("快单号-重量推送失败:{}",JSON.toJSONString(resp));
//                    return apiResponse;
//                }
//            }
//            apiResponse.success(saveRes,"barcode 验证成功,并保存成功, 快递单号和重量成功推送到WMS");
//        }
//        return apiResponse;
    }


    private WMSServiceResponse<List<Order>>  checkBarcode2Wms(String barcode){
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
        logger.debug("根据快递单号barcode 查询 快递详情 请求报文,{}",reqXml);
        WMSServiceResponse<List<Order>> orderDetails = null;
        try {
            result = client.invoke(method, reqXml);//调用webservice

            orderDetails = Xml2BeanUtil.getOrderDetails(result[0].toString());
            return orderDetails;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("校验快递单号失败");
        }
        return orderDetails;
    }


    /**
     * 查询最新的扫码重量数据,用于扫码量房界面 实时回显数据
     * @return
     */
    @GetMapping("/last_goodsweight")
    @ResponseBody
    public Object getLastGoodsweight(String gwRobotId,String gwId){
//        gwRobotId = "2C7FACD3AFC3FFE547FC54CDA076A25D";
        WCSApiResponse<Object> apiResponse = new WCSApiResponse<>();
        if(StringUtils.isEmpty(gwRobotId)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("机器id 不能为空");
            return apiResponse;
        }

        /**
         * 根据robotId查询 对应机器最新的一条数据
         */
        GoodsWeight goodsWeight = goodsWeightService.getLastGoodsweight(gwRobotId);

        if(goodsWeight == null){
            apiResponse.setCode(200);
            apiResponse.setServerMsg("数据为空");
            apiResponse.setData(null);
            return apiResponse;
        }

        WMSServiceResponse<List<Order>> wmsServiceResponse = checkBarcode2Wms(goodsWeight.getBarCode());
        logger.info("实时回显 快递单 验证 结果: {}",JSON.toJSONString(wmsServiceResponse));
        String rc = wmsServiceResponse.getRc();
        if(!"0000".equals(rc)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("实时回显 验证barcode失败 wms 状态码: "+rc+" msg: "+ wmsServiceResponse.getRm() );
            logger.error("实时回显 快递单号验证失败:{}",JSON.toJSONString(wmsServiceResponse));
            return apiResponse;
        }
        List<Order> res = wmsServiceResponse.getData();
        if(CollectionUtils.isEmpty(res)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("实时回显 查询快递详情错误: "+rc+" msg: "+ wmsServiceResponse.getRm() );
            logger.error("实时回显 查询快递详情错误:{}",JSON.toJSONString(wmsServiceResponse));
            return apiResponse;
        }
        Order order = res.get(0);
        Map<Object, Object> resMap = new HashMap<>();
        resMap.put("order",order);
        resMap.put("gw",goodsWeight);

        RobotInfo robotInfo = robotInfoService.getById(gwRobotId);
        resMap.put("robotInfo",robotInfo);
        apiResponse.success(resMap);
        return apiResponse;

    }
}
