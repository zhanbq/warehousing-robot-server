package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.service.GoodsWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping("/goodsweight")
    public String goodsweight(){
        return "goodsweight";
    }

    @Autowired
    GoodsWeightService goodsWeightService;

    /**
     * 查询最新的扫码重量数据
     * @return
     */
    @GetMapping("/last_goodsweight")
    @ResponseBody
    public Object getLastGoodsweight(String robotKey){
        robotKey = "2C7FACD3AFC3FFE547FC54CDA076A25D";
        WCSApiResponse<String> wcsApiResponse = new WCSApiResponse<>();
        if(StringUtils.isEmpty(robotKey)){
            wcsApiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            wcsApiResponse.setServerMsg("机器key 不能为空");
        }
        GoodsWeight goodsWeight = goodsWeightService.etLastGoodsweight(robotKey);
        wcsApiResponse.success(JSON.toJSONString(goodsWeight));
        return wcsApiResponse;

    }

}
