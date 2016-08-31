package com.outsourcebooster.cryptocurrency.core.cryptocurrency.core.model;

/**
 * Created by rklimemnko on 24.05.2016.
 */
public class Price {
    private String usd;
    private String eur;
    private String cny;
    private String rub;

    public String getUsd() {
        return usd;
    }

    public void setUsd(String usd) {
        this.usd = usd;
    }

    public String getEur() {
        return eur;
    }

    public void setEur(String eur) {
        this.eur = eur;
    }

    public String getCny() {
        return cny;
    }

    public void setCny(String cny) {
        this.cny = cny;
    }

    public String getRub() {
        return rub;
    }

    public void setRub(String rub) {
        this.rub = rub;
    }
}
