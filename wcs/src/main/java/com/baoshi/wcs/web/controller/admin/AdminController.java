package com.baoshi.wcs.web.controller.admin;

import com.baoshi.wcs.common.response.WCSApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping("/goodsweight")
    public String goodsweight(){
        return "goodsweight";
    }

    /**
     * 查询最新的扫码重量数据
     * @return
     */
    @RequestMapping("")
    public Object getLastGoodsweight(){

        WCSApiResponse<Object> wcsApiResponse = new WCSApiResponse<>();

        return wcsApiResponse;

    }

}
