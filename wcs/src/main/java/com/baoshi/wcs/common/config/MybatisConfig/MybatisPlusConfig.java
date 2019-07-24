package com.baoshi.wcs.common.config.MybatisConfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan("com.baoshi.wcs.dao")
public class MybatisPlusConfig {

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
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
}