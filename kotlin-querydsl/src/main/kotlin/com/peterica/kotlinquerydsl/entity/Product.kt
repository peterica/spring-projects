package com.peterica.kotlinquerydsl.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "products")
data class Product(
    @Column(name = "name")
    val name: String,
    @Column(name = "quantity")
    var quantity: Long,
    @Column(name = "registeredAt")
    val registeredAt: LocalDateTime,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
)
