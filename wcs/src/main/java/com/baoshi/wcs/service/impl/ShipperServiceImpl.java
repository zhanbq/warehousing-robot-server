package com.baoshi.wcs.service.impl;

import com.baoshi.wcs.entity.Shipper;
import com.baoshi.wcs.dao.ShipperMapper;
import com.baoshi.wcs.service.ShipperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanbq
 * @since 2019-11-01
 */
@Service
public class ShipperServiceImpl extends ServiceImpl<ShipperMapper, Shipper> implements ShipperService {

    @Autowired
    ShipperMapper shipperMapper;

    @Override
    public List<String> listAllShipperName() {
        return  shipperMapper.listAllShipperName();
    }
}
