package com.poc.recaptcha.service.impl

import com.poc.recaptcha.entities.dto.RecaptchaDTO
import com.poc.recaptcha.entities.exception.RecaptchaErrorException
import com.poc.recaptcha.service.RecaptchaService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class RecaptchaServiceImpl(
    private val restTemplate: RestTemplate,
    @Value("\${google.recaptcha.secret}") private val secret: String,
    @Value("\${google.recaptcha.verification.endpoint}") private val url: String
): RecaptchaService {

    override fun verifyRecaptcha(recaptchaResponse: String) {
        requestRestTemplate(recaptchaResponse)?.let {
            if (!it.success!!) throw RecaptchaErrorException(errors = it.errorCodes!!)
        }
    }

    private fun requestRestTemplate(recaptchaResponse: String): RecaptchaDTO? {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val map = LinkedMultiValueMap<String, String>()
        map.add("secret", this.secret)
        map.add("response", recaptchaResponse)

        val request = HttpEntity<MultiValueMap<String, String>>(map, httpHeaders)
        val response = this.restTemplate.postForEntity(url, request, RecaptchaDTO::class.java)
        return response.body
    }

}