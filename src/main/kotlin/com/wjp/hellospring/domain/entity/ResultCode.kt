package com.wjp.hellospring.domain.entity

enum class ResultCode(
    val code: Int,
    val msg: String
) {
    sucess(1000, "操作成功"),

    failed(1001, "相应失败"),

    invalid_param(1000, "参数校验失败"),

    error(5000, "未知错误")

}