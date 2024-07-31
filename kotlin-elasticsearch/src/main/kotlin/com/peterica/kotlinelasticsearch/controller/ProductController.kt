package com.peterica.kotlinelasticsearch.controller

import com.peterica.kotlinelasticsearch.Product
import com.peterica.kotlinelasticsearch.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: String): Product? {
        return productService.findProductById(id)
    }

    @GetMapping("/search")
    fun searchProducts(@RequestParam name: String): List<Product> {
        return productService.searchProducts(name)
    }

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productService.saveProduct(product)
    }
}