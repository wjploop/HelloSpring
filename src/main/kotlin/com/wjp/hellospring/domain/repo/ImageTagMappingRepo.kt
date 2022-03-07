package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.ImageTagMapping
import org.springframework.data.jpa.repository.JpaRepository

interface ImageTagMappingRepo : JpaRepository<ImageTagMapping, Long> {

    fun findByImageIdAndTagId(imageId: Long, tagId: Long):ImageTagMapping?

    fun deleteAllByImageId(imageId: Long)

    fun countByTagId(tagId: Long):Long

}