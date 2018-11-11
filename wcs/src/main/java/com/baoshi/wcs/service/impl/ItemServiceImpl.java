package com.baoshi.wcs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baoshi.wcs.dao.ItemMapper;
import com.baoshi.wcs.entity.Item;
import com.baoshi.wcs.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

}
