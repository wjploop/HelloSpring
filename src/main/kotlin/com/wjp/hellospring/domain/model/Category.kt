package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.model.base.BaseModel
import javax.persistence.Entity
import javax.persistence.Table

@Entity
data class Category(
    val name:String
) : BaseModel() {
}