package com.baoshi.wcs.common.utils;

import com.baoshi.wcs.common.response.ApiResponse;
import org.springframework.http.HttpStatus;

public class ResponseUtil<T> {


    public static <T>  ApiResponse<T> success(String msg){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMsg(msg);
        return apiResponse;
    }

    public static <T>  ApiResponse<T> success(String msg , T data){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMsg(msg);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T>  ApiResponse<T> success(T data){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMsg("成功");
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T>  ApiResponse<T> faile(T data,String msg){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + msg);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T>  ApiResponse<T> faile(String msg , T data){
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponse.setMsg(msg);
        apiResponse.setData(data);
        return apiResponse;
    }
}
