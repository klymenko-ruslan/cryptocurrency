package com.outsourcebooster.cryptocurrency.web.model.loan

import com.outsourcebooster.cryptocurrency.web.model.currency.CryptoCurrency
import com.outsourcebooster.cryptocurrency.web.model.currency.FiatCurrency
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
open class Loan(@Id var id: String? = null,
           var userId: String? = null,
           var periodInDays: Long = 0,
           var dateCreated: LocalDateTime? = null,
           var dateDue: LocalDateTime? = null,
           var currency: FiatCurrency? = null,
           var sum: String? = null,
           var returnSum: String? = null,
           var pledgeCurrency: CryptoCurrency? = null,
           var pledgeSum: String? = null,
           var pledgeAddress: String? = null,
           var status: LoanStatus? = null,
           var loanReceivingType: LoanReceivingType,
           var officeId: String? = null)