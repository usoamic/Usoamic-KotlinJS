package io.usoamic.usoamicktjs.core.extension

import io.usoamic.usoamicktjs.core.TransactionExplorer
import io.usoamic.usoamicktjs.model.Transfer
import io.usoamic.web3kt.core.contract.model.CallOption
import kotlin.math.min

private fun TransactionExplorer.iterateTransactions(
    option: CallOption,
    list: MutableList<Transfer>,
    owner: String,
    id: Long,
    lastId: Long,
    callback: (list: MutableList<Transfer>, t: Throwable?, needLoad: Boolean) -> Unit
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
    callback: (list: MutableList<Transfer>, t: Throwable?, hasUpdate: Boolean) -> Unit
) {
    val option = CallOption(from = owner)

    getNumberOfTransactionsByAddress(owner).call(option)
        .then {
            val factLastId = min(maxTx, it.toLong())
            if (factLastId > 0) {
                if (loadedLastId == factLastId) {
                    callback(mutableListOf(), null, false)
                    return@then
                }
                iterateTransactions(option, mutableListOf(), owner, 0, factLastId, callback)
            } else {
                callback(mutableListOf(), null, true)
            }
        }
        .catch {
            callback(mutableListOf(), it, false)
        }
}