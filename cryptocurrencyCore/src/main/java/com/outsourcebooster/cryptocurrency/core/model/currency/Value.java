package com.outsourcebooster.cryptocurrency.core.model.currency;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by rklimemnko on 26.05.2016.
 */
public class Value {
    @Id
    private String id;

    private Currency btc;
    private Currency eth;
    private LocalDateTime date;

    public Value() {
        date = LocalDateTime.now();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getBtc() {
        return btc;
    }

    public void setBtc(Currency btc) {
        this.btc = btc;
    }

    public Currency getEth() {
        return eth;
    }

    public void setEth(Currency eth) {
        this.eth = eth;
    }

    public Timestamp getDate() {
        return Timestamp.valueOf(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
