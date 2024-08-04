package com.peterica.kotlinquerydsl

import com.fasterxml.jackson.databind.ObjectMapper
import com.peterica.kotlinquerydsl.controller.ProductController
import com.peterica.kotlinquerydsl.entity.Product
import com.peterica.kotlinquerydsl.service.ProductService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@WebMvcTest(ProductController::class)
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var productService: ProductService
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `create product`() {
        val product = Product(name = "Test Product", quantity = 2, registeredAt = LocalDateTime.now())
        val savedProduct = product.copy(id = 1)

        `when`(productService.saveProduct(product)).thenReturn(savedProduct)

        mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(jsonPath("$.quantity").value(2))
    }

    @Test
    fun `get product by id`() {
        val product = Product(id = 1, name = "Test Product", quantity = 2, registeredAt = LocalDateTime.now())

        `when`(productService.findProductById(1)).thenReturn(product)

        mockMvc.perform(get("/products/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Test Product"))
            .andExpect(jsonPath("$.quantity").value(2))
    }

    @Test
    fun `return 404 when product not found`() {
        `when`(productService.findProductById(1)).thenReturn(null)

        mockMvc.perform(get("/products/1"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `search products`() {
        val products = listOf(
            Product(id = 1, name = "Test Product 1", quantity = 1, registeredAt = LocalDateTime.now()),
            Product(id = 2, name = "Test Product 2", quantity = 2, registeredAt = LocalDateTime.now())
        )
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(products, pageable, 2)

        `when`(productService.searchProductsByName("Test", pageable)).thenReturn(page)

        mockMvc.perform(get("/products/search")
            .param("name", "Test")
            .param("page", "0")
            .param("size", "10"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content.length()").value(2))
            .andExpect(jsonPath("$.content[0].id").value(1))
            .andExpect(jsonPath("$.content[0].name").value("Test Product 1"))
            .andExpect(jsonPath("$.content[1].id").value(2))
            .andExpect(jsonPath("$.content[1].name").value("Test Product 2"))
            .andExpect(jsonPath("$.totalElements").value(2))
            .andExpect(jsonPath("$.totalPages").value(1))
    }
}