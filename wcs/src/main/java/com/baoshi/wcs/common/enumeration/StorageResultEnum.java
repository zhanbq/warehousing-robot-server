package com.baoshi.wcs.common.enumeration;

public enum StorageResultEnum {

    SUCCESS(1,"成功"),
    failed(0,"失败"),

    /**
     * 库位数量不足
     */
    NO_STORAGEROOM_AVAILABLE(2,"无可用库位,库位数量不足"),

    INSUFFICIENT_STORAGEROOM_REMAIN_AVAILABLE(3,"可用库位剩余空间不足");

    int status;

    String result;

    StorageResultEnum(int status, String result) {
        this.status = status;
        this.result = result;
    }

}
