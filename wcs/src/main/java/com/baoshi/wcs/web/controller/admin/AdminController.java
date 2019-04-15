package com.baoshi.wcs.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.service.GoodsWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台界面页面跳转
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping("/goodsweight")
    public String goodsweight(){
        return "goodsweight";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/vue/{name}")
    public String vue(@PathVariable("name")String name){
        return "vue/" + name;
    }
}
