package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 盛放包裹容器 上架入库/下架入库用 货架摆放装有包裹的容器
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class Container extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String code;

    /**
     * 容器类型(料筐,托盘)
     */
    private String type;

    /**
     * 长度
     */
    private BigDecimal length;

    /**
     * 宽度
     */
    private BigDecimal width;

    /**
     * 高度
     */
    private BigDecimal height;

    /**
     * 容器内部长度
     */
    private BigDecimal internalLength;

    /**
     * 容器内部宽度
     */
    private BigDecimal internalWidth;

    /**
     * 容器的总体积
     */
    private BigDecimal totalVolume;

    /**
     * 内部可用的容纳包裹票的容积
     */
    private BigDecimal internalVolume;

    /**
     * 容器内部高度
     */
    private BigDecimal internalHeight;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }
    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }
    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getInternalLength() {
        return internalLength;
    }

    public void setInternalLength(BigDecimal internalLength) {
        this.internalLength = internalLength;
    }

    public BigDecimal getInternalWidth() {
        return internalWidth;
    }

    public void setInternalWidth(BigDecimal internalWidth) {
        this.internalWidth = internalWidth;
    }

    public BigDecimal getInternalHeight() {
        return internalHeight;
    }

    public void setInternalHeight(BigDecimal internalHeight) {
        this.internalHeight = internalHeight;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getInternalVolume() {
        return internalVolume;
    }

    public void setInternalVolume(BigDecimal internalVolume) {
        this.internalVolume = internalVolume;
    }
}
