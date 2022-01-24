package com.wjp.hellospring

import org.assertj.core.api.Assertions.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import java.util.Base64
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.hateoas.MediaTypes
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.FilterChainProxy
import org.springframework.test.web.servlet.MockMvc
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.context.WebApplicationContext


@SpringBootTest
class UrlLevelSecurityTest {

    @Autowired
    lateinit var context: WebApplicationContext


    lateinit var mvc: MockMvc


    @BeforeEach
    fun setup() {

        mvc = webAppContextSetup(context).build()
    }

    @Test
    fun test() {

        mvc.perform(
            get("/book/list").header(
                HttpHeaders.AUTHORIZATION,
                "Basic ${Base64.getEncoder().encodeToString("admin:123".toByteArray())}"
            )
        ).andReturn().response.let {
            println("result: " + it.contentAsString)
        }
    }


}