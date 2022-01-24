package com.wjp.hellospring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.FilterChainProxy
import org.springframework.web.filter.CommonsRequestLoggingFilter
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig:WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home").setViewName("home")
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/hello").setViewName("hello")
        registry.addViewController("/login").setViewName("login")
    }

    @Bean
    fun requestLoggingFilter():CommonsRequestLoggingFilter{
        return CommonsRequestLoggingFilter().apply {
            setIncludeClientInfo(true)
            setIncludeQueryString(true)
            setIncludePayload(true)
            setIncludeHeaders(true)
            setMaxPayloadLength(64000)
        }
    }

}