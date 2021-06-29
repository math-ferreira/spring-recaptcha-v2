package com.poc.recaptcha

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class SpringRecaptchaApplication

fun main(args: Array<String>) {
	runApplication<SpringRecaptchaApplication>(*args)
}

@Bean
fun getRestTemplate(): RestTemplate {
	return RestTemplate()
}

