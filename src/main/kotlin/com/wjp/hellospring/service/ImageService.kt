package com.wjp.hellospring.service

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.domain.model.Category
import com.wjp.hellospring.domain.model.Tag
import com.wjp.hellospring.domain.repo.CategoryRepo
import com.wjp.hellospring.domain.repo.ImageRepo
import com.wjp.hellospring.domain.repo.TagRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
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

        return when {
            searchKey.isNotBlank() -> {
                when {
                    categoryId != null && tagId != null -> {
                        throw IllegalArgumentException("参数错误，分类、标签同时存在时，不必搜索关键字")
                    }
                    categoryId != null -> {
                        imageRepo.searchByTag(categoryId, searchKey, pageable)
                    }
                    tagId != null -> {
                        imageRepo.searchByTag(tagId, searchKey, pageable)
                    }
                    else -> {
                        imageRepo.searchByCategoryOrTag(searchKey, pageable)
                    }
                }
            }
            categoryId != null && tagId != null -> {
                imageRepo.findByCategoryIdAndTagId(categoryId, tagId, pageable)
            }
            categoryId != null -> {
                imageRepo.findByCategoryId(categoryId, pageable)
            }
            tagId != null -> {
                imageRepo.findByTagId(tagId, pageable)
            }
            else -> {
                return imageRepo.findAllImage(pageable)
            }
        }
    }

    fun categories(): MutableList<Category> = categoryRepo.findAll()

    fun tags(pageable: Pageable): Page<Tag> = tagRepo.findAll(pageable)

}