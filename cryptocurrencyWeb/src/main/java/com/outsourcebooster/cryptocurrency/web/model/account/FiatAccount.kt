package com.outsourcebooster.cryptocurrency.web.model.account

import com.outsourcebooster.cryptocurrency.web.model.currency.FiatCurrency

data class FiatAccount(val fiatCurrency: FiatCurrency,
                       val amount: String)