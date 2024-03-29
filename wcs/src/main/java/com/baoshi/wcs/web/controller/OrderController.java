package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baoshi.wcs.common.response.NewWMSResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.entity.newwms.OrderVO4NewWms;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 订单改造
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {


    @Autowired
    GoodsWeightService goodsWeightService;

    @PostMapping("/express/info")
    @ResponseBody
    public Object receiveShipmentOrderInfo(@RequestBody JSONObject orderVO4NewWms){
        logger.info(JSON.toJSONString(orderVO4NewWms));
        JSONObject order = orderVO4NewWms.getJSONObject("request");

        GoodsWeight goodsWeight = new GoodsWeight();
        goodsWeight.setBarCode(order.getString("SOReference5"));//快递单号
        goodsWeight.setCarrierId(order.getString("CarrierID")); //承运商
        goodsWeight.setConsigneename(order.getString("Consigneename")); //收货人姓名
        goodsWeight.setCustomer(order.getString("Customer")); //货主
        goodsWeight.setPalletid(order.getString("palletid"));//发货单号
        goodsWeight.setTaskId(order.getString("TASKID"));//任务号
        goodsWeight.setOrderNo(order.getString("Orderno"));//WMS 订单号

        NewWMSResponse<Object> res = new NewWMSResponse<>();
        boolean save = goodsWeightService.save(goodsWeight);

        if(save){
            res.setFlag("Y");
            res.setCode("000");
            res.setMessage("正确接收");
        }else {
            res.setFlag("W");
            res.setCode("999");
            res.setMessage("接受失敗 內部錯誤");
        }

        return res;
    }
}
