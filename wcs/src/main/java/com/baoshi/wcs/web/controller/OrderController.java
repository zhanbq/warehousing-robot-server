package com.baoshi.wcs.web.controller;

import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    @RequestMapping()
    @ResponseBody
    public WCSApiResponse<Object> receiveShipmentOrderInfo(@RequestBody Object orderInfo){

        return null;
    }
}
