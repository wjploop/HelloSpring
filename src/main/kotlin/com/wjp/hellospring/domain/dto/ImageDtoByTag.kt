package com.wjp.hellospring.domain.dto

class ImageDtoByTag (
    override val id: Long,
    val tagId: Long = 0,
    val tagName: String = "",
    override val originUrl: String?,
    override val currentUrl: String?,
):ImageDto(id, originUrl, currentUrl)