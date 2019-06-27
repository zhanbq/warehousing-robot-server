package com.baoshi.wcs.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
@TableName("t_column")
public class Column extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 列宽1 列宽2 
     */
    private Integer columnNum;

    /**
     * 宽度
     */
    private Double wide;

    /**
     * 列深
     */
    private Double deep;

    /**
     * 单位 m
     */
    private String unit;

    /**
     * 货架id
     */
    private Integer shelvesId;

    public Integer getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(Integer columnNum) {
        this.columnNum = columnNum;
    }
    public Double getWide() {
        return wide;
    }

    public void setWide(Double wide) {
        this.wide = wide;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Integer getShelvesId() {
        return shelvesId;
    }

    public void setShelvesId(Integer shelvesId) {
        this.shelvesId = shelvesId;
    }

    public Double getDeep() {
        return deep;
    }

    public void setDeep(Double deep) {
        this.deep = deep;
    }
}
