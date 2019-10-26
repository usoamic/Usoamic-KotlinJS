package io.usoamic.usoamicktjs.exception

import io.usoamic.usoamicktjs.enumcls.NetworkType
import io.usoamic.usoamicktjs.enumcls.NodeProvider

class NodeProviderNotFound(provider: NodeProvider, networkType: NetworkType) : Exception("Provider $provider of $networkType not found")