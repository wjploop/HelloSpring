package com.wjp.hellospring.domain.model.base

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseModel: AbstractPersistable<Long>() {
//    @Id
//    @GeneratedValue
//    val id: Long = 0
}