package com.baoshi.wcs.service.impl;

import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.dao.GoodsWeightMapper;
import com.baoshi.wcs.service.GoodsWeightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoodsWeightServiceImpl implements GoodsWeightService {

    @Autowired
    GoodsWeightMapper goodsWeightMapper;

    @Override
    public Boolean saveOne(GoodsWeight goodsWeight) {
        int res = goodsWeightMapper.saveOne(goodsWeight);
        return res>0;
    }
}
