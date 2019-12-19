package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baoshi.wcs.common.config.NewWMSHttpProp;
import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.common.response.NewWMSResponse;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.common.utils.ExpressMathUtil;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.entity.LastGoodsWeight;
import com.baoshi.wcs.entity.RobotInfo;
import com.baoshi.wcs.entity.ShipperCarton;
import com.baoshi.wcs.pojo.ShipperCartonPojo;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.service.LastGoodsWeightService;
import com.baoshi.wcs.service.RobotInfoService;
import com.baoshi.wcs.service.ShipperCartonService;
import com.baoshi.wcs.vo.GoodsWeightVO;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/robot")
public class RobotController extends BaseController {
    /**
     * 对于计算密集型的任务，一个有Ncpu个处理器的系统通常通过使用一个Ncpu + 1个线程的线程池来获得最优的利用率
     */
    private static final int N_CPUS = Runtime.getRuntime().availableProcessors();
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

    @Value("${wcs.shipper_carton.digital.error.value}")
    private String shpperCartonDigitalErrorValue;

    @Autowired
    NewWMSHttpProp newWMSHttpProp;

    @Autowired
    RobotInfoService robotInfoService;

    @Autowired
    LastGoodsWeightService lastGoodsWeightService;

    @Autowired
    ShipperCartonService shipperCartonService;

    private static final String goodsWeightKey = "2C7FACD3AFC3FFE547FC54CDA076A25D";

    private static ExecutorService THREAD_4_PUSH_GOODSWEIGHT_DATA = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    private static final Pattern p = Pattern.compile("\\s*|\t|\r|\n");
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
//        NewWMSResponse<Object> newWMSResponse = new NewWMSResponse<>();
        if(StringUtils.isEmpty(goodsWeightVO)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("数据不能为空 ");
            return apiResponse;
        }

        if(StringUtils.isEmpty(goodsWeightVO.getId())){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("robotId 数据不能为空");
            return apiResponse;
        }

        //验证机器id是否有效
        RobotInfo robotInfo = robotInfoService.getById(goodsWeightVO.getId());

