package io.usoamic.usoamickotlinjs.model

import io.usoamic.usoamickotlinjs.other.Timestamp
import io.usoamic.web3kt.bignumber.BigNumber
import io.usoamic.web3kt.core.contract.util.Coin

data class Transaction constructor(
    val isExist: Boolean,
    val txId: BigNumber,
    val from: String,
    val to: String,
    val value: BigNumber,
    val timestamp: Timestamp
) {
    class Builder {
        private var isExist: Boolean = false
        private lateinit var txId: BigNumber
        private lateinit var from: String
        private lateinit var to: String
        private lateinit var value: BigNumber
        private lateinit var timestamp: Timestamp

        fun setExist(isExist: Boolean) = apply {
            this.isExist = isExist
        }

        fun setTxId(txId: BigNumber) = apply {
            this.txId = txId
        }

        fun setFrom(from: String) = apply {
            this.from = from
        }

        fun setTo(to: String) = apply {
            this.to = to
        }

        fun setValue(value: BigNumber) = apply {
            this.value = value
        }

        fun setTimestamp(timestamp: BigNumber) = apply {
            this.timestamp = Timestamp(timestamp)
        }

        fun build() = Transaction(
            isExist,
            txId,
            from,
            to,
            Coin.fromSat(value).toBigNumber(),
            timestamp
        )
    }
}