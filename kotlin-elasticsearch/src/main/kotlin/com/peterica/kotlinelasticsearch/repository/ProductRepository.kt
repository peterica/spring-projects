package com.peterica.kotlinelasticsearch.repository

import com.peterica.kotlinelasticsearch.Product
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ProductRepository : ElasticsearchRepository<Product, String> {
    fun findByNameContaining(name: String): List<Product>
}