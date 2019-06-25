package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-06-25
 */
public class Container extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 容器类型
     */
    private String typeName;

    /**
     * 容器类型编号
     */
    private String typeCode;

    /**
     * 容器编号
     */
    private String code;

    /**
     * 长
     */
    private Double length;

    /**
     * 宽
     */
    private Double high;

    /**
     * 深
     */
    private Double deep;

    /**
     * 格口数
     */
    private Integer holeNum;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getDeep() {
        return deep;
    }

    public void setDeep(Double deep) {
        this.deep = deep;
    }
    public Integer getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(Integer holeNum) {
        this.holeNum = holeNum;
    }

    @Override
    public String toString() {
        return "Container{" +
        "typeName=" + typeName +
        ", typeCode=" + typeCode +
        ", code=" + code +
        ", length=" + length +
        ", high=" + high +
        ", deep=" + deep +
        ", holeNum=" + holeNum +
        "}";
    }
}
