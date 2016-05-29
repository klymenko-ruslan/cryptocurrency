package com.outsourcebooster;

import com.outsourcebooster.cryptocurrency.core.repository.ValueRepository;
import com.outsourcebooster.cryptocurrency.core.model.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@SpringBootApplication
public class CryptocurrencyCoreApplication {

	public static final String API_URL = "https://coinmarketcap-nexuist.rhcloud.com/api/";
	public static final String ALL = "all";

	public static final int SLEEP_SECONDS = 30 * 1000;

	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().exec("mongod");
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(CryptocurrencyCoreApplication.class);
		ValueRepository valueRepository = configurableApplicationContext.getBeanFactory().getBean(ValueRepository.class);
		while (true) {
			RestTemplate restTemplate = new RestTemplate();
			Value currencyValue = restTemplate.getForObject(API_URL + ALL, Value.class);
			valueRepository.save(currencyValue);
			try {
				Thread.currentThread().sleep(SLEEP_SECONDS);
			} catch (InterruptedException e) {}
		}
	}
}
