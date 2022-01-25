package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.model.base.BaseModel
import javax.persistence.Entity

@Entity
data class Category(
    val name:String
) : BaseModel() {
}