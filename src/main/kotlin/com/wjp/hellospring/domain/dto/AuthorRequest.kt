package com.wjp.hellospring.domain.dto

import javax.validation.constraints.*

data class AuthorRequest(

    @field:Email
    @field:NotBlank
    @field:Size(min = 10, max = 30)
    val email: String,

    @field:NotBlank
    @field:NotNull
    @field:Size(min = 6, max = 20)
    val username: String,

    @field:NotBlank()
    @field:Size(min = 6, max = 20)
    val password: String,

    @field:Pattern(regexp = "female|male|unknown")
    val sex: String = "unknown",

    val age: Int = 0,

    )
