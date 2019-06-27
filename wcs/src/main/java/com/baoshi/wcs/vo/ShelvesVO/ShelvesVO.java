package com.baoshi.wcs.vo.ShelvesVO;

import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.entity.Layer;
import com.baoshi.wcs.entity.Shelves;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShelvesVO extends Shelves implements Serializable {

    List<Column> columns = new ArrayList<>();

    List<Layer> layers = new ArrayList<>();

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }


}
