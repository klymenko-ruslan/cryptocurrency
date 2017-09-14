package com.outsourcebooster.cryptocurrency.web.dto.credit

import com.outsourcebooster.cryptocurrency.web.model.office.Address

open class LoanRequestDTO(val periodInDays: String,
                          val currency: String,
                          val sum: String,
                          val pledgeCurrency: String,
                          val loanReceivingType: String,
                          val officeId: String,
                          val creditCardNumber: String,
                          val address: Address)