package io.usoamic.usoamicktjs.core

import io.usoamic.usoamicktjs.model.Transfer
import io.usoamic.web3kt.core.contract.response.CallResponse

interface TransactionExplorer : Purchases {
    @JsName("getTransaction")
    fun getTransaction(txId: String): CallResponse<Transfer>

    @JsName("getTransactionByAddress")
    fun getTransactionByAddress(owner: String, txId: String): CallResponse<Transfer>

    @JsName("getNumberOfTransactions")
    fun getNumberOfTransactions(): CallResponse<String>

    @JsName("getNumberOfTransactionsByAddress")
    fun getNumberOfTransactionsByAddress(owner: String): CallResponse<String>
}