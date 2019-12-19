package com.baoshi.wcs.dao;

import com.baoshi.wcs.entity.Shipper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanbq
 * @since 2019-11-01
 */
@Repository
public interface ShipperMapper extends BaseMapper<Shipper> {

    @Select("select shipper_name from shipper")
    List<String> listAllShipperName();
}
