package com.outsourcebooster.cryptocurrency.web.model;

/**
 * Created by rklimemnko on 30.05.2016.
 */
public class Account {
    private Currency currency;
    private String amount;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
