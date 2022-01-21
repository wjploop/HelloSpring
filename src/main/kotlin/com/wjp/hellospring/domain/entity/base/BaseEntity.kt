package com.wjp.hellospring.domain.entity.base

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity(
    @CreatedDate
    val createdDate: Long = 0,
    @LastModifiedDate
    val updatedAt: Long = 0
) {
    @Id
    val id: Long = 0;
}