package com.outsourcebooster.cryptocurrency.web.model.user

import com.outsourcebooster.cryptocurrency.web.model.account.CryptocurrencyAccount
import com.outsourcebooster.cryptocurrency.web.model.account.FiatAccount

/**
 * Created by rklimemnko on 30.05.2016.
 */
data class Wallet(val cryptocurrencyAccounts: List<CryptocurrencyAccount>,
                  val fiatAccounts: List<FiatAccount>) {
    constructor() : this(listOf(), listOf())
}