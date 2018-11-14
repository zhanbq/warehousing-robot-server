package com.baoshi.wcs.manager;

import com.baoshi.wcs.common.enumeration.StorageroomStatusEnum;
import com.baoshi.wcs.vo.StorageVO;

/**
 * 出库 入库 业务 事务管理
 */
public interface StorageServiceManager {

    /**
     * 入库操作
     */
    public StorageroomStatusEnum putInStorage(StorageVO storageVO);
}
