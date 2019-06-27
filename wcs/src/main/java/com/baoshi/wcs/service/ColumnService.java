package com.baoshi.wcs.service;

import com.baoshi.wcs.entity.Column;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
public interface ColumnService extends IService<Column> {

    boolean saveBatchColumns4Shelves(List<Column> columns,Integer shelvesId) throws Exception;
}
