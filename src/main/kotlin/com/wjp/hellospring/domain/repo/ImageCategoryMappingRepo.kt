package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.ImageCategoryMapping
import org.springframework.data.jpa.repository.JpaRepository

interface ImageCategoryMappingRepo : JpaRepository<ImageCategoryMapping, Long> {



}