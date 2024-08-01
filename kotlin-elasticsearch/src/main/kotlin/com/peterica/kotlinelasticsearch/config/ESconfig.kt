package com.peterica.kotlinelasticsearch.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories
import org.springframework.data.elasticsearch.support.HttpHeaders

@Configuration
@EnableReactiveElasticsearchRepositories
class ESconfig(
    @Value("\${spring.elasticsearch.rest.url}") val uri: String,
    @Value("\${spring.elasticsearch.rest.api_key}") val apiKey: String,
    @Value("\${spring.elasticsearch.rest.username}") val username: String,
    @Value("\${spring.elasticsearch.rest.password}") val password: String
): ElasticsearchConfiguration() {

    override fun clientConfiguration(): ClientConfiguration {
        val httpHeaders = HttpHeaders()
        httpHeaders.add("Authorization", "Bearer $apiKey")
        return ClientConfiguration.builder()
            .connectedTo(uri)
            .usingSsl()
            .withDefaultHeaders(httpHeaders)
            .withBasicAuth(username, password )
            .build()
    }
}