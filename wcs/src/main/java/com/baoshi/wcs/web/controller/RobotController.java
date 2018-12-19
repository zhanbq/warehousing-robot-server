package com.baoshi.wcs.web.controller;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.vo.GoodsWeightVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    private GoodsWeightService goodsWeightService;

    private static final String goodsWeightKey = "2C7FACD3AFC3FFE547FC54CDA076A25D";
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
        GoodsWeight goodsWeight = new GoodsWeight();
        goodsWeight.setBarCode(goodsWeightVO.getBarCode());
        goodsWeight.setWeight(goodsWeightVO.getWeight());
        boolean saveRes = goodsWeightService.save(goodsWeight);
        if(saveRes){
            apiResponse.success(saveRes,"保存成功");
        }
        return apiResponse;
    }

}
