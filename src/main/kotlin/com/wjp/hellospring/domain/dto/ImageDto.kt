package com.wjp.hellospring.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.lang.invoke.MethodHandles


data class ImageDto(
    val id: Long,
    val tagId: Long,
    val tagName: String,
    val categoryId: Long,
    val categoryName: String,
    val originUrl: String?,
    val currentUrl: String?,

){
    @JsonProperty("type")
    fun type() = "image"
}