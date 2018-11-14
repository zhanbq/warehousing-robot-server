package com.baoshi.wcs.manager.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baoshi.wcs.WcsApplicationTests;
import com.baoshi.wcs.common.enumeration.StorageroomStatusEnum;
import com.baoshi.wcs.dao.StoreroomMapper;
import com.baoshi.wcs.entity.Storeroom;
import com.baoshi.wcs.service.StoreroomService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StorageServiceManagerImplTest{


    @Autowired
    StoreroomService storeroomService;

    @Autowired
    StoreroomMapper storeroomMapper;

    @Test
    public void putInStorage() {
    }

    @Test
    public void getOne(){
        Storeroom storeroom = new Storeroom();
        storeroom.setContainerType("1");
        HashMap<String, Object> map = new HashMap<>();
        map.put("container_type",1);
        Collection<Storeroom> storerooms = storeroomService.listByMap(map);
//        List<Storeroom> list = CollectionUtils.arrayToList(storerooms);
        ArrayList<Storeroom> storerooms1 = new ArrayList<>(storerooms);
        System.out.println(JSON.toJSONString(storerooms1));
    }

    @Test
    public void getList(){
        QueryWrapper<Storeroom> storeroomQueryWrapper = new QueryWrapper<>();
        storeroomQueryWrapper.in("status",new String[]{StorageroomStatusEnum.EMPTY.getStatusStr(),StorageroomStatusEnum.NOTFULL.getStatusStr()});
        Collection<Storeroom> resList = storeroomService.list(storeroomQueryWrapper);
        System.out.println(JSON.toJSONString(resList));
    }
}