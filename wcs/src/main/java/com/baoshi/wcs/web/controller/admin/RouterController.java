package com.baoshi.wcs.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouterController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index.html";
    }
}
