package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.model.base.BaseModel
import javax.persistence.Entity

@Entity
class ImageTagMapping(
    val imageId: Long,
    val tagId: Long
) : BaseModel() {
}