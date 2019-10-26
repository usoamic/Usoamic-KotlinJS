package io.usoamic.usoamickotlinjs.other

import io.usoamic.usoamickotlinjs.enumcls.NetworkType

object Contract {
    fun forNetwork(networkType: NetworkType): String {
        return when(networkType) {
            NetworkType.MAINNET -> "0x065E2FF284d89A16c84D6EEb3d825e9fA621A2b8"
            NetworkType.TESTNET -> "0x1f559bB42f678D065fdeB71Ca3DE9d222C8aC756"
        }
    }
}