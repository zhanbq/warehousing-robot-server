package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.dao.GoodsWeightMapper;
import com.baoshi.wcs.service.GoodsWeightService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoodsWeightServiceImpl extends ServiceImpl<GoodsWeightMapper,GoodsWeight> implements GoodsWeightService {

    @Autowired
    GoodsWeightMapper goodsWeightMapper;

    @Override
    public GoodsWeight getLastGoodsweight(String gwRobotId) {

        return goodsWeightMapper.getLastGoodsweight(gwRobotId);
    }
}
