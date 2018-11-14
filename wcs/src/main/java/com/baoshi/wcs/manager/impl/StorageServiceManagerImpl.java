package com.baoshi.wcs.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baoshi.wcs.common.enumeration.StorageroomStatusEnum;
import com.baoshi.wcs.entity.Storeroom;
import com.baoshi.wcs.manager.StorageServiceManager;
import com.baoshi.wcs.pojo.StoreroomPojo;
import com.baoshi.wcs.service.StoreroomService;
import com.baoshi.wcs.vo.StorageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class StorageServiceManagerImpl implements StorageServiceManager {

    @Autowired
    StoreroomService storeroomService;


    /**
     * 包裹入库上架 返回库位
     * @param storageVO
     * @return
     */
    @Override
    public StorageroomStatusEnum putInStorage(StorageVO storageVO) {

        String containerType = storageVO.getContainerType();
        /**
         * 获取可用库位,包括未装满 已装满
         */
        StoreroomPojo availableStorerooms = getAvailableStoreroomsByContainerType(containerType);

        /**
         * 优先计算未装满库位
         */
        List<Storeroom> notfullStorerooms = availableStorerooms.getNotfullStoreroom();
        if(CollectionUtils.isEmpty(notfullStorerooms)){
            //无 未装满的 库位
        }else{
            //有未装满的库位
        }

        return null;
    }

    /**
     * 根据 容器类型 获取所用可用 库位
     * @param containerType
     * @return
     */
    private StoreroomPojo getAvailableStoreroomsByContainerType(String containerType){
        /**
         * 查询空库位
         */
        QueryWrapper<Storeroom> emptyStoreroomQueryWrapper = new QueryWrapper<>();
        emptyStoreroomQueryWrapper.eq("status",StorageroomStatusEnum.EMPTY.getStatusStr());
        Collection<Storeroom> emptyStoreroomResList = storeroomService.list(emptyStoreroomQueryWrapper);
        List emptyStorerooms = CollectionUtils.arrayToList(emptyStoreroomResList);
        emptyStoreroomQueryWrapper = null;//回收

        QueryWrapper<Storeroom> notfullStoreroomQueryWrapper = new QueryWrapper<>();
        emptyStoreroomQueryWrapper.eq("status",StorageroomStatusEnum.NOTFULL.getStatusStr());
        Collection<Storeroom> resList = storeroomService.list(notfullStoreroomQueryWrapper);
        List notFullStorerooms = CollectionUtils.arrayToList(resList);
        notfullStoreroomQueryWrapper = null;//回收

        StoreroomPojo storeroomPojo = new StoreroomPojo();
        storeroomPojo.setEmptyStorerooms(emptyStorerooms);
        storeroomPojo.setNotfullStoreroom(notFullStorerooms);
        return storeroomPojo;
    }



}
