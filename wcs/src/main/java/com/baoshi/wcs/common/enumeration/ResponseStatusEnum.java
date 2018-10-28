package com.baoshi.wcs.common.enumeration;


public enum  ResponseStatusEnum {

    //    INTERNAL
    InternalRuntimeError("",1);

    private String errorType;
    private int code;
    private ResponseStatusEnum(String s, int i){
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
