package com.outsourcebooster.cryptocurrency.core.rest;

import com.outsourcebooster.cryptocurrency.core.repository.ValueRepository;
import com.outsourcebooster.cryptocurrency.core.model.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
    @RequestMapping("/all")
    public List<Value> getDataAll() {
        return valueRepository.findAll();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/minute")
    public List<Value> getDataMinute() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusMinutes(1l), LocalDateTime.now());
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/hour")
    public List<Value> getDataHour() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusHours(1l), LocalDateTime.now());
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/day")
    public List<Value> getDataDay() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusDays(1l), LocalDateTime.now());
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/week")
    public List<Value> getDataWeek() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusWeeks(1l), LocalDateTime.now());
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/month")
    public List<Value> getDataMonth() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusMonths(1l), LocalDateTime.now());
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("/year")
    public List<Value> getDataYear() {
        return valueRepository.findByDateBetween(LocalDateTime.now().minusYears(1l), LocalDateTime.now());
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/last")
    public Value getLast() {
        return valueRepository.findFirstByOrderByDateDesc();
    }
}