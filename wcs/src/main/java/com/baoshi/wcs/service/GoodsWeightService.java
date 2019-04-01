package com.baoshi.wcs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baoshi.wcs.entity.GoodsWeight;

/**
 * <p>
 * 货物/包裹信息表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public interface GoodsWeightService extends IService<GoodsWeight> {


    GoodsWeight getLastGoodsweight(String robotKey);
}
