package com.baoshi.wcs.service;

import com.baoshi.wcs.entity.Express;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baoshi.wcs.vo.OrderVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2018-12-26
 */
public interface ExpressService extends IService<Express> {

    boolean saveBatchExpressList(OrderVO orderVO);
}
