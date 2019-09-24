package com.baoshi.wcs.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.vo.GoodsWeightSearchVO;
import com.baoshi.wcs.vo.GoodsWeightVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goodsweight")
public class GoodsweightController {

    @GetMapping("/all")
    public Object all(Page<GoodsWeightSearchVO> goodsWeightSearchVO){

        return null;
    }
}
