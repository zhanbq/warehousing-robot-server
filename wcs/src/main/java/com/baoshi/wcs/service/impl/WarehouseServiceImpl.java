package com.baoshi.wcs.service.impl;

import com.baoshi.wcs.dao.WarehouseMapper;
import com.baoshi.wcs.entity.Warehouse;
import com.baoshi.wcs.service.WarehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    WarehouseMapper warehouseMapper;

    public boolean saveWarehouse(Warehouse warehouse){
        return warehouseMapper.insert(warehouse)>0;
    }
}
