package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-07-07
 */
public class LastGoodsWeight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String lastBarCode;

    private String robotId;

    public String getLastBarCode() {
        return lastBarCode;
    }

    public void setLastBarCode(String lastBarCode) {
        this.lastBarCode = lastBarCode;
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

}
