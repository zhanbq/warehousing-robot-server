package com.baoshi.wcs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baoshi.wcs.entity.GoodsWeight;
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
}
