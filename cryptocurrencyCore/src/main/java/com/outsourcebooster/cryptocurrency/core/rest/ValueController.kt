package com.outsourcebooster.cryptocurrency.core.rest;

import com.outsourcebooster.cryptocurrency.common.model.Currency
import com.outsourcebooster.cryptocurrency.common.model.Value
import com.outsourcebooster.cryptocurrency.core.repository.ValueRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

/**
 * Created by rklimemnko on 26.05.2016.
 */
@RestController
@CrossOrigin(*arrayOf("http://localhost:8081","http://localhost:4200"))
@RequestMapping("/value")
open class ValueController(val valueRepository: ValueRepository) {

    @RequestMapping("/all")
    fun getDataAll() = valueRepository.findAll()

    @RequestMapping("/minute")
    fun getDataMinute() = valueRepository.findByDateBetween(LocalDateTime.now().minusMinutes(1L), LocalDateTime.now())

    @RequestMapping("/hour")
    fun getDataHour() = valueRepository.findByDateBetween(LocalDateTime.now().minusHours(1L), LocalDateTime.now())

    @RequestMapping("/day")
    fun getDataDay() = valueRepository.findByDateBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now())

    @RequestMapping("/week")
    fun getDataWeek() = valueRepository.findByDateBetween(LocalDateTime.now().minusWeeks(1L), LocalDateTime.now())

    @RequestMapping("/month")
    fun getDataMonth() = valueRepository.findByDateBetween(LocalDateTime.now().minusMonths(1L), LocalDateTime.now())


    @RequestMapping("/year")
    fun getDataYear() = valueRepository.findByDateBetween(LocalDateTime.now().minusYears(1L), LocalDateTime.now())

    @RequestMapping("/last")
    fun getLast() = valueRepository.findFirstByOrderByDateDesc()

    @RequestMapping("/currencies")
    fun getCurrencies(): List<String> {
        val currencies = mutableListOf<String>();
        for(field in Value::class.java.getDeclaredFields()) {
            if(field.getType() == Currency::class.java) {
                currencies.add(field.getName());
            }
        }
        return currencies.toList();
    }
}
