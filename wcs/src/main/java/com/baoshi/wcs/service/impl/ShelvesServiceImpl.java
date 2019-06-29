package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baoshi.wcs.common.exception.BaseException;
import com.baoshi.wcs.dao.ShelvesMapper;
import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.entity.Layer;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.service.ColumnService;
import com.baoshi.wcs.service.LayerService;
import com.baoshi.wcs.service.ShelvesService;
import com.baoshi.wcs.vo.ShelvesVO.ShelvesVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 货架信息管理 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@Service
public class ShelvesServiceImpl extends ServiceImpl<ShelvesMapper, Shelves> implements ShelvesService {

    @Autowired
    ShelvesMapper shelvesMapper;

    @Autowired
    ColumnService columnService;

    @Autowired
    LayerService layerService;

    @Override
    public boolean saveShelvesAndColumnsAndLayers(ShelvesVO shelvesVO) throws Exception {
        boolean isSucess = false;
        Shelves shelves = new Shelves();
        BeanUtils.copyProperties(shelvesVO,shelves);

        int insert = shelvesMapper.insert(shelves);
        if(insert<1){
            throw new Exception("新增货架设备失败");
        }

        Integer shelvesId = shelves.getId();
        List<Layer> layers = shelvesVO.getLayers();
        for(Layer layer : layers){
            layer.setShelvesId(shelvesId);
        }
        boolean saveBatchLayers = layerService.saveBatch(layers);
        if(!saveBatchLayers){
            throw new Exception("新增货架设备失败, 批量新增 层 失败");
        }

        List<Column> columns = shelvesVO.getColumns();
        for(Column column : columns){
            column.setShelvesId(shelvesId);
        }
        boolean saveBatchColumns = columnService.saveBatch(columns);
        if(!saveBatchColumns){
            throw new Exception("新增货架设备失败, 批量新增 列 失败");
        }
        isSucess = true;
        return isSucess;
    }


}
