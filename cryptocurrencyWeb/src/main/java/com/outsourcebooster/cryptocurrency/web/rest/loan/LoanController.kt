package com.outsourcebooster.cryptocurrency.web.rest.loan

import com.outsourcebooster.cryptocurrency.web.dto.credit.LoanRequestDTO
import com.outsourcebooster.cryptocurrency.web.service.loan.LoanService
import org.springframework.web.bind.annotation.*

@RestController
class LoanController(private val loanService: LoanService) {

    @RequestMapping(value = "/loan/initFiatCashLoan", method = arrayOf(RequestMethod.POST))
    fun initLoan(@RequestBody loanRequestDTO: LoanRequestDTO) = loanService.initLoan(loanRequestDTO)

    @RequestMapping(value = "loan/approve/{loanId}")
    fun approveLoan(@PathVariable("loanId") loanId: String) = loanService.approveLoan(loanId)

    @RequestMapping(value = "loan/disapprove/{loanId}")
    fun disapproveLoan(@RequestParam loanId: String) = loanService.disapproveLoan(loanId)
}