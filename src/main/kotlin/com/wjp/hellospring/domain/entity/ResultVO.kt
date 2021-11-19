package com.wjp.hellospring.domain.entity

data class ResultVO<T>(
    val code: Int,
    val msg: String?,
    val data: T?,
) {
    constructor(data: T) : this(ResultCode.sucess.code, ResultCode.sucess.msg, data)

    constructor(code: ResultCode, msg: String?) : this(code.code, msg, null)

    constructor(code: Int, msg: String?) : this(code, msg, null)
}
