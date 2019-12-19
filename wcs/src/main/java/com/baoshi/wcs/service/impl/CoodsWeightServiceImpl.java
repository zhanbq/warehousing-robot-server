package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baoshi.wcs.entity.GoodsWeight;
import com.baoshi.wcs.dao.GoodsWeightMapper;
import com.baoshi.wcs.pojo.GoodsWeight4ExcelExportPOJO;
import com.baoshi.wcs.service.GoodsWeightService;

import com.baoshi.wcs.vo.GoodsWeightVO;
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

    @Override
    public Page<GoodsWeight4ExcelExportPOJO> pageGoodsWeightAndRobot(Page<GoodsWeight4ExcelExportPOJO> gwPage, GoodsWeightVO goodsWeightVO) {

        return goodsWeightMapper.pageGoodsWeightAndRobot(gwPage, goodsWeightVO);
    }
}
