package com.peterica.core.client

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.peterica.core.client"])
@Configuration
internal class ExampleApiConfig
