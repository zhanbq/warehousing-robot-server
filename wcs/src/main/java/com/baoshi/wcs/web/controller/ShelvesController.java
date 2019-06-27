package com.baoshi.wcs.web.controller;


import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.vo.ShelvesVO.ShelvesVO;
import org.springframework.web.bind.annotation.*;

import com.baoshi.wcs.web.basic.BaseController;

/**
 * <p>
 * 货架信息管理 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/shelves")
public class ShelvesController extends BaseController {

    @PostMapping("/add")
    @ResponseBody
    public Object add(@RequestBody ShelvesVO shelvesVO){

        logger.info(JSON.toJSONString(shelvesVO));

        return shelvesVO;

    }

}
