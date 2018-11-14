package com.baoshi.wcs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@MapperScan("com.baoshi.wcs.dao")
public class WcsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
