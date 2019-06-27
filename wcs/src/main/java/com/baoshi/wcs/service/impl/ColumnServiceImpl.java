package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baoshi.wcs.dao.ShelvesMapper;
import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.dao.ColumnMapper;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.service.ColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements ColumnService {

    @Autowired
    ColumnMapper columnMapper;

    @Autowired
    ShelvesMapper shelvesMapper;

    @Override
    public boolean saveBatchColumns4Shelves(List<Column> columns,Integer shelvesId) throws Exception {
        Shelves shelves = null;
        boolean isSuccess = false;

        for(Column c : columns){
            QueryWrapper<Column> columnQueryWrapper = new QueryWrapper<>();
            columnQueryWrapper.eq("deep",c.getDeep());
            columnQueryWrapper.eq("wide",c.getWide());
            //根据 深度 宽度 查询是否有相同数据,如果有 则不新增
            Column cRes = columnMapper.selectOne(columnQueryWrapper);
            if(null == cRes){
                int cId = columnMapper.insert(c);
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
