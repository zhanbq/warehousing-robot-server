package com.baoshi.wcs.entity;

import com.baoshi.wcs.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-07-04
 */
public class OldWmsOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String oldWmsOrderJson;

    private String barCode;

    public String getOldWmsOrderJson() {
        return oldWmsOrderJson;
    }

    public void setOldWmsOrderJson(String oldWmsOrderJson) {
        this.oldWmsOrderJson = oldWmsOrderJson;
    }
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public String toString() {
        return "OldWmsOrder{" +
        "oldWmsOrderJson=" + oldWmsOrderJson +
        ", barCode=" + barCode +
        "}";
    }
}
