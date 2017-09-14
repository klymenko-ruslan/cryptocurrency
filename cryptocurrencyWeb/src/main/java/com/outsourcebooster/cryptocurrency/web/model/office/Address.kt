package com.outsourcebooster.cryptocurrency.web.model.office

data class Address(val city : City,
                   val street: String,
                   val building: String,
                   val appartaments: String,
                   var postIndex: String? = null)