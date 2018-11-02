package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.common.ApiResponse;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.vo.GoodsWeightVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private GoodsWeightService goodsWeightService;

    /**
     *
     * @param goodsWeightVO
     * @return
     */
    @RequestMapping(value = "/goods/weight", method = RequestMethod.POST)
    @ResponseBody
    public String weigt(@RequestBody GoodsWeightVO goodsWeightVO){

        GoodsWeight goodsWeight = new GoodsWeight();
        goodsWeight.setBarCode(goodsWeightVO.getBarCode());
        goodsWeight.setWeight(goodsWeightVO.getWeight());
        Boolean isSuccess = goodsWeightService.saveOne(goodsWeight);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        return JSON.toJSONString(apiResponse);
    }

}
