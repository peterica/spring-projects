package com.peterica.kotlinquerydsl.entity

import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>, ProductRepositoryCustom

interface ProductRepositoryCustom {
    fun findProductsByName(
        name: String,
        pageable: Pageable,
    ): Page<Product>
}
