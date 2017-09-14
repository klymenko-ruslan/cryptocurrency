package com.outsourcebooster.cryptocurrency.bitcoin.controller

import com.outsourcebooster.cryptocurrency.bitcoin.service.BitcoinLoanWalletService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/bitcoin-loan")
open class BitcoinLoanWalletController(val bitcoinLoanWalletService: BitcoinLoanWalletService) {
    @RequestMapping("get-pledge-address/{loanId}")
    fun getPledgeAddress(@PathVariable loanId: String) = bitcoinLoanWalletService.getPledgeAddress(loanId)

    @RequestMapping("get-loan-balance/{loanId}")
    fun getBalance(@PathVariable loanId: String) = bitcoinLoanWalletService.getBalance(loanId)
}