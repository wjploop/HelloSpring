package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepo:JpaRepository<Tag,Long> {
}