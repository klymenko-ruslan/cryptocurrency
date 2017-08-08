package com.outsourcebooster.cryptocurrency.core;

import com.outsourcebooster.cryptocurrency.core.model.Value;
import com.outsourcebooster.cryptocurrency.core.repository.ValueRepository;
import com.outsourcebooster.cryptocurrency.core.util.ApplicationUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@SpringBootApplication
public class CryptocurrencyCoreApplication {

	public static void main(String[] args) throws IOException {

		Runtime.getRuntime().exec("mongod");

		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(CryptocurrencyCoreApplication.class);

		loadProperties(configurableApplicationContext);
		startDataUpdate(configurableApplicationContext);
	}

	private static int getSleepSeconds() {
		return Integer.parseInt(ApplicationUtils.getCommonProperty("cryptocurrency.core.update.interval.in.seconds")) * 1000;
	}

	private static void loadProperties(ApplicationContext context) {
		Environment environment = context.getBean(Environment.class);
		ApplicationUtils.getCommonPropertiesMap().put("cryptocurrency.core.port", environment.getProperty("server.port"));
	}

	private static void startDataUpdate(ConfigurableApplicationContext context) {
		ValueRepository valueRepository = context.getBeanFactory().getBean(ValueRepository.class);
		while (true) {
			RestTemplate restTemplate = new RestTemplate();
			Value currencyValue = restTemplate.getForObject(ApplicationUtils.getCommonProperty("cryptocurrency.core.external.exchange.api.url"), Value.class);
			valueRepository.save(currencyValue);
			try {
				Thread.currentThread().sleep(getSleepSeconds());
			} catch (InterruptedException e) {}
		}
	}
}