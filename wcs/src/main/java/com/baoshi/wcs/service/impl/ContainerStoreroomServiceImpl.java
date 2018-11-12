package com.baoshi.wcs.service.impl;

import com.baoshi.wcs.entity.ContainerStoreroom;
import com.baoshi.wcs.dao.ContainerStoreroomMapper;
import com.baoshi.wcs.service.ContainerStoreroomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 包裹容器-库位绑定表 允许沉余多个关键字段 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-12
 */
@Service
public class ContainerStoreroomServiceImpl extends ServiceImpl<ContainerStoreroomMapper, ContainerStoreroom> implements ContainerStoreroomService {

}
