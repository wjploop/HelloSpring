package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.domain.model.Image
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImageRepo : JpaRepository<Image, Long> {

    @Query("select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i, Tag t, Category c, ImageTagMapping m  where " +
            "m.tagId = ?1 and m.tagId = t.id")
    fun findByTagId(tagId: Long, pageable: Pageable): Page<ImageDto>

    @Query("select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i, Tag t, Category c, ImageCategoryMapping m where" +
            " m.categoryId = ?1 and m.categoryId = m.categoryId")
    fun findByCategoryId(categoryId: Long, pageable: Pageable): Page<ImageDto>

    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i, Tag t, Category c, ImageCategoryMapping cm, ImageTagMapping tm" +
                " where cm.categoryId = ?1 and tm.tagId = ?2 and cm.imageId = i.id and tm.tagId = t.id"
    )
    fun findByCategoryIdAndTagId(categoryId: Long, tagId: Long, pageable: Pageable): Page<ImageDto>

    @Query("select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i, Tag t, Category c ")
    fun findAllImage(pageable: Pageable): Page<ImageDto>


}