package com.wjp.hellospring.domain.entity

data class ResultVO<T>(
    val code: ResultCode,
    val msg: String?,
    val data: T?,
) {
    constructor(data: T) : this(ResultCode.sucess, null, data)

    constructor(code: ResultCode, msg: String?) : this(code, msg, null)
}
