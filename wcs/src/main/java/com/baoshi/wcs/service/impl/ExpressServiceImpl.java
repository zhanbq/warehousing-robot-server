package com.baoshi.wcs.service.impl;

import com.baoshi.wcs.entity.Express;
import com.baoshi.wcs.dao.ExpressMapper;
import com.baoshi.wcs.pojo.OrderDetailPojo;
import com.baoshi.wcs.service.ExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baoshi.wcs.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-12-26
 */
@Service
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements ExpressService {

    @Autowired
    ExpressMapper expressMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 500)
    public boolean saveBatchExpressList(OrderVO orderVO) {

        String orderNo = orderVO.getOrderNo();
        String owner = orderVO.getOwner();
        String waveNo = orderVO.getWaveNo();
        String waveType = orderVO.getWaveType();
        List<OrderDetailPojo> orderDetailList = orderVO.getOrderDetailList();
        ArrayList<Express> expresses = new ArrayList<>();
        for(OrderDetailPojo e: orderDetailList){
            Express express = new Express();
            BeanUtils.copyProperties(e,express);
            express.setOwner(owner);
            express.setOrderNo(orderNo);
            express.setWaveNo(waveNo);
            express.setWaveType(waveType);
            expresses.add(express);
        }
        boolean res = this.saveBatch(expresses);

        return res;
    }

    @Override
    public void checkExpressCode(String expressCode) {

    }
}
