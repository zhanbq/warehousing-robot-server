package com.baoshi.wcs.web.basic;

import com.baoshi.wcs.common.response.ApiResponse;
import com.baoshi.wcs.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse<Object> handlerException(Exception ex){
        logger.error("ex :",ex);
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
        logger.error("ex :",ex);
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMsg("程序开小差了");
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ApiResponse<Object> buserException(BaseException ex){
        logger.error("ex :",ex);
        ApiResponse<Object> errorResponse = new ApiResponse<>();
        errorResponse.setMsg(ex.getMsg());
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }
}

