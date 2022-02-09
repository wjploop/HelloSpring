package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.model.base.BaseModel
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.Table

@Entity
@Table
data class Tag(
    val name: String
) : BaseModel() {
}