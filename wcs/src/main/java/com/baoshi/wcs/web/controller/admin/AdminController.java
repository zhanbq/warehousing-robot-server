package com.baoshi.wcs.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping("/goodsweight")
    public String goodsweight(){
        return "goodsweight";
    }

}
