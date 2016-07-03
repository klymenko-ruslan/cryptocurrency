package com.outsourcebooster.cryptocurrency.web.model;

/**
 * Created by rklimemnko on 02.07.2016.
 */
public class Notification {
    private Currency currency;
    private boolean isPurchasable;
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isPurchasable() {
        return isPurchasable;
    }

    public void setIsPurchasable(boolean isPurchasable) {
        this.isPurchasable = isPurchasable;
    }
}
