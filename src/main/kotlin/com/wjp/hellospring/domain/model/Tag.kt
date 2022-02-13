package com.wjp.hellospring.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.wjp.hellospring.domain.model.base.BaseModel
import java.lang.invoke.MethodHandles
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinColumns
import javax.persistence.Table

@Entity
@Table
data class Tag(
    val name: String
) : BaseModel() {

    @JsonProperty("type")
    fun type() = MethodHandles.lookup().lookupClass().simpleName.toString().lowercase()
}