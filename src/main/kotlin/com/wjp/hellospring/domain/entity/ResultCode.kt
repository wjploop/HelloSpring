package com.wjp.hellospring.domain.entity

enum class ResultCode(
    val code: Int,
    val msg: String
) {
    sucess(1000, "success"),

    failed(1001, "failed"),

    invalid_param(1002, "参数校验失败"),

    error_method(1003, "请求方法不对"),

    error(5000, "未知错误")

}