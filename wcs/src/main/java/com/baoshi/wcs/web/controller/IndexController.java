package com.baoshi.wcs.web.controller;

import com.baoshi.wcs.common.response.WCSApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    public WCSApiResponse index(){
        WCSApiResponse<Object> response = new WCSApiResponse();
        response.setMsg("欢迎使用WCS系统");
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @GetMapping("/csrf")
    public WCSApiResponse csrf(){
        WCSApiResponse<Object> response = new WCSApiResponse();
        response.setMsg("欢迎使用WCS系统");
        response.setCode(HttpStatus.OK.value());
        return response;
    }
}
