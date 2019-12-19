package com.baoshi.wcs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.pojo.GoodsWeight4ExcelExportPOJO;
import com.baoshi.wcs.vo.GoodsWeightVO;

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


    Page<GoodsWeight4ExcelExportPOJO> pageGoodsWeightAndRobot(Page<GoodsWeight4ExcelExportPOJO> gwPage, GoodsWeightVO goodsWeightVO);
}
