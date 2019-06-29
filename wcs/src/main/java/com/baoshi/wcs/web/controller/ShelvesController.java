package com.baoshi.wcs.web.controller;


import com.alibaba.fastjson.JSON;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.service.ColumnService;
import com.baoshi.wcs.service.LayerService;
import com.baoshi.wcs.service.ShelvesService;
import com.baoshi.wcs.vo.ShelvesVO.ShelvesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baoshi.wcs.web.basic.BaseController;

import java.util.List;

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

    @Autowired
    ShelvesService shelvesService;

    @Autowired
    ColumnService columnService;

    @Autowired
    LayerService layerService;

    @PostMapping("/add")
    @ResponseBody
    public Object add(@RequestBody ShelvesVO shelvesVO) throws Exception {

        logger.info(JSON.toJSONString(shelvesVO));
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        if(null == shelvesVO){
            res.failed("參數不能為空");
        }

        Shelves shelves = new Shelves();
        BeanUtils.copyProperties(shelvesVO,shelves);
        boolean save = shelvesService.saveShelvesAndColumnsAndLayers(shelvesVO);
        if(!save){
            res.failed("新增貨架失敗",logger);
        }
        res.success("0","设备创建成功");
        return res;

    }

}
