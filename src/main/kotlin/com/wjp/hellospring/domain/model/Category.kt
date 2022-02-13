package com.wjp.hellospring.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.wjp.hellospring.domain.model.base.BaseModel
import java.lang.invoke.MethodHandles
import javax.persistence.Entity
import javax.persistence.Table

@Entity
data class Category(
    val name:String
) : BaseModel() {


    @JsonProperty("type")
    fun type() = MethodHandles.lookup().lookupClass().simpleName.toString().lowercase()
}