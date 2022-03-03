package com.wjp.hellospring.domain.dto

class ImageDtoByCategory (
    override val id: Long,
    val categoryId: Long = 0,
    val categoryName: String = "",
    override val originUrl: String?,
    override val currentUrl: String?,
):ImageDto(id, originUrl, currentUrl)