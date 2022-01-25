package com.wjp.hellospring.service

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.domain.repo.ImageRepo
import com.wjp.hellospring.domain.repo.ImageTagMappingRepo
import com.wjp.hellospring.domain.repo.TagRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class ImageService {

    @Resource
    lateinit var imageRepo: ImageRepo

    @Resource
    lateinit var tagRepo: TagRepo

    @Resource
    lateinit var imageTagMappingRepo: ImageTagMappingRepo

    fun page(categoryId: Long?, tagId: Long?, pageable: Pageable): Page<ImageDto> {
        return if (categoryId != null && tagId != null) {
            imageRepo.findByCategoryIdAndTagId(categoryId, tagId, pageable)
        } else if (categoryId != null) {
            imageRepo.findByCategoryId(categoryId, pageable)
        } else if (tagId != null) {
            imageRepo.findByTagId(tagId, pageable)
        } else {
            return imageRepo.findAllImage(pageable)
        }
    }
}