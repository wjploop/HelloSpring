package com.wjp.hellospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
class HelloSpringApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringApplication>(*args)
}
