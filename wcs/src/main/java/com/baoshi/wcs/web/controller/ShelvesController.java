package com.baoshi.wcs.web.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.entity.Layer;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.service.ColumnService;
import com.baoshi.wcs.service.LayerService;
import com.baoshi.wcs.service.ShelvesService;
import com.baoshi.wcs.vo.ShelvesVO.ShelvesVO;
import jdk.nashorn.internal.codegen.ObjectCreator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.baoshi.wcs.web.basic.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 分页
     * @param page
     * @return
     */
    @GetMapping("/all")
    public Object findAll(Page<Shelves> page){
        IPage<Shelves> shelves = shelvesService.page(page, new QueryWrapper<Shelves>());
        logger.info(JSON.toJSONString(shelves));
        if(shelves == null){
            return null;
        }

        if(CollectionUtils.isEmpty(shelves.getRecords())){
            return null;
        }
        List<ShelvesVO> resShelvesList = new ArrayList<>();
        for(Shelves shelve: shelves.getRecords()){
            Integer shelveId = shelve.getId();
            ShelvesVO shelvesVO = new ShelvesVO();
            BeanUtils.copyProperties(shelve,shelvesVO);
            QueryWrapper<Layer> layerQueryWrapper = new QueryWrapper<>();
            layerQueryWrapper.eq("shelves_id",shelveId);
            List<Layer> layers = layerService.list(layerQueryWrapper);
            if(CollectionUtils.isEmpty(layers)){
                continue;
            }
            shelvesVO.setLayers(layers);

            QueryWrapper<Layer> columnsQueryWrapper = new QueryWrapper<>();
            QueryWrapper<Column> columnQueryWrapper = new QueryWrapper<>();
            columnQueryWrapper.eq("shelves_id",shelveId);
            List<Column> columns = columnService.list(columnQueryWrapper);
            if(CollectionUtils.isEmpty(columns)){
                continue;
            }
            shelvesVO.setColumns(columns);

            resShelvesList.add(shelvesVO);
        }
        IPage<ShelvesVO> shelvesPageList = new Page<ShelvesVO>();
        BeanUtils.copyProperties(shelves,shelvesPageList,"records");
        shelvesPageList.setRecords(resShelvesList);
        return shelvesPageList;
    }



}
