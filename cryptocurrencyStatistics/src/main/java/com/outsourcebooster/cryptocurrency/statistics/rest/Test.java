package com.outsourcebooster.cryptocurrency.statistics.rest;

import com.outsourcebooster.cryptocurrency.statistics.TestEntity;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rklimemnko on 26.08.2016.
 */
@RestController
public class Test {

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public HttpEntity<Integer> test() {
        return new HttpEntity<Integer>(new TestEntity().x());
    }
}
