package com.peterica.kotlinelasticsearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinElasticsearchApplication

fun main(args: Array<String>) {
    runApplication<KotlinElasticsearchApplication>(*args)
}
