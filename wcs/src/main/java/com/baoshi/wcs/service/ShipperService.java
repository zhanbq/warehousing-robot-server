package com.baoshi.wcs.service;

import com.baoshi.wcs.entity.Shipper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanbq
 * @since 2019-11-01
 */
public interface ShipperService extends IService<Shipper> {

    List<String> listAllShipperName();
}
