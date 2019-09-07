package io.usoamic.usoamickotlinjs.core.extension

import io.usoamic.usoamickotlinjs.core.TransactionExplorer
import io.usoamic.usoamickotlinjs.model.Transaction
import io.usoamic.web3kt.core.contract.model.CallOption
import kotlin.math.min

private fun TransactionExplorer.iterateTransactions(option: CallOption, list: MutableList<Transaction>, owner: String, id: Long, lastId: Long, callback: (list: MutableList<Transaction>) -> Unit) {
    getTransactionByAddress(owner, id.toString()).call(option).then { tx ->
        list.add(tx)
        val nextId = (id + 1)
        if(nextId < lastId) {
            iterateTransactions(option, list, owner, nextId, lastId, callback)
        }
        else {
            callback(list)
        }
    }
}

fun TransactionExplorer.getTransactionsByAddress(owner: String, lastId: Long?, callback: (list: MutableList<Transaction>) -> Unit) {
    val option = CallOption(from = owner)

    getNumberOfTransactionsByAddress(owner).call(option).then {
        val numberOfTx = it.toLong()
        val factLastId = if(lastId == null) numberOfTx else min((lastId + 1), numberOfTx)
        if(factLastId > 0.toLong()) {
            iterateTransactions(option, mutableListOf(), owner, 0, factLastId, callback)
        }
        else {
            callback(mutableListOf())
        }
    }
}