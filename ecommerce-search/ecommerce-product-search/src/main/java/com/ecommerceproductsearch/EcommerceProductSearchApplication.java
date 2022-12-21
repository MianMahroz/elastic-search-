package com.ecommerceproductsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ecommerceproductsearch")
public class EcommerceProductSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductSearchApplication.class, args);
	}

}
