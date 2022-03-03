package com.wjp.hellospring.domain.dto

class ImageDtoByTagAndCategory(
    override val id: Long,
    val tagId: Long = 0,
    val tagName: String = "",
    val categoryId: Long = 0,
    val categoryName: String = "",
    override val originUrl: String?,
    override val currentUrl: String?,
    ):ImageDto(id, originUrl, currentUrl)