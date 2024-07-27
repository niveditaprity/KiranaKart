package com.ecommerce.KiranaKart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootTest
class KiranaKartApplicationTests {

	@Test
	void contextLoads() {
	}

}
