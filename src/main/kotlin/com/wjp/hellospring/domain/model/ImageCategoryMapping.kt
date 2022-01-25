package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.model.base.BaseModel
import javax.persistence.Entity

@Entity
data class ImageCategoryMapping(
    val imageId: Long,
    val categoryId: Long,
) : BaseModel() {
}