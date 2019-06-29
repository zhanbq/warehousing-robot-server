package com.baoshi.wcs.vo.ShelvesVO;

import com.baoshi.wcs.entity.Column;
import com.baoshi.wcs.entity.Layer;
import com.baoshi.wcs.entity.Shelves;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShelvesVO extends Shelves implements Serializable {

    List<Column> columns = new ArrayList<>();

    List<Layer> layers = new ArrayList<>();

    /**
     * 绑定的容器类型
     */
    List<String> containerTypes = new ArrayList<>();

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

    public List<String> getContainerTypes() {
        return containerTypes;
    }

    public void setContainerTypes(List<String> containerTypes) {
        this.containerTypes = containerTypes;
    }

    public String getStringContainerTypes(){
        String strContainerTypes = "";
        if(CollectionUtils.isEmpty(this.containerTypes)){
            return strContainerTypes;
        }

        for(String ct : containerTypes){
            strContainerTypes += ct;
            if (containerTypes.indexOf(ct) < (containerTypes.size()-1)){
             strContainerTypes+=",";
            }
        }
        return strContainerTypes;
    }
}
