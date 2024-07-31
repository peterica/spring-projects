package com.peterica.kotlinelasticsearch.service

import com.peterica.kotlinelasticsearch.Product
import com.peterica.kotlinelasticsearch.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun saveProduct(product: Product): Product =
        productRepository.save(product)

    fun findProductById(id: String): Product? {
        return productRepository.findById(id).orElse(null)
    }

    fun searchProducts(name: String): List<Product> {
        return productRepository.findByNameContaining(name)
    }
}