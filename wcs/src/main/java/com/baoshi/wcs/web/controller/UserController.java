package com.baoshi.wcs.web.controller;


import com.baoshi.wcs.entity.User;
import com.baoshi.wcs.service.UserService;
import com.baoshi.wcs.web.basic.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{id}")
    @ResponseBody
    public Object getUserById(@PathVariable("id")int id){
        User user = userService.getById(id);
        return user;
    }
}
