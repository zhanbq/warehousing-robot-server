package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jobob
 * @since 2018-11-11
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型(设备/员工)
     */
    private String userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
