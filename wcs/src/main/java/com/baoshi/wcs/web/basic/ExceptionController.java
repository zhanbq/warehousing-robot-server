package com.baoshi.wcs.web.basic;

import com.baoshi.wcs.common.ApiResponse;
import com.baoshi.wcs.common.exception.BaseException;
import com.baoshi.wcs.common.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<Object> handlerException(Exception ex){
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            errorResponse.setCode(HttpStatus.OK.value());
            errorResponse.setMsg("哎呦,你想去哪里");
        } else {
            errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setMsg("程序开小差了");
        }
        return errorResponse;
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ApiResponse<Object> baseException(RuntimeException ex){
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMsg("用户信息错误");
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ApiResponse<Object> buserException(BaseException ex){
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMsg(ex.getMsg());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }
}

