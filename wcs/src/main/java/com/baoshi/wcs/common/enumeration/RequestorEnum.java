package com.baoshi.wcs.common.enumeration;

import com.alibaba.fastjson.JSON;

/**
 * 服务请求者 身份
 */
public enum RequestorEnum {

    GENERAL_USER(1,"普通用户"),
    ROBOT_USER(0,"机器"),
    ERROR(3,"错误,不存在的状态码");

    RequestorEnum(int code, String role) {
        this.code = code;
        this.role = role;
    }

    int code;
    String role;

    public static String getRole(int code){

        for(RequestorEnum requestorEnum:RequestorEnum.values()){
            if(requestorEnum.getCode() == code){
                return requestorEnum.getRole();
            }
        }
        return null;
    }


    public String getRole() {
        return role;
    }

    public int getCode() {
        return code;
    }

}
