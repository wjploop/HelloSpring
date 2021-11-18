package com.wjp.hellospring.domain.exeption

import java.lang.RuntimeException

data class NotFoundException(
    val msg: String? = null,
    val clz: Class<*>? = null,
    val id: Any
) : RuntimeException(
    msg ?: String.format("Entity %s with id %id not found", clz?.simpleName, id)
)
