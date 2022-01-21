package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.entity.base.BaseEntity
import javax.persistence.*

@Entity
data class Book(
    val name: String,
    @OneToOne
    val author: Author
) : BaseEntity()