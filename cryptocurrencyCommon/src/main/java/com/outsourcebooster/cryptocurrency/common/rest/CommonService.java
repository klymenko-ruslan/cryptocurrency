package com.outsourcebooster.cryptocurrency.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hazelcast.core.*;
import com.hazelcast.config.*;
import java.util.Map;

/**
 * Created by rklimemnko on 03.09.2016.
 */
@RestController
public class CommonService {



    @RequestMapping("/test")
    public HttpEntity<Map<String, Object>> get() {

        Config cfg = new Config();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
        Map<String, Object> mapCustomers = instance.getMap("commonProperties");
        return new HttpEntity<>(mapCustomers);
    }

}
