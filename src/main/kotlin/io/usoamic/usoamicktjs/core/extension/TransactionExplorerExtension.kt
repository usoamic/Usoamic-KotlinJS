package io.usoamic.usoamicktjs.core.extension

import io.usoamic.usoamicktjs.core.TransactionExplorer
import io.usoamic.usoamicktjs.model.Transfer
import io.usoamic.web3kt.core.contract.model.CallOption
import kotlin.math.max

private fun TransactionExplorer.iterateTransactions(
    option: CallOption,
    list: MutableList<Transfer>,
    owner: String,
    id: Long,
    endId: Long,
    lastTxId: Long,
    callback: (list: MutableList<Transfer>, lastTxId: Long, t: Throwable?, needLoad: Boolean) -> Unit
) {
    getTransactionByAddress(owner, id.toString()).call(option)
        .then { tx ->
            list.add(tx)
            val nextId = (id - 1)
            if (nextId >= endId) {
                iterateTransactions(option, list, owner, nextId, endId, lastTxId, callback)
            } else {
                callback(list, lastTxId, null, true)
            }
        }
        .catch {
            callback(mutableListOf(), lastTxId, it, false)
        }
}

fun TransactionExplorer.getTransactionsByAddress(
    owner: String,
    maxTx: Long,
    loadedLastId: Long,
    callback: (list: MutableList<Transfer>, lastTxId: Long, t: Throwable?, needLoad: Boolean) -> Unit
) {
    val option = CallOption(from = owner)

    getNumberOfTransactionsByAddress(owner).call(option)
        .then {
            val lastId = it.toLong() - 1
            val endId = max(lastId - (maxTx - 1), 0)

            if (lastId > 0) {
                if (loadedLastId == lastId) {
                    callback(mutableListOf(), lastId, null, false)
                    return@then
                }
                iterateTransactions(option, mutableListOf(), owner, lastId, endId, lastId, callback)
            } else {
                callback(mutableListOf(), lastId, null, true)
            }
        }
        .catch {
            callback(mutableListOf(), 0, it, false)
        }
}