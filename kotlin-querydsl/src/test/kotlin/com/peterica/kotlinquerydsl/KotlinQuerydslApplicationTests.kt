package com.peterica.kotlinquerydsl

import com.peterica.kotlinquerydsl.entity.Product
import com.peterica.kotlinquerydsl.entity.ProductRepository
import com.peterica.kotlinquerydsl.entity.QProduct.product
import com.querydsl.jpa.impl.JPAQueryFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class KotlinQuerydslApplicationTests {

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var jpaQueryFactory: JPAQueryFactory

    @Test
    fun contextLoads() {
    }


    @DisplayName("1개월 내로 등록된 Product 중 수량이 적은 5개 찾기")
    @Test
    fun querydslTest() {
        // given
        this.setUpTestData()

        // when
        val products = jpaQueryFactory
            .selectFrom(product)
            .where(product.registeredAt.after(LocalDateTime.now().minusMonths(1)))
            .orderBy(product.quantity.asc())
            .limit(5)
            .fetch()

        products.forEach { println(it) }

        // then
        assertThat(products).hasSize(5)
        assertThat(products[0].name).isEqualTo("Ginger")
        assertThat(products[1].name).isEqualTo("Fish and Chips")
        assertThat(products[2].name).isEqualTo("Egg")
        assertThat(products[3].name).isEqualTo("Dragon Fruit")
        assertThat(products[4].name).isEqualTo("Cap")
    }

    private fun setUpTestData() {
        val products = listOf(
            Product("Apple", 1, LocalDateTime.now()),
            Product("Banana", 3, LocalDateTime.now().minusDays(1)),
            Product("Cap", 4, LocalDateTime.now().minusDays(15)),
            Product("Dragon Fruit", 5, LocalDateTime.now().minusDays(20)),
            Product("Egg", 7, LocalDateTime.now().minusDays(24)),
            Product("Fish and Chips", 8, LocalDateTime.now().minusDays(26)),
            Product("Ginger", 3, LocalDateTime.now().minusDays(28)),
            Product("Ham", 5, LocalDateTime.now().minusDays(33)),
            Product("Ice", 6, LocalDateTime.now().minusDays(46)),
        )
        productRepository.saveAll(products)
    }
}
