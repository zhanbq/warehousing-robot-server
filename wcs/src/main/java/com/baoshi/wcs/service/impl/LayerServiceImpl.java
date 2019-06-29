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

    @Override
    public boolean saveBatchLayers4Shelves(List<Layer> layers, Integer shelvesId) {

        Shelves shelves = null;
        boolean isSuccess = false;

        for(Layer c : layers){
            QueryWrapper<Layer> layerQueryWrapper = new QueryWrapper<>();
            layerQueryWrapper.eq("height",c.getHeight());
            //根据 深度 宽度 查询是否有相同数据,如果有 则不新增
            Layer cRes = layerMapper.selectOne(layerQueryWrapper);
            if(null == cRes){
                int cId = layerMapper.insert(c);
                if(cId < 0){
                    return isSuccess;
                }
            }else{
                c.setId(cRes.getId());
            }
            shelves = new Shelves();
            shelves.setColumnsId(c.getId());
            shelves.setId(shelvesId);
            int i = shelvesMapper.updateById(shelves);
            if(i<0){
                return isSuccess;
            }
        }
        isSuccess = true;
        return isSuccess;

    }
}
