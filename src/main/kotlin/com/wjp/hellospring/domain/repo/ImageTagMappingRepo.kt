package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.ImageTagMapping
import org.springframework.data.jpa.repository.JpaRepository

interface ImageTagMappingRepo : JpaRepository<ImageTagMapping, Long> {
}