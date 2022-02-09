package com.wjp.hellospring.service

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.domain.model.Category
import com.wjp.hellospring.domain.model.Tag
import com.wjp.hellospring.domain.repo.CategoryRepo
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
    lateinit var categoryRepo: CategoryRepo

    @Resource
    lateinit var tagRepo: TagRepo

    fun images(searchKey: String, categoryId: Long?, tagId: Long?, pageable: Pageable): Page<ImageDto> {

        return if (searchKey.isNotBlank()) {
            imageRepo.search(searchKey, pageable)
        } else if (categoryId != null && tagId != null) {
            imageRepo.findByCategoryIdAndTagId(categoryId, tagId, pageable)
        } else if (categoryId != null) {
            imageRepo.findByCategoryId(categoryId, pageable)
        } else if (tagId != null) {
            imageRepo.findByTagId(tagId, pageable)
        } else {
            return imageRepo.findAllImage(pageable)
        }
    }

    fun categories(): MutableList<Category> = categoryRepo.findAll()

    fun tags(pageable: Pageable): Page<Tag> = tagRepo.findAll(pageable)

}