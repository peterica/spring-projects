package com.peterica.core.client

import com.peterica.core.client.response.ExampleResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "example", url = "https://catfact.ninja")
interface ExampleApi {
    @GetMapping("/fact")
    fun getFact(): ExampleResponse
}
