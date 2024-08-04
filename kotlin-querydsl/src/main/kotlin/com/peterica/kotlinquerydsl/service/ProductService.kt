package com.peterica.kotlinquerydsl.service

import com.peterica.kotlinquerydsl.entity.Product
import com.peterica.kotlinquerydsl.entity.ProductRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun saveProduct(product: Product): Product = productRepository.save(product)

    fun findProductById(id: Long): Product? =
        productRepository.findByIdOrNull(id)?.apply {
            println(this)
        }

    fun searchProductsByName(
        name: String,
        pageable: Pageable,
    ): Page<Product> =
        productRepository.findProductsByName(name, pageable).apply {
            println(this)
        }
}
