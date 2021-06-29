package com.poc.recaptcha.controller.handler

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY
import com.poc.recaptcha.entities.exception.RecaptchaErrorException
import org.springframework.http.HttpStatus.UNAUTHORIZED
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [RecaptchaErrorException::class])
    @ResponseStatus(UNAUTHORIZED)
    fun handler(ex: RecaptchaErrorException, response: HttpServletRequest): ApiError {
    val code = "$UNAUTHORIZED - ${UNAUTHORIZED.value()}"
        return ApiError(
            code = code,
            message = ex.message,
            errors = ex.errors
        )
    }

}

data class ApiError(
    val code: String,
    val message: String?,
    val errors: List<String>,
    @JsonInclude(NON_EMPTY)
    val cause: String? = ""
)