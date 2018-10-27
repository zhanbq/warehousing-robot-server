package com.baoshi.wcs.dao;


import com.baoshi.wcs.entity.GoodsWeight;

import org.springframework.stereotype.Repository;

@Repository
public interface GoodsWeightMapper {

    int saveOne(GoodsWeight goodsWeight);
}
