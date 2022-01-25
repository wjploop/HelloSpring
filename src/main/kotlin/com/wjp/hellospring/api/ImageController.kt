package com.wjp.hellospring.api

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.service.ImageService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("image")
class ImageController {

    @Resource
    lateinit var imageService: ImageService

    @RequestMapping("list")
    fun list(
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) tagId: Long?,
        @RequestParam(required = false, defaultValue = "1") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
//        @RequestParam(required = false)
    ): Page<ImageDto> {
        return imageService.page(categoryId, tagId, PageRequest.of(page, size))


    }
}