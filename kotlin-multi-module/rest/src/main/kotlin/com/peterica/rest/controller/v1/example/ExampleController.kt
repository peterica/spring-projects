package com.peterica.rest.controller.v1.example

import com.peterica.core.client.ExampleClient
import com.peterica.core.response.ApiResponse
import com.peterica.rest.controller.v1.example.response.CatFactResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/example")
class ExampleController(
    // (예시용) 보통 client를 service에 구현하지만 예시용
    private val exampleClient: ExampleClient
) {
    @GetMapping("/cat/fact")
    fun getCatFact(): ApiResponse<CatFactResponse> {
        return ApiResponse.success(CatFactResponse(exampleClient.getCatFacts()))
    }
}
