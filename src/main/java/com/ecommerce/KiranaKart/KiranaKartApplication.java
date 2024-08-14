package com.ecommerce.KiranaKart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KiranaKartApplication {

	public static void main(String[] args) {
		SpringApplication.run(KiranaKartApplication.class, args);
	}

}
