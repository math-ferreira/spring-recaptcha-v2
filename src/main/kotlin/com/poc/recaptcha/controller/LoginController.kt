package com.poc.recaptcha.controller

import com.poc.recaptcha.entities.dto.LoginDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest


@Controller
class LoginController {

    @GetMapping(value=["/login"])
    fun getLoginForm(): String {
        return "login";
    }

    @RequestMapping(value=["/login"])
   fun login(
        @ModelAttribute(name = "loginForm") loginDTO: LoginDTO,
        model: Model,
        httpServletRequest: HttpServletRequest
    ): String? {
        val recaptchaResponse = httpServletRequest.getParameter("g-recaptcha-response")


        if ("admin".equals(username) && "admin".equals(password)) {
            return "home";
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

}