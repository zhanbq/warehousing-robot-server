package com.baoshi.wcs.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传路径
 */
@Component(value = "FilePath")
//@ConfigurationProperties(prefix = "wcs.filepath")
public class FilePath {


    public static String shipperCartonExcelFilePath;

    public String getShipperCartonExcelFilePath() {
        return shipperCartonExcelFilePath;
    }
    @Value("${wcs.filepath.shipper-carton}")
    public void setShipperCartonExcelFilePath(String shipperCartonExcelFilePath) {
        FilePath.shipperCartonExcelFilePath = shipperCartonExcelFilePath;
    }
}
