package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
            apiResponse.failed("查询不到快递单号",logger);
            return apiResponse;
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
            apiResponse.failed("快递单号更新失败",logger);
            return apiResponse;
        }
        goodsWeightResOne.setWeight(goodsWeightVO.getWeight());

        //推送重量至wms
        RestTemplate restTemplate = new RestTemplate();
        String url = NewWMSHttpProp.orderUrl;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        MultiValueMap<String, JSONObject> map= new LinkedMultiValueMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TASKID", goodsWeightResOne.getTaskId());
        jsonObject.put("SOReference5",goodsWeightVO.getBarCode());
        jsonObject.put("Weigh",goodsWeightVO.getWeight().toString());
        map.add("request",jsonObject);
        HttpEntity<MultiValueMap<String, JSONObject>> request = new HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> gwPostRes = restTemplate.postForEntity(url, request, JSONObject.class);
        if(null == gwPostRes){
            apiResponse.failed("快递单号推送失败",logger);
            return apiResponse;
        }
        JSONObject body = gwPostRes.getBody();
        if(null == body){
            apiResponse.failed("快递单号推送失败",logger);
            return apiResponse;
        }
        JSONObject response = body.getJSONObject("response");
        if(response == null){
            apiResponse.failed("快递单号推送失败",logger);
            return apiResponse;
        }
        String flag = response.getString("flag");
        if(!"Y".equals(flag)){
            apiResponse.failed("快递单号推送失败 : "+response.getString("message"),logger);
            return apiResponse;
        }
        apiResponse.success(update,"barcode 验证成功,并保存成功, 快递单号和重量成功推送到WMS");
        return apiResponse;
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
        Map<Object, Object> resMap = new HashMap<>();
        resMap.put("gw",goodsWeight);

        RobotInfo robotInfo = robotInfoService.getById(gwRobotId);
        resMap.put("robotInfo",robotInfo);
        apiResponse.success(resMap);
        return apiResponse;

    }
}
