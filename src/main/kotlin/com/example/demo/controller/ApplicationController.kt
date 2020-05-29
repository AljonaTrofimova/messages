package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController("APPLICATION_CONTROLLER")
class ApplicationController {
    @Autowired
    lateinit var environment: Environment

    @GetMapping(value = ["/"])
    @ResponseBody
    private fun hello(): String? {
        return "Message service" +
                "<br><a href=\"http://localhost:" + environment.getProperty("local.server.port") + "/swagger-ui.html\">Swagger documentation</a>\n";
    }
}