        if(robotInfo == null){
            logger.error("机器id错误 : id {}",JSON.toJSONString(goodsWeightVO.getId()));
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("机器id错误");
            return apiResponse;
        }
        //**********************
        //验证单号是否有效
        String barCode = goodsWeightVO.getBarCode();
        if(StringUtils.isEmpty(barCode)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("barcode 不能为空");
            return apiResponse;
        }
        //快递单号不能为空
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
            logger.error("查询不到快递单号,barcode : {}",barCode);
            apiResponse.setCode(1003);
            apiResponse.setMsg("单号 : ["+ barCode +"], 此快递单号 wms并无推送至wcs, 无法保存称重!");
            return apiResponse;
        }

        //保存最新的快递单号 用于实时回显
        LastGoodsWeight lastGoodsWeight = new LastGoodsWeight();
        lastGoodsWeight.setLastBarCode(barCode);
        lastGoodsWeight.setRobotId(goodsWeightVO.getId());
        boolean lastGoodsWeightSave = lastGoodsWeightService.save(lastGoodsWeight);
        if(!lastGoodsWeightSave){
            apiResponse.failed("最新快递单号保存失败",logger);
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
        if(goodsWeightVO.getLength()!=null&&goodsWeightVO.getWidth()!=null&&goodsWeightVO.getHeight()!=null){
            //长宽高 都不为空时,排序 取体积
            ExpressMathUtil.sortAndGetLengthAndWidthAndHeight(goodsWeightVO,goodsWeightVO.getLength(),goodsWeightVO.getWidth(),goodsWeightVO.getHeight());
            goodsWeightParam.setLength(goodsWeightVO.getLength());
            goodsWeightParam.setWidth(goodsWeightVO.getWidth());
            goodsWeightParam.setHeight(goodsWeightVO.getHeight());
            //计算体积
            goodsWeightParam.setVolume(ExpressMathUtil.getVolume(goodsWeightVO.getLength(),goodsWeightVO.getWidth(),goodsWeightVO.getHeight()));//计算

            //计算 长宽高 体积 误差
            QueryWrapper<ShipperCarton> shipperCartonQueryWrapper = new QueryWrapper<>();
            //根据货主查询纸箱
            shipperCartonQueryWrapper.eq("shipper_name",goodsWeightResOne.getCustomer());
            if(!StringUtils.isEmpty(goodsWeightResOne.getCustomer())){
                List<ShipperCarton> shipperCartonlist = shipperCartonService.list(shipperCartonQueryWrapper);

                if(!CollectionUtils.isEmpty(shipperCartonlist)){
                    //查询到货主的所有纸箱 不能为空
                    List<ShipperCartonPojo> shipperCartonPojoList = ExpressMathUtil.sortAscShipperCartonList(shipperCartonlist
                            , goodsWeightVO.getLength()
                            , ExpressMathUtil.LENGTH);

                    if(!CollectionUtils.isEmpty(shipperCartonPojoList)){
                        ShipperCartonPojo mixShipperCarton = shipperCartonPojoList.get(0);

                        //保存纸箱名称,即纸箱编码
                        goodsWeightParam.setCartonName(mixShipperCarton.getCartonName());
                    }
                }
            }
        }

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
        jsonObject.put("TASKID",goodsWeightResOne.getTaskId());
        jsonObject.put("SOReference5",goodsWeightVO.getBarCode());
        jsonObject.put("Weigh",goodsWeightVO.getWeight().toString());
        jsonObject.put("Addwho","EDI");
        jsonObject.put("Package",goodsWeightParam.getCartonName());//纸箱名称 2019.12.17 添加纸箱计数
        jsonObject.put("Addtime",System.currentTimeMillis());
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("request",jsonObject);
//        map.add("request",jsonObject);

        ResponseEntity<String> gwPostRes = restTemplate.postForEntity(url, jsonObject1, String.class);
        if(null == gwPostRes){
            apiResponse.failed("快递单号推送失败",logger);
            return apiResponse;
        }
        String body1 = gwPostRes.getBody();
        JSONObject body = JSON.parseObject(body1);
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
    public Object getLastGoodsweight(String gwRobotId){
//        gwRobotId = "2C7FACD3AFC3FFE547FC54CDA076A25D";
        WCSApiResponse<Object> apiResponse = new WCSApiResponse<>();
        if(StringUtils.isEmpty(gwRobotId)){
            apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            apiResponse.setServerMsg("机器id 不能为空");
            return apiResponse;
        }

        QueryWrapper<LastGoodsWeight> lastGoodsWeightQueryWrapper = new QueryWrapper<>();
        lastGoodsWeightQueryWrapper.eq("robot_id",gwRobotId);
        lastGoodsWeightQueryWrapper.orderByDesc("id").last("limit 0,1");

        LastGoodsWeight lastGoodsWeight = lastGoodsWeightService.getOne(lastGoodsWeightQueryWrapper);

        if(lastGoodsWeight == null){
            apiResponse.failed("没有最新称重数据",logger);
            return apiResponse;
        }

        /**
         * 根据robotId查询 对应机器最新的一条数据
         */
        QueryWrapper<GoodsWeight> goodsWeightQueryWrapper = new QueryWrapper<>();
        goodsWeightQueryWrapper.eq("bar_code",lastGoodsWeight.getLastBarCode());
        GoodsWeight goodsWeight = goodsWeightService.getOne(goodsWeightQueryWrapper);

        if(goodsWeight == null){
            logger.error("查询不到快递单号,barcode : {}",lastGoodsWeight.getLastBarCode());
            apiResponse.setCode(1003);
            apiResponse.setMsg("单号 : ["+ lastGoodsWeight.getLastBarCode() +"], 此快递单号 wms并无推送至wcs, 无法保存称重, 无法查询[货主] [承运商]等信息!");
            return apiResponse;
        }
        Map<Object, Object> resMap = new HashMap<>();
        resMap.put("gw",goodsWeight);

        RobotInfo robotInfo = robotInfoService.getById(gwRobotId);
        resMap.put("robotInfo",robotInfo);
        apiResponse.success(resMap);
        return apiResponse;

    }

    /**
     *
     * 批量推送单号
     * @param begin
     * @param end
     * @return
     */

    @GetMapping("/goodsweight/batchpush")
    @ResponseBody
    public Object BatchPushGoodsweightData2Wms(String begin, String end){

        ApiResponse<Object> res = new ApiResponse<>();
        QueryWrapper<GoodsWeight> batchGWQuery = new QueryWrapper<>();
        QueryWrapper<GoodsWeight> goodsWeightQueryWrapper = batchGWQuery.between("create_time", begin, end);
        List<GoodsWeight> goodsWeightList = goodsWeightService.list(goodsWeightQueryWrapper);
        if(CollectionUtils.isEmpty(goodsWeightList)){
            res.failed("此时间段没有单子 , begin: " + begin + " || end: " + end);
            return res;
        }
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                RestTemplate restTemplate = new RestTemplate();
                String url = NewWMSHttpProp.orderUrl;
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                for(GoodsWeight gw : goodsWeightList){
                    //推送重量至wms

                    MultiValueMap<String, JSONObject> map= new LinkedMultiValueMap<>();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("TASKID",gw.getTaskId());
                    jsonObject.put("SOReference5",gw.getBarCode());
                    jsonObject.put("Weigh",gw.getWeight().toString());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("request",jsonObject);

                    ResponseEntity<String> gwPostRes = restTemplate.postForEntity(url, jsonObject1, String.class);
                    if(null == gwPostRes){
                        res.failed("快递单号推送失败",logger);
                        return res;
                    }
                    JSONObject body = JSON.parseObject(gwPostRes.getBody());
                    if(null == body){
                        res.failed("快递单号推送失败",logger);
                        return res;
                    }
                    JSONObject response = body.getJSONObject("response");
                    if(response == null){
                        res.failed("快递单号推送失败",logger);
                        return res;
                    }
                    String flag = response.getString("flag");
                    if(!"Y".equals(flag)){
                        res.failed("快递单号推送失败 : "+response.getString("message"),logger);
                        return res;
                    }
                }
                return null;
            }
        };
        THREAD_4_PUSH_GOODSWEIGHT_DATA.submit(callable);
        THREAD_4_PUSH_GOODSWEIGHT_DATA.shutdown();
        res.success("","批量推送成功.");
        return res;
    }
}
