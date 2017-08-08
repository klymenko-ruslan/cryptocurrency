package com.outsourcebooster.cryptocurrency.core.rest;

import com.outsourcebooster.cryptocurrency.core.model.Currency;
import com.outsourcebooster.cryptocurrency.core.model.Value;
import com.outsourcebooster.cryptocurrency.core.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rklimemnko on 26.05.2016.
 */
@RestController
@CrossOrigin({"http://localhost:8081","http://localhost:4200"})
@RequestMapping("/value")
public class ValueController {

    @Autowired
    private ValueRepository valueRepository;

    @RequestMapping("/all")
    public List<Value> getDataAll() {
        return valueRepository.findAll();
    }

    @RequestMapping("/minute")
    public List<Value> getDataMinute() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusMinutes(1l), LocalDateTime.now());
    }

    @RequestMapping("/hour")
    public List<Value> getDataHour() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusHours(1l), LocalDateTime.now());
    }

    @RequestMapping("/day")
    public List<Value> getDataDay() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusDays(1l), LocalDateTime.now());
    }

    @RequestMapping("/week")
    public List<Value> getDataWeek() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusWeeks(1l), LocalDateTime.now());
    }

    @RequestMapping("/month")
    public List<Value> getDataMonth() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusMonths(1l), LocalDateTime.now());
    }


    @RequestMapping("/year")
    public List<Value> getDataYear() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusYears(1l), LocalDateTime.now());
    }

    @RequestMapping("/last")
    public Value getLast() {
        return valueRepository.findFirstByOrderByDateDesc();
    }

    @RequestMapping("/currencies")
    public List<String> getCurrencies() {
        List<String> currencies = new ArrayList<>();
        for(Field field : Value.class.getDeclaredFields()) {
            if(field.getType() == Currency.class) {
                currencies.add(field.getName());
            }
        }
        return currencies;
    }
}
