package com.baoshi.wcs.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baoshi.wcs.entity.Container;
import com.baoshi.wcs.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baoshi.wcs.web.basic.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@RestController
@RequestMapping("/container")
public class ContainerController extends BaseController {

    @Autowired
    ContainerService containerService;

    @GetMapping("/all")
    @ResponseBody
    public Object findAll(){
        return containerService.list(new QueryWrapper<Container>());
    }
}
