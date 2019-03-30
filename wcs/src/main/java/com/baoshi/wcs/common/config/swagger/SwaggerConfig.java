/*
package com.baoshi.wcs.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig  {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.baoshi.wcs.web.controller"))
                .paths(PathSelectors.regex("/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "宝时WCS系统API",
                "欢迎访问宝时WCS系统API接口文档.",
                "V0.0.1",
                "服务调用系统",
                new Contact("zhanbq", "www.example.com", "zhanbq@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}*/
