package com.wjp.hellospring.domain.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class AuthorRequest(

    @field:Email
    @field:NotBlank
    @field:Size(min = 10, max = 30,)
    val email: String,

    @field:NotBlank
    @field:NotNull
    @field:Size(min = 6, max = 20)
    val username: String,

    @field:NotBlank()
    @field:Size(min = 6, max = 20)
    val password: String,

    )
