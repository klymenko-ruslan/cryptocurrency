package com.outsourcebooster.cryptocurrency.web.service.loan

import com.outsourcebooster.cryptocurrency.common.model.Value
import com.outsourcebooster.cryptocurrency.common.util.ApplicationUtils
import com.outsourcebooster.cryptocurrency.web.dto.credit.LoanRequestDTO
import com.outsourcebooster.cryptocurrency.web.model.loan.LoanStatus
import com.outsourcebooster.cryptocurrency.web.model.currency.CryptoCurrency
import com.outsourcebooster.cryptocurrency.web.model.currency.FiatCurrency
import com.outsourcebooster.cryptocurrency.web.model.loan.Loan
import com.outsourcebooster.cryptocurrency.web.model.loan.LoanReceivingType
import com.outsourcebooster.cryptocurrency.web.repository.credit.LoanRepository
import com.outsourcebooster.cryptocurrency.web.repository.user.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

@Service
open class LoanService(private val loanRepository: LoanRepository,
                       private val userRepository: UserRepository) {

    fun initLoan(loanRequestDTO: LoanRequestDTO): Loan {
        val userEmail = SecurityContextHolder.getContext().getAuthentication().name
        val loggedUser = userRepository.findByEmail(userEmail)
        val loan = Loan(officeId = loanRequestDTO.officeId,
                        userId =  loggedUser.id,
                        periodInDays = loanRequestDTO.periodInDays.toLong(),
                        currency = FiatCurrency.valueOf(loanRequestDTO.currency),
                        sum = loanRequestDTO.sum,
                        pledgeCurrency = CryptoCurrency.valueOf(loanRequestDTO.pledgeCurrency),
                        status = LoanStatus.waiting_for_user_agreement,
                        dateCreated = LocalDateTime.now(),
                        dateDue = LocalDateTime.now().plusDays(loanRequestDTO.periodInDays.toLong()),
                        pledgeSum = calculatePledgeSum(CryptoCurrency.valueOf(loanRequestDTO.pledgeCurrency), FiatCurrency.valueOf(loanRequestDTO.currency), loanRequestDTO.sum),
                        returnSum = calculateReturnSum(loanRequestDTO.periodInDays.toLong(), loanRequestDTO.sum),
                        loanReceivingType = LoanReceivingType.cash)
        loan.officeId = loanRequestDTO.officeId
        loan.userId = loggedUser.id
        loan.periodInDays = loanRequestDTO.periodInDays.toLong()
        loan.currency = FiatCurrency.valueOf(loanRequestDTO.currency)
        loan.sum = loanRequestDTO.sum
        loan.pledgeCurrency = CryptoCurrency.valueOf(loanRequestDTO.pledgeCurrency)
        loan.status = LoanStatus.waiting_for_user_agreement
        loan.dateCreated = LocalDateTime.now()
        loan.dateDue = LocalDateTime.now().plusDays(loanRequestDTO.periodInDays.toLong())
        loan.pledgeSum = calculatePledgeSum(loan)
        loan.returnSum = calculateReturnSum(loanRequestDTO.periodInDays.toLong(), loanRequestDTO.sum)

        return loanRepository.save(loan)
    }

    private fun calculatePledgeSum(pledgeCurrency: CryptoCurrency?, currency: FiatCurrency?, sum: String?): String {
        val lastValue = RestTemplate().getForObject(ApplicationUtils.getCommonProperty("cryptocurrency.core.external.exchange.api.url"), Value::class.java)
        val lastPriceOfPledgeCurrency = if (pledgeCurrency == CryptoCurrency.btc) lastValue.btc.price
        else lastValue.eth.price
        val lastPriceOfPledgePrice = when (currency) {
            FiatCurrency.usd -> lastPriceOfPledgeCurrency?.usd ?: throw RuntimeException()
            FiatCurrency.eur -> lastPriceOfPledgeCurrency?.eur ?: throw RuntimeException()
            FiatCurrency.cny -> lastPriceOfPledgeCurrency?.cny ?: throw RuntimeException()
            FiatCurrency.rub -> lastPriceOfPledgeCurrency?.rub ?: throw RuntimeException()
            else -> throw RuntimeException()
        }
        val originalPledge = BigDecimal(sum).divide(BigDecimal(lastPriceOfPledgePrice.toString()), 10, RoundingMode.UP)
        val pledgeCoefficient = BigDecimal("1.5")
        return originalPledge.multiply(pledgeCoefficient).toString()
    }

    private fun calculateReturnSum(periodInDays: Long, sum: String?): String {
        val percents = periodInDays * 0.3
        val givenSum = BigDecimal(sum)
        val additionalSum = givenSum.divide(BigDecimal(100), 10, RoundingMode.UP).multiply(BigDecimal(percents))
        return (givenSum + additionalSum).setScale(2, RoundingMode.UP).toString()
    }

    fun approveLoan(loanId: String): String {
        val loan = loanRepository.findOne(loanId)
        val pledgeAddress = RestTemplate().getForObject("http://localhost:8084/bitcoin-loan/get-pledge-address/" + loanId, String::class.java)
        loan.status = LoanStatus.ready_to_receive
        loan.pledgeAddress = pledgeAddress
        loanRepository.save(loan)
        return pledgeAddress
    }

    fun disapproveLoan(loanId: String) {
        val loan = loanRepository.findOne(loanId)
        loan.status = LoanStatus.removed
        loanRepository.save(loan)
    }
}