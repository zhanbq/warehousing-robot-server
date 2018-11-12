package com.baoshi.wcs.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    Integer id;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 修改时间
     */
    Date updateTime;

    /**
     * 创建人
     */
    Integer creator;

    /**
     * 修改人
     */
    Integer updator;

    /**
     * 版本号
     */
    int version;

    public BaseEntity() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
