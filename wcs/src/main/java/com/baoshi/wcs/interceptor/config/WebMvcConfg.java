package com.baoshi.wcs.interceptor.config;

import com.baoshi.wcs.interceptor.RequestorInterceptor;
import com.baoshi.wcs.service.RequestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Autowired
    RequestorService requestorService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RequestorInterceptor(requestorService)).addPathPatterns("/**");
    }
}
