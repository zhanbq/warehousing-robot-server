package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-03-26
 */
public class RobotInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 是否有效
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RobotInfo{" +
        "status=" + status +
        "}";
    }
}
