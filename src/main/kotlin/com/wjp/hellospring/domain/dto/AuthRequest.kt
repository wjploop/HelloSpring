package com.wjp.hellospring.domain.dto

import javax.validation.constraints.Email

data class AuthRequest(
    @Email
    val username:String,
    val password:String
)