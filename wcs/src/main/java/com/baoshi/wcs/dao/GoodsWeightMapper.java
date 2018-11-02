package com.baoshi.wcs.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baoshi.wcs.entity.GoodsWeight;

import org.springframework.stereotype.Repository;

@Repository
public interface GoodsWeightMapper extends BaseMapper<GoodsWeight> {

    int saveOne(GoodsWeight goodsWeight);
}
