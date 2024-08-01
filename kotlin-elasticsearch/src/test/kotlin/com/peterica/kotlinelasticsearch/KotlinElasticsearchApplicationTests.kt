package com.peterica.kotlinelasticsearch

import com.peterica.kotlinelasticsearch.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KotlinElasticsearchApplicationTests {

    @Autowired
    private lateinit var productRepository: ProductRepository
    @BeforeEach
    fun setUp() {
        productRepository.deleteAll()
    }

    @Test
    fun `test save and find product`() {
        val product = Product(id = "1", name = "Test Product", price = 10.0)
        productRepository.save(product)

        val foundProduct = productRepository.findById("1").orElse(null)
        assertNotNull(foundProduct)
        assertEquals("Test Product", foundProduct?.name)
        assertEquals(10.0, foundProduct?.price)
    }
    @Test
    fun `test find all products`() {
        val product1 = Product(id = "1", name = "Product 1", price = 10.0)
        val product2 = Product(id = "2", name = "Product 2", price = 20.0)
        productRepository.saveAll(listOf(product1, product2))

        val products = productRepository.findAll()
        assertEquals(2, products.count())
    }
}
