package io.usoamic.usoamickotlinjs.other

import io.usoamic.web3kt.bignumber.BigNumber
import kotlin.js.Date

class Timestamp(number: BigNumber) {
    private val _number = BigNumber(number)

    fun toSeconds(): BigNumber {
        return _number
    }

    fun toMilliseconds(): BigNumber {
        return _number.multipliedBy(BigNumber(1000))
    }

    fun toDate(): Date {
        return Date(toMilliseconds().toString().toLong())
    }

    fun toLocaleString(): String {
        return toDate().toLocaleString()
    }
}