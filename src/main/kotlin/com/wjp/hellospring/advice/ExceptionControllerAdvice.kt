package com.wjp.hellospring.advice

import com.wjp.hellospring.domain.entity.ResultCode
import com.wjp.hellospring.domain.entity.ResultVO
import com.wjp.hellospring.domain.exeption.ApiException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException

@ControllerAdvice
@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(Exception::class,NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun commonException(e: Exception): ResultVO<String> {
        return ResultVO(ResultCode.failed, e.message)
    }

    @ExceptionHandler(ApiException::class)
    fun apiExceptionHandler(e: ApiException): ResultVO<String> {
        return ResultVO(e.code, e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidHandler(ex: MethodArgumentNotValidException): ResultVO<String> {
        return ResultVO(
            ResultCode.invalid_param,
            ex.bindingResult.allErrors.first().defaultMessage
        )
    }
}