package com.outsourcebooster.cryptocurrency.web.model.user

import com.outsourcebooster.cryptocurrency.web.model.currency.CryptoCurrency

/**
 * Created by rklimemnko on 02.07.2016.
 */
data class NotificationRule(val currency: CryptoCurrency?,
                            val price: String) {
    constructor(): this(null, "")
}