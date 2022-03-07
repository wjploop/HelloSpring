package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.ImageCategoryMapping
import org.springframework.data.jpa.repository.JpaRepository

interface ImageCategoryMappingRepo : JpaRepository<ImageCategoryMapping, Long> {

    fun findByImageIdAndCategoryId(imageId:Long,categoryId:Long):ImageCategoryMapping?

    fun deleteAllByImageId(imageId: Long)

    fun countByCategoryId(categoryId: Long):Long

}