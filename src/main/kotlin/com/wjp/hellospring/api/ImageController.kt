package com.wjp.hellospring.api

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.service.ImageService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

@RestController
@RequestMapping("image")
class ImageController {

    @Resource
    lateinit var imageService: ImageService

    @RequestMapping("images")
    fun images(
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) tagId: Long?,
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
        @RequestParam(required = false, defaultValue = "") search: String
    ): Page<ImageDto> {
        return imageService.images(search, categoryId, tagId, PageRequest.of(page, size))
    }

    @RequestMapping("categories")
    fun categories() = imageService.categories()

    @RequestMapping("tags")
    fun tags(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "10") size: Int,
    ) = imageService.tags(PageRequest.of(page, size))

    @RequestMapping("tags_by_image_id")
    fun tagsByImageId(@RequestParam imageId: Long) = imageService.findTagsByImageId(imageId)

    @RequestMapping("add")
    fun add(
        category: String, tags: String, url: String
    ) = imageService.save(category, tags, url)

    @RequestMapping("remove")
    fun remove(
        category: String, tags: String, url: String
    ) = imageService.delete(category, tags, url)

}