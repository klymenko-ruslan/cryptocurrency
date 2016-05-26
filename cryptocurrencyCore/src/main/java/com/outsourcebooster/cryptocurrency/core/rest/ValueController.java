package com.outsourcebooster.cryptocurrency.core.rest;

import com.outsourcebooster.cryptocurrency.core.jpa.ValueRepository;
import com.outsourcebooster.cryptocurrency.core.model.currency.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rklimemnko on 26.05.2016.
 */
@RestController
public class ValueController {

    @Autowired
    private ValueRepository valueRepository;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/value")
    public List<Value> getData() {
        return valueRepository.findAll();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/last")
    public Value getLast() {
        return valueRepository.findFirstByOrderByDateDesc();
    }
}