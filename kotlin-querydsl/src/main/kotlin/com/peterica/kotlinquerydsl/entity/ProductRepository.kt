package com.peterica.kotlinquerydsl.entity

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
