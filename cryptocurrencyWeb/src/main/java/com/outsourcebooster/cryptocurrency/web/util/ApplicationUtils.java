package com.outsourcebooster.cryptocurrency.web.util;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

/**
 * Created by rklimemnko on 03.09.2016.
 */
public class ApplicationUtils {

    public static String getCommonProperty(String propertyName) {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map<String, Object> commonPropertiesMap = instance.getMap("commonProperties");
        return (String)commonPropertiesMap.get(propertyName);
    }

    public static Map<String, Object> getCommonPropertiesMap() {
        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        return instance.getMap("commonProperties");
    }
}
