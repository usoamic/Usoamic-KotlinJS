package io.usoamic.usoamicktjs.other

import io.usoamic.usoamicktjs.enumcls.NetworkType
import io.usoamic.usoamicktjs.exception.NodeProviderNotFound
import io.usoamic.usoamicktjs.enumcls.NodeProvider

object Node {
    private const val PROJECT_ID = "fa8e5195184149f79e644292d2b31707"
    fun by(networkType: NetworkType, nodeProvider: NodeProvider): String {
        return when(nodeProvider) {
            NodeProvider.INFURA -> when(networkType) {
                NetworkType.MAINNET -> "https://mainnet.infura.io:443/v3/${PROJECT_ID}"
                NetworkType.TESTNET -> "https://rinkeby.infura.io:443/v3/${PROJECT_ID}"
            }
            NodeProvider.MYETHERWALLET -> when(networkType) {
                NetworkType.MAINNET -> "https://api.myetherwallet.com:443/eth"
                NetworkType.TESTNET -> throw NodeProviderNotFound(nodeProvider, networkType)
            }
            NodeProvider.ETHERSCAN -> when(networkType) {
                NetworkType.MAINNET -> "https://etherscan.io:443"
                NetworkType.TESTNET -> "https://rinkeby.etherscan.io:443/api"
            }
        }
    }
}