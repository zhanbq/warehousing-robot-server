package com.baoshi.wcs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.baoshi.wcs.dao")
public class WcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcsApplication.class, args);
	}
}
