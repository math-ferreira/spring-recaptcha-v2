package com.poc.recaptcha.entities.exception

import java.lang.RuntimeException

class RecaptchaErrorException(
    val errors: List<String>
) : RuntimeException()