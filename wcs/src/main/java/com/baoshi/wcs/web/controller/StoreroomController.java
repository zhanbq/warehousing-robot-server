package com.baoshi.wcs.web.controller;


import com.baoshi.wcs.WcsApplication;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.entity.Storeroom;
import com.baoshi.wcs.service.StoreroomService;
import com.baoshi.wcs.vo.StorageVO;
import com.baoshi.wcs.vo.StoreroomVO;
import com.baoshi.wcs.web.basic.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 库位信息 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
@RestController
@RequestMapping("/storeroom")
public class StoreroomController extends BaseController {

    @Autowired
    StoreroomService storeroomService;

    /**
     * 保存 库位信息
     * @param storeroomVO
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public WCSApiResponse save(@RequestBody StoreroomVO storeroomVO){
        WCSApiResponse<Object> response = new WCSApiResponse<>();
        boolean res = storeroomService.save(storeroomVO);
        response.success(res);
        return response;
    }

    @PostMapping("/storage")
    public WCSApiResponse save(@RequestBody StorageVO storageVO){
        WCSApiResponse<Object> res = new WCSApiResponse<>();

        return res;
    }
}
