package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.Express;
import com.baoshi.wcs.service.ExpressService;
import com.baoshi.wcs.vo.OrderVO;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    ExpressService expressService;

    @RequestMapping("/express/info")
    @ResponseBody
    public Object receiveShipmentOrderInfo(@RequestBody OrderVO orderVO){
        System.out.println(JSON.toJSONString(orderVO));

        boolean res = expressService.saveBatchExpressList(orderVO);
        return res;
    }
}
