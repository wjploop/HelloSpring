package com.wjp.hellospring.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.wjp.hellospring.domain.model.base.BaseModel
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import javax.persistence.Entity

@Entity
class Image(
    val currentUrl: String?,
    val originUrl: String?,
) : BaseModel(){

    @JsonProperty("type")
    fun type() = MethodHandles.lookup().lookupClass().simpleName.toString().lowercase()
}