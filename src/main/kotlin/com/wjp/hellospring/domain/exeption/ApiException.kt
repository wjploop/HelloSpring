package com.wjp.hellospring.domain.exeption

import com.wjp.hellospring.domain.entity.ResultCode
import java.lang.RuntimeException

/**
 * 业务的异常
 */
data class ApiException(
    val code: ResultCode,
) : RuntimeException(
    code.msg
)