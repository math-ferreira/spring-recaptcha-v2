package com.poc.recaptcha

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class Config {

    @Bean
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}