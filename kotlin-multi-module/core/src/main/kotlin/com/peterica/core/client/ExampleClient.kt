package com.peterica.core.client

import org.springframework.stereotype.Component

@Component
class ExampleClient(
    private val exampleApi: ExampleApi
) {
    fun getCatFacts(): String {
        return exampleApi.getFact().fact
    }
}
