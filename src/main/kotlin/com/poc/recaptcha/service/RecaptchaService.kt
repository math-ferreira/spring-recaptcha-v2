package com.poc.recaptcha.service

interface RecaptchaService {
    fun verifyRecaptcha(recaptchaResponse: String)
}