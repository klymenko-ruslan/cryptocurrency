package com.outsourcebooster.cryptocurrency.web.repository.credit

import com.outsourcebooster.cryptocurrency.web.model.loan.FiatCashLoan
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : MongoRepository<FiatCashLoan, String> {
    fun findByUserId(userId: String) : List<FiatCashLoan>
}