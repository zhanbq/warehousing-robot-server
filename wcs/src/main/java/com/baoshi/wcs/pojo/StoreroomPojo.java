package com.baoshi.wcs.pojo;

import com.baoshi.wcs.entity.Storeroom;
import com.baoshi.wcs.vo.StoreroomVO;

import java.util.ArrayList;
import java.util.List;

public class StoreroomPojo {

    List<Storeroom> fullStorerooms = new ArrayList<>();

    List<Storeroom> emptyStorerooms =  new ArrayList<>();

    List<Storeroom> notfullStoreroom = new ArrayList<>();

    public StoreroomPojo() {
    }

    public List<Storeroom> getFullStorerooms() {
        return fullStorerooms;
    }

    public void setFullStorerooms(List<Storeroom> fullStorerooms) {
        this.fullStorerooms = fullStorerooms;
    }

    public List<Storeroom> getEmptyStorerooms() {
        return emptyStorerooms;
    }

    public void setEmptyStorerooms(List<Storeroom> emptyStorerooms) {
        this.emptyStorerooms = emptyStorerooms;
    }

    public List<Storeroom> getNotfullStoreroom() {
        return notfullStoreroom;
    }

    public void setNotfullStoreroom(List<Storeroom> notfullStoreroom) {
        this.notfullStoreroom = notfullStoreroom;
    }
}
