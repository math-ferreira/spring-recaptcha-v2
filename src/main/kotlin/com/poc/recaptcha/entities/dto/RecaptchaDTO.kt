package com.poc.recaptcha.entities.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RecaptchaDTO(

    @JsonProperty("success")
    val success: Boolean? = false,

    @JsonProperty("challenge_ts")
    val challengeTs: String? = null,

    @JsonProperty("hostname")
    val hostname: String? = null,

    @JsonProperty("error-codes")
    val errorCodes : List<String>? = emptyList()
)
