package com.wjp.hellospring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.security.crypto.codec.Utf8
import java.nio.charset.StandardCharsets

@Configuration
class LangConfig {


    @Bean
    fun messageSource()  = ResourceBundleMessageSource ().apply {
        setBasename("message")
        setUseCodeAsDefaultMessage(true);
        setDefaultEncoding(StandardCharsets.UTF_8.name())

    }

}