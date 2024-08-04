package com.peterica.kotlinquerydsl.controller

import com.peterica.kotlinquerydsl.entity.Product
import com.peterica.kotlinquerydsl.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productService.saveProduct(product)
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): Product? {
        return productService.findProductById(id)
    }

    @GetMapping("/search")
    fun searchProductsByName(@RequestParam name: String, pageable: Pageable): Page<Product> {
        return productService.searchProductsByName(name, pageable)
    }
}