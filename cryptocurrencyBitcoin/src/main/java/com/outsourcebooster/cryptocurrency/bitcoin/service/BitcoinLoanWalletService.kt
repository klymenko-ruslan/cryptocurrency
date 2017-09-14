package com.outsourcebooster.cryptocurrency.bitcoin.service

import org.bitcoinj.core.*
import org.bitcoinj.kits.WalletAppKit
import org.bitcoinj.params.MainNetParams
import org.springframework.stereotype.Service
import java.io.File
import org.springframework.beans.factory.annotation.Value

@Service
open class BitcoinLoanWalletService(@Value("\${bitcoin.wallets.location}") val walletsLocation: String) {

    fun getPledgeAddress(loanId: String): String {
        val walletKit = loadWalletKit(loanId)
        if(walletKit.wallet().importedKeys.isEmpty()) {
            walletKit.wallet().importKey(ECKey())
        }
        val address = getAddressForKey(walletKit.wallet().importedKeys[0])
        walletKit.stopAsync()
        walletKit.awaitTerminated()
        return address
    }

    fun getBalance(loanId: String) = loadWalletKit(loanId).wallet().balance.toString()

    private fun loadWalletKit(loanId: String): WalletAppKit {
        val walletKit = WalletAppKit(MainNetParams.get(), File(walletsLocation), "loan-"+loanId)
        walletKit.startAsync()
        walletKit.awaitRunning()
        return walletKit
    }

    private fun getAddressForKey(key: ECKey): String {
        return key.toAddress(MainNetParams.get()).toBase58()
    }
}