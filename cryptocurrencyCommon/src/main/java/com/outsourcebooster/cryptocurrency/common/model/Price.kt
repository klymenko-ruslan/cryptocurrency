package com.outsourcebooster.cryptocurrency.common.model

/**
 * Created by rklimemnko on 24.05.2016.
 */
data class Price(var usd: String?,
                   var eur: String?,
                   var cny: String?,
                   var rub: String?) {
    constructor() : this(null,null,null,null)
}