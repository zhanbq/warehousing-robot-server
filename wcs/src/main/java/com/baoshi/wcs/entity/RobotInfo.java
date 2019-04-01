package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;
import com.baoshi.wcs.entity.base.BaseStringIdEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-03-30
 */
public class RobotInfo {

    private static final long serialVersionUID = 1L;

    String id;

    /**
     * 是否有效 1 有效 0 无效
     */
    private String status;

    private String code;

    private String name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RobotInfo{" +
        "status=" + status +
        ", code=" + code +
        ", name=" + name +
        "}";
    }
}
