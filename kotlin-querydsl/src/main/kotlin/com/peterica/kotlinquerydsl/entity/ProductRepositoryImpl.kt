package com.peterica.kotlinquerydsl.entity

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.*
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): ProductRepositoryCustom {

    override fun findProductsByName(name: String, pageable: Pageable): Page<Product> {
        val qProduct = QProduct.product

        val query = jpaQueryFactory.selectFrom(qProduct)
            .where(qProduct.name.containsIgnoreCase(name))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        val products = query.fetch()
        val countQuery = jpaQueryFactory.selectFrom(qProduct)
            .where(qProduct.name.containsIgnoreCase(name))

        return PageImpl(products, pageable, countQuery.fetchCount())
    }
}
