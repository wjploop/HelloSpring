package com.wjp.hellospring.service

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.domain.model.*
import com.wjp.hellospring.domain.repo.*
import com.wjp.hellospring.log.Logger
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import javax.annotation.Resource

@Service
class ImageService {

    @Resource
    lateinit var imageRepo: ImageRepo


    @Resource
    lateinit var categoryRepo: CategoryRepo

    @Resource
    lateinit var imageCategoryMappingRepo: ImageCategoryMappingRepo

    @Resource
    lateinit var imageTagMappingRepo: ImageTagMappingRepo

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

    fun findTagsByImageId(imageId: Long) = tagRepo.findByImageId(imageId)

    @Transactional
    fun save(categoryName: String, tagsName: String, url: String) {

        if (categoryName.isBlank() || tagsName.isBlank() || url.isBlank()) {
            Logger.warn(" fail to save $categoryName $tagsName $url, due some param is blank")
            return
        }

        val image =
            imageRepo.findByOriginUrl(url.trim()) ?: imageRepo.saveAndFlush(Image(originUrl = url, currentUrl = null))

        val category = categoryRepo.findByName(categoryName) ?: categoryRepo.saveAndFlush(Category(categoryName))

        imageCategoryMappingRepo.findByImageIdAndCategoryId(image.id, category.id)
            ?: imageCategoryMappingRepo.save(ImageCategoryMapping(image.id, category.id))


        tagsName.split(" ").map { it.trim() }.filter { it.isNotBlank() }.forEach {
            val tag = tagRepo.findByName(it) ?: tagRepo.saveAndFlush(Tag(it))
            imageTagMappingRepo.findByImageIdAndTagId(image.id, tag.id)
                ?: imageTagMappingRepo.save(ImageTagMapping(image.id, tag.id))
        }

        Logger.info("save $categoryName $tagsName $url")
    }

    @Transactional
    fun delete(categoryName: String, tagsName: String, url: String) {
        val image = imageRepo.findByOriginUrl(url)
        image?.id?.let {
            imageCategoryMappingRepo.deleteAllByImageId(it)
            imageTagMappingRepo.deleteAllByImageId(it)
            imageRepo.deleteById(it)
        }
        val category = categoryRepo.findByName(categoryName)
        category?.id?.let {
            if (imageCategoryMappingRepo.countByCategoryId(categoryId = it) == 0L) {
                categoryRepo.deleteById(category.id)
            }
        }
        tagsName.split(" ").map { it.trim() }.filter { it.isNotBlank() }.forEach { tagsName ->
            val tag = tagRepo.findByName(tagsName)
            tag?.id?.let {
                if (imageTagMappingRepo.countByTagId(tagId = it) == 0L) {
                    tagRepo.deleteById(tag.id)
                }
            }
        }
    }

}