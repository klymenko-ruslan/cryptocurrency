package com.outsourcebooster.cryptocurrency.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class CryptocurrencyWebApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(CryptocurrencyWebApplication.class);
	}
}