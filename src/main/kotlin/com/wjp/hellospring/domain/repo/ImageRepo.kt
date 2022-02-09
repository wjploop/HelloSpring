package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.dto.ImageDto
import com.wjp.hellospring.domain.model.Image
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import javax.transaction.Transactional

@Transactional
interface ImageRepo : JpaRepository<Image, Long> {

    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId " +
                " where tm.tagId = ?1"
    )
    fun findByTagId(tagId: Long, pageable: Pageable): Page<ImageDto>

    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId " +
                " where cm.categoryId = ?1"
    )
    fun findByCategoryId(categoryId: Long, pageable: Pageable): Page<ImageDto>

    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId " +
                " where cm.categoryId = ?1 and tm.tagId = ?2"
    )
    fun findByCategoryIdAndTagId(categoryId: Long, tagId: Long, pageable: Pageable): Page<ImageDto>

    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId "
    )

    fun findAllImage(pageable: Pageable): Page<ImageDto>

    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId " +
                " where t.name like ?1 or c.name like ?1"
    )
    fun searchByCategoryOrTag(searchKey: String, pageable: Pageable): Page<ImageDto>


    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId " +
                " where tm.tagId = ?1 and t.name like ?2"
    )
    fun searchByCategory(tagId: Long, searchKey: String, pageable: Pageable): Page<ImageDto>


    @Query(
        "select new com.wjp.hellospring.domain.dto.ImageDto(i.id, t.id, t.name, c.id, c.name, i.originUrl, i.currentUrl) from Image i " +
                " join ImageCategoryMapping cm on cm.imageId = i.id " +
                " join ImageTagMapping tm on tm.imageId = i.id " +
                " join Category c on c.id = cm.categoryId " +
                " join Tag t on t.id = tm.tagId " +
                " where cm.categoryId = ?1 and t.name like ?2"
    )
    fun searchByTag(categoryId: Long, searchKey: String, pageable: Pageable): Page<ImageDto>


}