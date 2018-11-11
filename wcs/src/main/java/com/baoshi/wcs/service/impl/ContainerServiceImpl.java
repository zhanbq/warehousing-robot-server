package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baoshi.wcs.dao.ContainerMapper;
import com.baoshi.wcs.entity.Container;
import com.baoshi.wcs.service.ContainerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 盛放包裹容器 上架入库/下架入库用 货架摆放装有包裹的容器 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
@Service
public class ContainerServiceImpl extends ServiceImpl<ContainerMapper, Container> implements ContainerService {

}
