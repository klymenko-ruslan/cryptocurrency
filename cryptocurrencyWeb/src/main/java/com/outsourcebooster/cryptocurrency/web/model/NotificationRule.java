package com.outsourcebooster.cryptocurrency.web.model;

/**
 * Created by rklimemnko on 02.07.2016.
 */
public class NotificationRule {

    private Currency currency;
    private String price;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
