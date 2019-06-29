package com.baoshi.wcs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baoshi.wcs.entity.Shelves;
import com.baoshi.wcs.vo.ShelvesVO.ShelvesVO;

/**
 * <p>
 * 货架信息管理 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
public interface ShelvesService extends IService<Shelves> {


    boolean saveShelvesAndColumnsAndLayers(ShelvesVO shelvesVO) throws Exception;
}
