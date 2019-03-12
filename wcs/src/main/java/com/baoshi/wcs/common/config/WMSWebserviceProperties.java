package com.baoshi.wcs.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "WMSWebserviceProperties")
@ConfigurationProperties(prefix = "com.wcs.wms")
public class WMSWebserviceProperties {
    private static String url;

    public static String getUrl() {
        return url;
    }

    @Value("${com.wcs.wms.url}")
    public void setUrl(String url) {

        WMSWebserviceProperties.url = url;
    }
}