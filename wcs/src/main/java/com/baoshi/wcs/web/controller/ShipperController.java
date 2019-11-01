package com.baoshi.wcs.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.baoshi.wcs.web.basic.BaseController;

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

}
