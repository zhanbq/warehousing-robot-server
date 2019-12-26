package com.baoshi.wcs.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.common.enumeration.YesOrNoEnum;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.common.validator.ShipperValidationGroup;
import com.baoshi.wcs.entity.Shipper;
import com.baoshi.wcs.entity.ShipperCarton;
import com.baoshi.wcs.service.ShipperCartonService;
import com.baoshi.wcs.service.ShipperService;
import com.baoshi.wcs.vo.ShipperVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.baoshi.wcs.web.basic.BaseController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhanbq
 * @since 2019-11-01
 */
@RestController
@RequestMapping("/shipper")
public class ShipperController extends BaseController {

    @Autowired
    ShipperService shipperService;

    @Autowired
    ShipperCartonService shipperCartonService;

    /**
     * 新建货主信息
     * @param shipperVO
     * @return
     */
    @RequestMapping("/add")
    public Object add(@Validated({ShipperValidationGroup.class}) ShipperVO shipperVO){

        WCSApiResponse<Object> res = new WCSApiResponse<>();
        QueryWrapper<Shipper> shipperQW = new QueryWrapper<>();
        shipperQW.eq("shipper_name",shipperVO.getShipperName());
        Shipper shipperRes = shipperService.getOne(shipperQW);
        if(null == shipperRes){
            //校验 shipper是否存在 如果存在直接返回
            res.success(shipperRes,"货主已经存在,无需重复新建.");
            return res;
        }

        Shipper shipperParam = new Shipper();
        BeanUtils.copyProperties(shipperVO,shipperParam);
        boolean save = shipperService.save(shipperParam);
        if(!save){
            res.failed("新建货主信息失败.",logger);
        }
        res.success("创建货主信息成功");
        return res;
    }

    /**
     * 根据 货主名查询货主信息
     * @param shipperName
     * @return
     */
    @GetMapping("/shipper_name/{shipper_name}")
    public Object findShipper(@PathVariable("shipper_name")String shipperName){
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        QueryWrapper<Shipper> shipperQW = new QueryWrapper<>();
        Shipper shipperRes = null;
        if(!StringUtils.isEmpty(shipperName)){
            shipperQW.eq("shipper_name",shipperName);
            shipperRes = shipperService.getOne(shipperQW);

        }
        if(null == shipperRes){
            res.success("货主信息不存在");
            return res;
        }

        QueryWrapper<ShipperCarton> shipperCartonQW = new QueryWrapper<>();
        shipperCartonQW.eq("shipper_name",shipperName);
        List<ShipperCarton> shipperCartonResList = shipperCartonService.list(shipperCartonQW);

        ShipperVO shipperVORes = new ShipperVO();
        BeanUtils.copyProperties(shipperRes,shipperVORes);
        shipperVORes.setShipperCartonList(shipperCartonResList);
        res.success(shipperVORes,"货主信息查询成功");
        return res;
    }

    /**
     * 设置货主是否参与纸箱计数
     * @param shipperVO
     * @return
     */
    @PostMapping("/shipperCartonCount")
    @ResponseBody
    public Object shipperCartonCount(@RequestBody ShipperVO shipperVO){
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        if(null == shipperVO){
            logger.info("入参为空");
            res.failed("入参为空");
            return res;
        }
        if(StringUtils.isEmpty(shipperVO.getShipperName())){
            logger.info("货主名为空,请填写货主名");
            res.failed("货主名为空,请填写货主名");
            return res;
        }

        if(StringUtils.isEmpty(shipperVO.getWhetherTheCount())){
            logger.info("是否计数名为空,请选择是否计数");
            res.failed("是否计数名为空,请选择是否计数");
            return res;
        }
        QueryWrapper<Shipper> shipperQuery = new QueryWrapper<>();
        shipperQuery.eq("shipper_name",shipperVO.getShipperName());
        Shipper shipperRes = shipperService.getOne(shipperQuery);
        if(null == shipperRes){
            logger.info("货主不存在,请填写正确的货主名/货主编号");
            res.failed("货主不存在,请填写正确的货主名/货主编号");
            return res;
        }
//        Shipper shipperParam = new Shipper();
//        shipperRes.setShipperName(shipperVO.getShipperName());
        shipperRes.setWhetherTheCount(shipperVO.getWhetherTheCount());
        boolean save = shipperService.updateById(shipperRes);
        if(!save){
            logger.info("保存失败,请重试或联系技术.");
            res.failed("保存失败,请重试或联系技术.");
            return res;
        }
        res.success("保存成功.");
        return res;
    }


    /**
     * 查询所有
     * @return
     */
    @GetMapping("/list")
    public Object list(){
        WCSApiResponse<Object> res = new WCSApiResponse<>();

        List<Shipper> shipperList = shipperService.list(new QueryWrapper<Shipper>());

        List<JSONObject> list = new ArrayList<>();
        shipperList.forEach(shipper -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("value",shipper.getShipperName()); //组装成前端需要的json 结构
            list.add(jsonObject);
        });
        res.setData(list);
        return res;
    }

    /**
     * 分页查询货主数据
     * @param page
     * @return
     */
    @GetMapping("/pagelist")
    public Object list(Page<Shipper> page){
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        IPage<Shipper> shipperCartonIPage = shipperService.page(page, new QueryWrapper<Shipper>());
        res.setData(shipperCartonIPage);
        return res;
    }

    @PostMapping("/batch/changeIsCount")
    @ResponseBody
    public Object batchChangeIsCount(@RequestBody  ArrayList<Shipper> shippers){
        WCSApiResponse<Object> res = new WCSApiResponse<>();
        shippers.forEach(shipper -> {
            if(YesOrNoEnum.YES.equalsIgnoreCase(shipper.getWhetherTheCount())){
                shipper.setWhetherTheCount(YesOrNoEnum.NO.toUpperCase());
            }else if(YesOrNoEnum.NO.equalsIgnoreCase(shipper.getWhetherTheCount())){
                shipper.setWhetherTheCount(YesOrNoEnum.YES.toUpperCase());
            }
            shipper.setVersion(shipper.getVersion()+1);
            shipper.setModifyTime(new Date());
        });
        boolean save = false;
        try {
            save = shipperService.updateBatchById(shippers);
        } catch (Exception e) {
            logger.info("修改失败:",e);
            res.failed("修改失败");
        } finally {
            if(save){
                res.success("修改成功");
            }else {
                res.failed("修改失败");
            }
            return res;
        }

    }
}
