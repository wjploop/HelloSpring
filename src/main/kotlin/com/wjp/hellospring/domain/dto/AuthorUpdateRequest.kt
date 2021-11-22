package com.wjp.hellospring.domain.dto

import javax.validation.constraints.*

data class AuthorUpdateRequest(
    @NotNull
    val id:Long,

    @field:Email
    @field:Size(min = 10, max = 30)
    val email: String?,

    @field:NotBlank
    @field:Size(min = 6, max = 20)
    val username: String?,

    val password: String?,

    @field:Pattern(regexp = "female|male|unknown")
    val sex: String? = "unknown",

    @field:Positive
    val age: Int? = 0,

    )
