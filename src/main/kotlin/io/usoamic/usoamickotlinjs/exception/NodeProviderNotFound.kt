package io.usoamic.usoamickotlinjs.exception

import io.usoamic.usoamickotlinjs.enumcls.NetworkType
import io.usoamic.usoamickotlinjs.enumcls.NodeProvider

class NodeProviderNotFound(provider: NodeProvider, networkType: NetworkType) : Exception("Provider $provider of $networkType not found")