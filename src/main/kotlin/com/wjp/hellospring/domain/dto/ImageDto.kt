package com.wjp.hellospring.domain.dto


data class ImageDto(
    val id: Long,
    val tagId: Long,
    val tagName: String,
    val categoryId: Long,
    val categoryName: String,
    val originUrl: String?,
    val currentUrl: String?,
)