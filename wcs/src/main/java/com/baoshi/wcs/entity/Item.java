package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class Item extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品编码
     */
    private String skuCode;

    /**
     * 商品条码
     */
    private String barCode;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品规格
     */
    private String specification;

    /**
     * 包装单位
     */
    private String packingUnit;

    /**
     * 长
     */
    private BigDecimal length;

    /**
     * 宽
     */
    private BigDecimal width;

    /**
     * 高
     */
    private BigDecimal height;

    /**
     * 商品数量
     */
    private Integer quantity;

    private Date modifyTime;

    /**
     * 修改人
     */
    private Integer modifier;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
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
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "Item{" +
        "skuCode=" + skuCode +
        ", barCode=" + barCode +
        ", name=" + name +
        ", specification=" + specification +
        ", packingUnit=" + packingUnit +
        ", length=" + length +
        ", width=" + width +
        ", height=" + height +
        ", quantity=" + quantity +
        ", modifyTime=" + modifyTime +
        ", modifier=" + modifier +
        "}";
    }
}
