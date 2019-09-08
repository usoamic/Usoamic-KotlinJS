package io.usoamic.usoamickotlinjs.core.extension

import io.usoamic.usoamickotlinjs.core.TransactionExplorer
import io.usoamic.usoamickotlinjs.model.Transaction
import io.usoamic.web3kt.core.contract.model.CallOption
import kotlin.math.min

private fun TransactionExplorer.iterateTransactions(
    option: CallOption,
    list: MutableList<Transaction>,
    owner: String,
    id: Long,
    lastId: Long,
    callback: (list: MutableList<Transaction>, t: Throwable?, needLoad: Boolean) -> Unit
) {
    getTransactionByAddress(owner, id.toString()).call(option)
        .then { tx ->
            list.add(tx)
            val nextId = (id + 1)
            if (nextId < lastId) {
                iterateTransactions(option, list, owner, nextId, lastId, callback)
            } else {
                callback(list, null, true)
            }
        }
        .catch {
            callback(mutableListOf(), it, false)
        }
}

fun TransactionExplorer.getTransactionsByAddress(
    owner: String,
    maxTx: Long,
    loadedLastId: Long,
    callback: (list: MutableList<Transaction>, t: Throwable?, hasUpdate: Boolean) -> Unit
) {
    val option = CallOption(from = owner)

    getNumberOfTransactionsByAddress(owner).call(option)
        .then {
            val factLastId = min(maxTx, it.toLong())
            if(loadedLastId == factLastId) {
                callback(mutableListOf(), null, false)
                return@then
            }
            if (factLastId > 0) {
                iterateTransactions(option, mutableListOf(), owner, 0, factLastId, callback)
            } else {
                callback(mutableListOf(), null, true)
            }
        }
        .catch {
            callback(mutableListOf(), it, false)
        }
}