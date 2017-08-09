package com.outsourcebooster.cryptocurrency.web;

import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CryptocurrencyWebApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CryptocurrencyWebApplication.class);
		loadProperties(context);
	}

	private static void loadProperties(ApplicationContext context) {
		Environment environment = context.getBean(Environment.class);
		ApplicationUtils.getCommonPropertiesMap().put("cryptocurrency.web.port", environment.getProperty("server.port"));
	}
}