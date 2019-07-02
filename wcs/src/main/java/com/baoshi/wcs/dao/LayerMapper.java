package com.baoshi.wcs.dao;

import com.baoshi.wcs.entity.Layer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 货架 层管理 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@Repository
public interface LayerMapper extends BaseMapper<Layer> {

    int batchInsertLayers(List<Layer> layers, @Param("shelvesId") Integer shelvesId);
}