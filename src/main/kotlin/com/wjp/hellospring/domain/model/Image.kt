package com.wjp.hellospring.domain.model

import com.wjp.hellospring.domain.model.base.BaseModel
import javax.persistence.Entity

@Entity
class Image(
    val currentUrl: String?,
    val originUrl: String?,
) : BaseModel()