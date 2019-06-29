package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baoshi.wcs.dao.ShelvesMapper;
import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.entity.Layer;
import com.baoshi.wcs.dao.LayerMapper;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.service.LayerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 货架 层管理 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@Service
public class LayerServiceImpl extends ServiceImpl<LayerMapper, Layer> implements LayerService {

    @Autowired
    LayerMapper layerMapper;

    @Autowired
    ShelvesMapper shelvesMapper;

}
