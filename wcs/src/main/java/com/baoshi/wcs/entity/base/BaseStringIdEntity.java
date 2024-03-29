package com.baoshi.wcs.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class BaseStringIdEntity {

    @TableId(value = "id",type = IdType.NONE)
    String id;

    /**
     * 创建时间
     */
    Date createTime;

    /**
     * 修改时间
     */
    Date modifyTime;

    /**
     * 创建人
     */
    protected Integer creator;

    /**
     * 修改人
     */
    protected Integer modifier;

    /**
     * 版本号
     */
    int version;

    public BaseStringIdEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
//
//    public Integer getCreator() {
//        return creator;
//    }
//
//    public void setCreator(Integer creator) {
//        this.creator = creator;
//    }
//
//    public Integer getModifier() {
//        return modifier;
//    }
//
//    public void setModifier(Integer modifier) {
//        this.modifier = modifier;
//    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
