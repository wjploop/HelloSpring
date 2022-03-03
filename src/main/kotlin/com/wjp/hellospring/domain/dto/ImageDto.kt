package com.wjp.hellospring.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.lang.invoke.MethodHandles

open class ImageDto(
    open val id: Long,
    open val originUrl: String?,
    open val currentUrl: String?,
){
    @JsonProperty("type")
    fun type() = "image"
}

