package com.poc.recaptcha.controller

import com.poc.recaptcha.entities.dto.LoginDTO
import com.poc.recaptcha.service.RecaptchaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest


@Controller
class LoginController(
    private val recaptchaService: RecaptchaService
) {

    @GetMapping(value = ["/login"])
    fun getLoginForm(): String {
        return "login";
    }

    @PostMapping(value = ["/login"])
    fun login(
        @ModelAttribute(name = "loginForm") loginDTO: LoginDTO,
        model: Model,
        httpServletRequest: HttpServletRequest
    ): String? {
        val recaptchaResponse = httpServletRequest.getParameter("g-recaptcha-response")
        this.recaptchaService.verifyRecaptcha(recaptchaResponse)
        return verifyCredentials(loginDTO, model)

    }

    fun verifyCredentials(loginDTO: LoginDTO, model: Model): String {
        val username: String = loginDTO.username
        val password: String = loginDTO.password

        return when {
            "admin" == username && "admin" == password -> "home";
            else -> {
                model.addAttribute("invalidCredentials", true);
                "login";
            }
        }
    }

}