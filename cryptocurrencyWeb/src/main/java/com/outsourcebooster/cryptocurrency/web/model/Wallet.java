package com.outsourcebooster.cryptocurrency.web.model;

import java.util.List;

/**
 * Created by rklimemnko on 30.05.2016.
 */
public class Wallet {
    private List<Account> accounts;

    public Wallet() {}

    public Wallet(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
