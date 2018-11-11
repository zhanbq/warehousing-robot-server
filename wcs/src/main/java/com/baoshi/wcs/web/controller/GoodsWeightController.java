package com.baoshi.wcs.web.controller;


import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.common.response.WCSApiResponse;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 货物/包裹信息表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
@RestController
@RequestMapping("/goods-weight")
public class GoodsWeightController extends BaseController {

    public ApiResponse<Object> hello(){
        return new WCSApiResponse<>();
    }

}
