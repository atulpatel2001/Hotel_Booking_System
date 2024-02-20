package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayHotelApplication.class, args);
	}

}
