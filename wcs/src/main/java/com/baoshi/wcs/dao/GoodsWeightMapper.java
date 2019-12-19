package com.baoshi.wcs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.pojo.GoodsWeight4ExcelExportPOJO;
import com.baoshi.wcs.vo.GoodsWeightVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 货物/包裹信息表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
@Repository
public interface GoodsWeightMapper extends BaseMapper<GoodsWeight> {

    GoodsWeight getLastGoodsweight(@Param("gwRobotId") String robotKey);

    Page<GoodsWeight4ExcelExportPOJO> pageGoodsWeightAndRobot(Page<GoodsWeight4ExcelExportPOJO> gwPage, @Param("gwVO")GoodsWeightVO goodsWeightVO);
}
