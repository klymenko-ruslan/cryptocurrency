package com.outsourcebooster.cryptocurrency.common.model

/**
 * Created by rklimemnko on 24.05.2016.
 */
data class Currency(var price: Price?) {
    constructor() : this(null)
}
