package com.truxxkart.sellerservice_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SellerserviceV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SellerserviceV1Application.class, args);
	}

}
