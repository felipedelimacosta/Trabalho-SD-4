package com.trabalho.sd

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
internal open class AppConfiguration {
    @Bean
    open fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}