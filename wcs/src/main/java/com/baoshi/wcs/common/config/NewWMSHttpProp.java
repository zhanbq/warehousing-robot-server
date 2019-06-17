package com.baoshi.wcs.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 新的wms服务商的domain
 */
@Component(value = "NewWMSHttpProp")
@ConfigurationProperties(prefix = "newwms")
public class NewWMSHttpProp {


    public static String orderUrl;

    @Value("${newmws.host}")
    public void setOrderUrl(String orderUrl) {
        NewWMSHttpProp.orderUrl = orderUrl;
    }
}


