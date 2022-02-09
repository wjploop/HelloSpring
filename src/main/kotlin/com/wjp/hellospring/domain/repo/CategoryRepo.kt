package com.wjp.hellospring.domain.repo

import com.wjp.hellospring.domain.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepo:JpaRepository<Category,Long> {
}