package com.baoshi.wcs.vo;

import com.baoshi.wcs.entity.Item;
import com.baoshi.wcs.entity.Storeroom;

import java.util.List;

/**
 * 入库上架信息接口
 */
public class StorageVO extends Storeroom {

    /**
     * 商品 清单明细
     */
    List<ItemVO> itemList;

    public List<ItemVO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemVO> itemList) {
        this.itemList = itemList;
    }
}
