package com.outsourcebooster.cryptocurrency.core;

import com.outsourcebooster.cryptocurrency.common.model.Value;
import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils;
import com.outsourcebooster.cryptocurrency.core.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CryptocurrencyCoreApplication {

    @Autowired
    private Environment environment;

    @Autowired
    private ValueRepository valueRepository;

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("mongod");
        SpringApplication.run(CryptocurrencyCoreApplication.class);
    }

    @PostConstruct
    public void loadProperties() {
        ApplicationUtils.getCommonPropertiesMap().put("cryptocurrency.core.port", environment.getProperty("server.port"));
    }

    @PostConstruct
    public void startUpdater() {
        while (true) {
            RestTemplate restTemplate = new RestTemplate();
            Value currencyValue = restTemplate.getForObject(ApplicationUtils.getCommonProperty("cryptocurrency.core.external.exchange.api.url"), Value.class);
            valueRepository.save(currencyValue);
            try {
                Thread.currentThread().sleep(getSleepSeconds());
            } catch (InterruptedException e) {}
        }
    }

	private static int getSleepSeconds() {
		return Integer.parseInt(ApplicationUtils.getCommonProperty("cryptocurrency.core.update.interval.in.seconds")) * 1000;
	}

}