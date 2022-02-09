package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Tag
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface TagRepo:JpaRepository<Tag,Long> {
}