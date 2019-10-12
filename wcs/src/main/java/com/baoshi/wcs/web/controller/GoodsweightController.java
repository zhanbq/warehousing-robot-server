package com.baoshi.wcs.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.service.GoodsWeightService;
import com.baoshi.wcs.vo.GoodsWeightSearchVO;
import com.baoshi.wcs.vo.GoodsWeightVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goodsweight")
public class GoodsweightController {

    @Autowired
    GoodsWeightService goodsWeightService;

    @GetMapping("/list")
    @ResponseBody
    public Object all(Page<GoodsWeightSearchVO> page, GoodsWeightVO goodsWeightVO){

        WCSApiResponse<Object> res = new WCSApiResponse<>();
        Page<GoodsWeight> gwPage = new Page<>();
        BeanUtils.copyProperties(page,gwPage);
        QueryWrapper<GoodsWeight> gwQuerryWrapper = new QueryWrapper<>();
        if(goodsWeightVO!=null){
            if(!StringUtils.isEmpty(goodsWeightVO.getBarCode())){
                //查询快递单号
                gwQuerryWrapper.eq("bar_code",goodsWeightVO.getBarCode());
            }
            if(!StringUtils.isEmpty(goodsWeightVO.getId())){
                //根据机器id查询
                gwQuerryWrapper.eq("gw_robot_id",goodsWeightVO.getId());
            }
        }

        IPage<GoodsWeight> resPage = goodsWeightService.page(gwPage, gwQuerryWrapper);
        res.success(resPage);
        return res;
    }
}
