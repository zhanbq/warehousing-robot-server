package com.baoshi.wcs.web.basic;

import com.baoshi.wcs.common.ApiResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> baseException(Exception ex){
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMsg();
    }
}

