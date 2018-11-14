package com.baoshi.wcs.common.enumeration;

/**
 * 库位状态枚举
 */
public enum StorageroomStatusEnum {

    EMPTY(0,"空库位"),
    NOTFULL(1,"未装满"),
    FULL(2,"满库位"),
    ERROR(3,"错误,不存在的状态码");
    int status;

    String val;

    StorageroomStatusEnum(int status, String val) {
        this.status = status;
        this.val = val;
    }

    public static String getValByStatus(int status){

        for(StorageroomStatusEnum s:StorageroomStatusEnum.values()){
            if(s.getStatus() == status){
                return s.getVal();
            }
        }
        return ERROR.getVal();
    };

    public int getStatus() {
        return status;
    }
    public String getStatusStr() {
        return Integer.toString(this.status);
    }

    public String getVal() {
        return val;
    }
}
