package com.wjp.hellospring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
