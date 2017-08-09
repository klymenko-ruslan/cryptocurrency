package com.outsourcebooster.cryptocurrency.datagrid;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils;

public class CryptocurrencyDatagridApplication {

	public static void main(String[] args) throws IOException {
        ApplicationUtils.getCommonPropertiesMap().putAll(getProperties());
    }
    private static Map<String, String> getProperties() {
        return new HashMap<String, String>() {
            {
                put("cryptocurrency.common.url", "http://localhost");
                put("cryptocurrency.core.url", "http://localhost");
                put("cryptocurrency.core.update.interval.in.seconds", "30");
                put("cryptocurrency.core.external.exchange.api.url", "https://coinmarketcap-nexuist.rhcloud.com/api/all");
                put("cryptocurrency.web.url", "http://localhost");
                put("cryptocurrency.web.images.path", "C:/Users/rklim/img/user/");
                put("cryptocurrency.statistics.url", "http://localhost");
            }
        };
    }
}
