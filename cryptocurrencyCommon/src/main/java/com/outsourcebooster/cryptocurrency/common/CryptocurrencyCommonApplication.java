package com.outsourcebooster.cryptocurrency.common;

import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class CryptocurrencyCommonApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CryptocurrencyCommonApplication.class, args);
		setUpProperties(context);
	}

	private static void setUpProperties(ApplicationContext context) {
		Environment environment = context.getBean(Environment.class);
		Map<String, Object> commonPropertiesMap = ApplicationUtils.getCommonPropertiesMap();
		for(Iterator it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext(); ) {
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof MapPropertySource && propertySource.getName().startsWith("applicationConfig")) {
				commonPropertiesMap.putAll(((MapPropertySource) propertySource).getSource());
			}
		}
	}
}
