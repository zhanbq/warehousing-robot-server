package com.baoshi.wcs.web.test;

import com.baoshi.wcs.common.response.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/ex")
    @ResponseBody
    public ApiResponse<String> testEx() throws Exception{
        Exception exception = new Exception();
        throw exception;
    }

    @RequestMapping("/rx")
    @ResponseBody
    public ApiResponse<String> testRx(){
        RuntimeException exception = new RuntimeException();
        throw exception;
    }

}
