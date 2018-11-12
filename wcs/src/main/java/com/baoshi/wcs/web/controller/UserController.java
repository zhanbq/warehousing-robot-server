package com.baoshi.wcs.web.controller;


import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.entity.User;
import com.baoshi.wcs.service.UserService;
import com.baoshi.wcs.web.basic.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public ApiResponse<Object> hello(){
        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(1);
        User user = new User();
//        user.setId();
        user.setUsername("zhanbq");
        user.setPassword("123");
        userService.save(user);
        return response;
    }
}
