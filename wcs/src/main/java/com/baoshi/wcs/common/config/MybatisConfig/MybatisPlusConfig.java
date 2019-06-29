package com.baoshi.wcs.common.config.mybatisconfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration

@MapperScan("com.baoshi.wcs.dao")

public class MybatisPlusConfig {

    /**
     * 分页插件
     */

    @Bean
    public PaginationInterceptor PaginationInterceptorpaginationInterceptor() {

        return new PaginationInterceptor();

    }

    /**
     * 打印 sql
     */

    @Bean
    public PerformanceInterceptor PerformanceInterceptorperformanceInterceptor() {

        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

        //格式化sql语句

        Properties properties = new Properties();

        properties.setProperty("format", "false");

        performanceInterceptor.setProperties(properties);

        return performanceInterceptor;

    }

}