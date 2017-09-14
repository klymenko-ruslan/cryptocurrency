package com.outsourcebooster.cryptocurrency.web.model.account

import com.outsourcebooster.cryptocurrency.web.model.currency.CryptoCurrency

/**
 * Created by rklimemnko on 30.05.2016.
 */
data class CryptocurrencyAccount(val cryptoCurrency: CryptoCurrency,
                                 val amount: String)
